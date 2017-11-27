package com.google.cloud.bigtable.hbase2_x;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AsyncAdminBuilder;
import org.apache.hadoop.hbase.client.AsyncBufferedMutator;
import org.apache.hadoop.hbase.client.AsyncBufferedMutatorBuilder;
import org.apache.hadoop.hbase.client.AsyncConnection;
import org.apache.hadoop.hbase.client.AsyncTable;
import org.apache.hadoop.hbase.client.AsyncTableBuilder;
import org.apache.hadoop.hbase.client.AsyncTableRegionLocator;
import org.apache.hadoop.hbase.client.RawAsyncTable;
import org.apache.hadoop.hbase.security.User;

/**
 * @author spollapally
 */
public class BigtableAsyncConnection implements AsyncConnection, Closeable {
  private BigtableConnection connection;
  //private BigtableRegionLocator2x regionLocator;
  private volatile ExecutorService pool = null;

  public BigtableAsyncConnection(Configuration conf) throws IOException {
    connection = new BigtableConnection(conf);
  }

  public BigtableAsyncConnection(Configuration conf, ExecutorService pool, User user)
      throws IOException {
    connection = new BigtableConnection(conf, pool, user);
  }

  @Override
  public void close() throws IOException {
    connection.close();
  }

  @Override
  public AsyncAdminBuilder getAdminBuilder() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncAdminBuilder getAdminBuilder(ExecutorService arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncBufferedMutatorBuilder getBufferedMutatorBuilder(TableName tableName) {
    return getBufferedMutatorBuilder(tableName, pool);
  }

  @Override
  public AsyncBufferedMutatorBuilder getBufferedMutatorBuilder(final TableName tableName,
      ExecutorService pool) {
    
    //TODO: how is/should this batchPool pool and the one passed to this classes constructor used.
    
    AsyncBufferedMutatorBuilder builder = new AsyncBufferedMutatorBuilder() {

      @Override
      public AsyncBufferedMutatorBuilder setWriteBufferSize(long arg0) {
        // TODO impl?
        throw new UnsupportedOperationException("setWriteBufferSize");
      }

      @Override
      public AsyncBufferedMutatorBuilder setStartLogErrorsCnt(int arg0) {
        // TODO impl?
        throw new UnsupportedOperationException("setStartLogErrorsCnt");
      }

      @Override
      public AsyncBufferedMutatorBuilder setRpcTimeout(long arg0, TimeUnit arg1) {
        // TODO impl?
        throw new UnsupportedOperationException("setRpcTimeout");
      }

      @Override
      public AsyncBufferedMutatorBuilder setRetryPause(long arg0, TimeUnit arg1) {
        // TODO impl?
        throw new UnsupportedOperationException("setRetryPause");
      }

      @Override
      public AsyncBufferedMutatorBuilder setOperationTimeout(long arg0, TimeUnit arg1) {
        // TODO impl?
        throw new UnsupportedOperationException("setOperationTimeout");
      }

      @Override
      public AsyncBufferedMutatorBuilder setMaxAttempts(int arg0) {
        // TODO impl?
        throw new UnsupportedOperationException("setMaxAttempts");
      }

      @Override
      public AsyncBufferedMutator build() {
        return new BigtableAsyncBufferedMutator(connection.createAdapter(tableName),
            connection.getConfiguration(), connection.getSession(), null);
      }
    };

    return builder;
  }

  @Override
  public Configuration getConfiguration() {
    return connection.getConfiguration();
  }

  @Override
  public AsyncTableRegionLocator getRegionLocator(TableName tableName) {
    //BigtableRegionLocator2x regionLocator = new BigtableRegionLocator2x(tableName, connection.getO, connection.getSession().getDataClient());
   //TODO : discuss approach. Will need to consider caching with this approach
    
    final BigtableRegionLocator2x regionLocator = null;
    
    AsyncTableRegionLocator asyncTableRegionLocator = new AsyncTableRegionLocator() {
      
      @Override
      public CompletableFuture<HRegionLocation> getRegionLocation(final byte[] row, final boolean reload) {
        final CompletableFuture<HRegionLocation> future = new CompletableFuture<HRegionLocation>();
            
        CompletableFuture.supplyAsync(new Supplier<HRegionLocation>() {

          @Override
          public HRegionLocation get() {
            HRegionLocation res = null;;
            try {
              res = regionLocator.getRegionLocation(row, reload);
              future.complete(res);
            } catch (IOException ex) {
              future.completeExceptionally(ex);
            }
            return res;
          }
        }, pool);
        
        return future;
      }
            
        
      @Override
      public TableName getName() {
        return regionLocator.getName();
      }
    };
   
    return asyncTableRegionLocator;
  }

  @Override
  public AsyncTableBuilder<RawAsyncTable> getRawTableBuilder(TableName arg0) {
    // TODO Auto-generated method stub
    return null;

  }

  @Override
  public AsyncTableBuilder<AsyncTable> getTableBuilder(TableName arg0, ExecutorService arg1) {
    // TODO Auto-generated method stub
    return null;
  }
}
