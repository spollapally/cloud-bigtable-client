/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Row;

import com.google.cloud.bigtable.config.BigtableOptions;
import com.google.cloud.bigtable.config.Logger;
import com.google.cloud.bigtable.grpc.BigtableSession;
import com.google.cloud.bigtable.grpc.BigtableTableName;
import com.google.cloud.bigtable.grpc.async.AsyncExecutor;
import com.google.cloud.bigtable.grpc.async.BulkMutation;
import com.google.cloud.bigtable.hbase.adapters.HBaseRequestAdapter;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Bigtable's {@link org.apache.hadoop.hbase.client.BufferedMutator} implementation.
 *
 * @author sduskis
 * @version $Id: $Id
 */
public class BigtableBufferedMutator implements BufferedMutator {

  /** Constant <code>LOG</code> */
  protected static final Logger LOG = new Logger(BigtableBufferedMutator.class);

  private static class MutationException {
    private final Row mutation;
    private final Throwable throwable;

    MutationException(Row mutation, Throwable throwable) {
      this.mutation = mutation;
      this.throwable = throwable;
    }
  }

  private final Configuration configuration;

  /**
   * Makes sure that mutations and flushes are safe to proceed.  Ensures that while the mutator
   * is closing, there will be no additional writes.
   */
  private final ReentrantReadWriteLock isClosedLock = new ReentrantReadWriteLock();
  private final ReadLock closedReadLock = isClosedLock.readLock();
  private final WriteLock closedWriteLock = isClosedLock.writeLock();

  private boolean closed = false;

  private final HBaseRequestAdapter adapter;
  private final ExceptionListener exceptionListener;

  private final AtomicBoolean hasExceptions = new AtomicBoolean(false);
  private final List<MutationException> globalExceptions = new ArrayList<MutationException>();

  private final String host;

  private final AsyncExecutor asyncExecutor;

  private BulkMutation bulkMutation = null;

  private BigtableOptions options;

  /**
   * <p>Constructor for BigtableBufferedMutator.</p>
   *
   * @param adapter Converts HBase objects to Bigtable protos
   * @param configuration For Additional configuration. TODO: move this to options
   * @param listener Handles exceptions. By default, it just throws the exception.
   * @param session a {@link com.google.cloud.bigtable.grpc.BigtableSession} to get {@link com.google.cloud.bigtable.config.BigtableOptions}, {@link com.google.cloud.bigtable.grpc.async.AsyncExecutor}
   * and {@link com.google.cloud.bigtable.grpc.async.BulkMutation} objects from
   * starting the async operations on the BigtableDataClient.
   */
  public BigtableBufferedMutator(
      HBaseRequestAdapter adapter,
      Configuration configuration,
      BigtableSession session,
      BufferedMutator.ExceptionListener listener) {
    this.adapter = adapter;
    this.configuration = configuration;
    this.exceptionListener = listener;
    this.options = session.getOptions();
    this.host = options.getDataHost().toString();
    this.asyncExecutor = session.createAsyncExecutor();
    BigtableTableName tableName = this.adapter.getBigtableTableName();
    this.bulkMutation = session.createBulkMutation(tableName);
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws IOException {
    closedWriteLock.lock();
    try {
      flush();
      asyncExecutor.flush();
      closed = true;
    } finally {
      closedWriteLock.unlock();
    }
  }

  /** {@inheritDoc} */
  @Override
  public void flush() throws IOException {
    // If there is a bulk mutation in progress, then send it.
    if (bulkMutation != null) {
      try {
        bulkMutation.flush();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IOException("flush() was interrupted", e);
      }
    }
    asyncExecutor.flush();
    handleExceptions();
  }

  /** {@inheritDoc} */
  @Override
  public Configuration getConfiguration() {
    return this.configuration;
  }

  /** {@inheritDoc} */
  @Override
  public TableName getName() {
    return this.adapter.getTableName();
  }

  /** {@inheritDoc} */
  @Override
  public long getWriteBufferSize() {
    return this.options.getBulkOptions().getMaxMemory();
  }

  /** {@inheritDoc} */
  @Override
  public void mutate(List<? extends Mutation> mutations) throws IOException {
    closedReadLock.lock();
    try {
      if (closed) {
        throw new IllegalStateException("Cannot mutate when the BufferedMutator is closed.");
      }
      handleExceptions();
      for (Mutation mutation : mutations) {
        offer(mutation);
      }
    } finally {
      closedReadLock.unlock();
    }
  }

  /**
   * {@inheritDoc}
   *
   * Being a Mutation. This method will block if either of the following are true:
   * 1) There are more than {@code maxInflightRpcs} RPCs in flight
   * 2) There are more than {@link #getWriteBufferSize()} bytes pending
   */
  @Override
  public void mutate(final Mutation mutation) throws IOException {
    closedReadLock.lock();
    try {
      if (closed) {
        throw new IllegalStateException("Cannot mutate when the BufferedMutator is closed.");
      }
      handleExceptions();
      offer(mutation);
    } finally {
      closedReadLock.unlock();
    }
  }

  /**
   * Send the operations to the async executor asynchronously.  The conversion from hbase
   * object to cloud bigtable proto and the async call both take time (microseconds worth) that
   * could be parallelized, or at least removed from the user's thread.
   */
  @SuppressWarnings("unchecked")
  private void offer(Mutation mutation) {
    ListenableFuture<?> future = null;
    try {
      if (mutation == null) {
        future = Futures.immediateFailedFuture(
          new IllegalArgumentException("Cannot perform a mutation on a null object."));
      } else if (mutation instanceof Put) {
        future = bulkMutation.add(adapter.adaptEntry((Put) mutation));
      } else if (mutation instanceof Delete) {
        future = bulkMutation.add(adapter.adaptEntry((Delete) mutation));
      } else if (mutation instanceof Increment) {
        future = asyncExecutor.readModifyWriteRowAsync(adapter.adapt((Increment) mutation));
      } else if (mutation instanceof Append) {
        future = asyncExecutor.readModifyWriteRowAsync(adapter.adapt((Append) mutation));
      } else {
        future = Futures.immediateFailedFuture(new IllegalArgumentException(
            "Encountered unknown mutation type: " + mutation.getClass()));
      }
    } catch (Exception e) {
      // issueRequest(mutation) could throw an Exception for validation issues. Remove the heapsize
      // and inflight rpc count.
      future = Futures.immediateFailedFuture(e);
    }
    Futures.addCallback(future, new ExceptionCallback(mutation), MoreExecutors.directExecutor());
  }

  private void addGlobalException(Row mutation, Throwable t) {
    synchronized (globalExceptions) {
      globalExceptions.add(new MutationException(mutation, t));
      hasExceptions.set(true);
    }
  }

  /**
   * Create a {@link RetriesExhaustedWithDetailsException} if there were any async exceptions and
   * send it to the {@link org.apache.hadoop.hbase.client.BufferedMutator.ExceptionListener}.
   */
  private void handleExceptions() throws RetriesExhaustedWithDetailsException {
    if (hasExceptions.get()) {
      ArrayList<MutationException> mutationExceptions = null;
      synchronized (globalExceptions) {
        hasExceptions.set(false);
        if (globalExceptions.isEmpty()) {
          return;
        }

        mutationExceptions = new ArrayList<>(globalExceptions);
        globalExceptions.clear();
      }

      List<Throwable> problems = new ArrayList<>(mutationExceptions.size());
      ArrayList<String> hostnames = new ArrayList<>(mutationExceptions.size());
      List<Row> failedMutations = new ArrayList<>(mutationExceptions.size());

      if (!mutationExceptions.isEmpty()) {
        LOG.warn("Exception occurred in BufferedMutator", mutationExceptions.get(0).throwable);
      }
      for (MutationException mutationException : mutationExceptions) {
        problems.add(mutationException.throwable);
        failedMutations.add(mutationException.mutation);
        hostnames.add(host);
        LOG.debug("Exception occurred in BufferedMutator", mutationException.throwable);
      }

      RetriesExhaustedWithDetailsException exception = new RetriesExhaustedWithDetailsException(
          problems, failedMutations, hostnames);

      exceptionListener.onException(exception, this);
    }
  }

  @SuppressWarnings("rawtypes")
  private class ExceptionCallback implements FutureCallback {
    private final Row mutation;

    public ExceptionCallback(Row mutation) {
      this.mutation = mutation;
    }

    @Override
    public void onFailure(Throwable t) {
      addGlobalException(mutation, t);
    }

    @Override
    public void onSuccess(Object ignored) {
    }
  }

  /**
   * <p>hasInflightRequests.</p>
   *
   * @return a boolean.
   */
  public boolean hasInflightRequests() {
    return this.asyncExecutor.hasInflightRequests()
        || (bulkMutation != null && !bulkMutation.isFlushed());
  }
}
