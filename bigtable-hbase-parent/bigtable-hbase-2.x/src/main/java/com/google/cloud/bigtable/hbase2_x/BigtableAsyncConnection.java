package com.google.cloud.bigtable.hbase2_x;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AsyncAdminBuilder;
import org.apache.hadoop.hbase.client.AsyncBufferedMutatorBuilder;
import org.apache.hadoop.hbase.client.AsyncConnection;
import org.apache.hadoop.hbase.client.AsyncTable;
import org.apache.hadoop.hbase.client.AsyncTableBuilder;
import org.apache.hadoop.hbase.client.AsyncTableRegionLocator;
import org.apache.hadoop.hbase.client.RawAsyncTable;

/**
 * 
 * @author spollapally
 */
public class BigtableAsyncConnection implements AsyncConnection, Closeable {

  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub
    
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
  public AsyncBufferedMutatorBuilder getBufferedMutatorBuilder(TableName arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncBufferedMutatorBuilder getBufferedMutatorBuilder(TableName arg0,
      ExecutorService arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Configuration getConfiguration() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncTableBuilder<RawAsyncTable> getRawTableBuilder(TableName arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncTableRegionLocator getRegionLocator(TableName arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AsyncTableBuilder<AsyncTable> getTableBuilder(TableName arg0, ExecutorService arg1) {
    // TODO Auto-generated method stub
    return null;
  }

}
