package com.google.cloud.bigtable.hbase2_x;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AbstractBigtableConnection;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.AsyncTable;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.RowMutations;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.ScanResultConsumer;

import com.google.cloud.bigtable.hbase.BigtableTable;
import com.google.cloud.bigtable.hbase.adapters.HBaseRequestAdapter;

/**
 * 
 * @author spollapally
 *
 */
public class BigtableAsyncTable implements AsyncTable {
  private BigtableTable bigtableTable;
  
  public BigtableAsyncTable(AbstractBigtableConnection bigtableConnection,
      HBaseRequestAdapter hbaseAdapter) {
    
    bigtableTable = new BigtableTable(bigtableConnection, hbaseAdapter);
  }
  
  //TODO: discuss a good approach. Looks like for most of these we need similar logic in bigtableTable with corresponding
  //async methods similar to readModifyWriteRowAsync for append(), and wrap them in CompletableFuture ??
  
  
  @Override
  public CompletableFuture<Result> append(Append arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> List<CompletableFuture<T>> batch(List<? extends Row> arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CheckAndMutateBuilder checkAndMutate(byte[] arg0, byte[] arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Void> delete(Delete arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<CompletableFuture<Void>> delete(List<Delete> arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Result> get(Get arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<CompletableFuture<Result>> get(List<Get> arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Configuration getConfiguration() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long getOperationTimeout(TimeUnit arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getReadRpcTimeout(TimeUnit arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getRpcTimeout(TimeUnit arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getScanTimeout(TimeUnit arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getWriteRpcTimeout(TimeUnit arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public CompletableFuture<Result> increment(Increment arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Void> mutateRow(RowMutations arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Void> put(Put arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<CompletableFuture<Void>> put(List<Put> arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<List<Result>> scanAll(Scan arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResultScanner getScanner(Scan arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void scan(Scan arg0, ScanResultConsumer arg1) {
    // TODO Auto-generated method stub
    
  }

}
