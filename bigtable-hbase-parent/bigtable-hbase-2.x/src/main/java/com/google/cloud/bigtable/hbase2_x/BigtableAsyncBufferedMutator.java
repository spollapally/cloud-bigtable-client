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
package com.google.cloud.bigtable.hbase2_x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AsyncBufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Mutation;

import com.google.cloud.bigtable.config.Logger;
import com.google.cloud.bigtable.grpc.BigtableSession;
import com.google.cloud.bigtable.hbase.BigtableBufferedMutator;
import com.google.cloud.bigtable.hbase.adapters.HBaseRequestAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * Bigtable's {@link org.apache.hadoop.hbase.client.AsyncBufferedMutator} implementation.
 * 
 * @author spollapally
 * 
 */
public class BigtableAsyncBufferedMutator implements AsyncBufferedMutator {
  
  /** Constant <code>LOG</code> */
  protected static final Logger LOG = new Logger(BigtableAsyncBufferedMutator.class);

  private BigtableBufferedMutator bufferedMutator;
  
  public BigtableAsyncBufferedMutator(HBaseRequestAdapter adapter,
      Configuration configuration,
      BigtableSession session,
      BufferedMutator.ExceptionListener listener) {

    //TODO: can Exception lister be null?
    bufferedMutator = new BigtableBufferedMutator(adapter, configuration, session, listener);
  }
  
  @Override
  public void close() {
    
    //TODO: discuss this exception handling
    try {
      bufferedMutator.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void flush() {
    try {
    bufferedMutator.flush();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
 
  }

  @Override
  public Configuration getConfiguration() {
    return bufferedMutator.getConfiguration();
  }

  @Override
  public TableName getName() {
    return bufferedMutator.getName();
  }

  @Override
  public long getWriteBufferSize() {
    return bufferedMutator.getWriteBufferSize();
  }

  @Override
  public List<CompletableFuture<Void>> mutate(List<? extends Mutation> mutations) {
    List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
    
    for (Mutation mutation : mutations) {
      completableFutures.add(mutate(mutation));
    }
    
    return completableFutures; 
  }
  
  @Override
  public CompletableFuture<Void> mutate(Mutation mutation) {
    final ListenableFuture<?> listenableFuture = bufferedMutator.asyncOffer(mutation);
    
    CompletableFuture<Void> cfFuture = new CompletableFuture<Void>() {
      
      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        boolean result = listenableFuture.cancel(mayInterruptIfRunning);
        super.cancel(mayInterruptIfRunning);
        return result;
      };
    };
    
    return cfFuture;
  }

}
