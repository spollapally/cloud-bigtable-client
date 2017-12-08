/*
 * Copyright 2017 Google Inc. All Rights Reserved.
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
package com.google.cloud.bigtable.hbase1_x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.hadoop.hbase.ProcedureInfo;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AbstractBigtableAdmin;
import org.apache.hadoop.hbase.client.AbstractBigtableConnection;
import org.apache.hadoop.hbase.client.security.SecurityCapability;
import org.apache.hadoop.hbase.protobuf.generated.AdminProtos;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos.SnapshotDescription;
import org.apache.hadoop.hbase.protobuf.generated.MasterProtos;
import org.apache.hadoop.hbase.quotas.QuotaFilter;
import org.apache.hadoop.hbase.quotas.QuotaRetriever;
import org.apache.hadoop.hbase.quotas.QuotaSettings;
import org.apache.hadoop.hbase.snapshot.SnapshotCreationException;

/**
 * This is an hbase 1.x implementation of {@link AbstractBigtableAdmin}. Most methods in this class
 * unsupported
 */
@SuppressWarnings("deprecation")
public class BigtableAdmin extends AbstractBigtableAdmin {

  public BigtableAdmin(AbstractBigtableConnection connection) throws IOException {
    super(connection);
  }

  /** {@inheritDoc} */
  @Override
  public MasterProtos.SnapshotResponse takeSnapshotAsync(
      HBaseProtos.SnapshotDescription snapshot) throws IOException, SnapshotCreationException {
    snapshotTable(snapshot.getName(), TableName.valueOf(snapshot.getTable()));
    LOG.warn("isSnapshotFinished() is not currently supported by BigtableAdmin.\n"
        + "You may poll for existence of the snapshot with listSnapshots(snpashotName)");
    return MasterProtos.SnapshotResponse.newBuilder()
        .setExpectedTimeout(TimeUnit.MINUTES.toMillis(5)).build();
  }

  /** {@inheritDoc} */
  @Override
  public void disableTableAsync(TableName tableName) throws IOException {
    disableTable(tableName);
  }

  /** {@inheritDoc} */
  @Override
  public void enableTableAsync(TableName tableName) throws IOException {
    enableTable(tableName);
  }

  /** {@inheritDoc} */
  @Override
  public AdminProtos.GetRegionInfoResponse.CompactionState getCompactionState(TableName tableName)
      throws IOException {
    throw new UnsupportedOperationException("getCompactionState");
  }

  /** {@inheritDoc} */
  @Override
  public AdminProtos.GetRegionInfoResponse.CompactionState getCompactionStateForRegion(byte[] bytes)
      throws IOException {
    throw new UnsupportedOperationException("getCompactionStateForRegion");
  }

  /** {@inheritDoc} */
  @Override
  public List<HBaseProtos.SnapshotDescription> listSnapshots() throws IOException {
    throw new IllegalArgumentException("listSnapshots is not supported");
  }

  /** {@inheritDoc} */
  @Override
  public List<HBaseProtos.SnapshotDescription> listSnapshots(String regex) throws IOException {
    return listSnapshots(Pattern.compile(regex));
  }

  /** {@inheritDoc} */
  @Override
  public List<HBaseProtos.SnapshotDescription> listSnapshots(Pattern pattern) throws IOException {
    List<HBaseProtos.SnapshotDescription> response = new ArrayList<>();
    for (HBaseProtos.SnapshotDescription description : listSnapshots()) {
      if (pattern.matcher(description.getName()).matches()) {
        response.add(description);
      }
    }
    return response;
  }

  @Override
  public void deleteTableSnapshots(String arg0, String arg1) throws IOException {
    throw new UnsupportedOperationException("deleteTableSnapshots"); // TODO
  }

  @Override
  public void deleteTableSnapshots(Pattern arg0, Pattern arg1) throws IOException {
    throw new UnsupportedOperationException("deleteTableSnapshots"); // TODO
  }

  @Override
  public List<SnapshotDescription> listTableSnapshots(String arg0, String arg1) throws IOException {
    throw new UnsupportedOperationException("listTableSnapshots"); // TODO
  }

  @Override
  public List<SnapshotDescription> listTableSnapshots(Pattern arg0, Pattern arg1)
      throws IOException {
    throw new UnsupportedOperationException("listTableSnapshots"); // TODO
  }

  @Override
  public boolean isBalancerEnabled() throws IOException {
    throw new UnsupportedOperationException("isBalancerEnabled"); // TODO
  }

  @Override
  public long getLastMajorCompactionTimestamp(TableName tableName) throws IOException {
    throw new UnsupportedOperationException("getLastMajorCompactionTimestamp"); // TODO
  }

  @Override
  public long getLastMajorCompactionTimestampForRegion(byte[] regionName) throws IOException {
    throw new UnsupportedOperationException("getLastMajorCompactionTimestampForRegion"); // TODO
  }

  @Override
  public void setQuota(QuotaSettings quota) throws IOException {
    throw new UnsupportedOperationException("setQuota"); // TODO
  }

  @Override
  public QuotaRetriever getQuotaRetriever(QuotaFilter filter) throws IOException {
    throw new UnsupportedOperationException("getQuotaRetriever"); // TODO
  }

  @Override
  public boolean normalize() throws IOException {
    throw new UnsupportedOperationException("normalize"); // TODO
  }

  @Override
  public boolean isNormalizerEnabled() throws IOException {
    throw new UnsupportedOperationException("isNormalizerEnabled"); // TODO
  }

  @Override
  public boolean setNormalizerRunning(boolean on) throws IOException {
    throw new UnsupportedOperationException("setNormalizerRunning"); // TODO
  }

  @Override
  public boolean abortProcedure(long procId, boolean mayInterruptIfRunning) throws IOException {
    throw new UnsupportedOperationException("abortProcedure"); // TODO
  }

  @Override
  public ProcedureInfo[] listProcedures() throws IOException {
    throw new UnsupportedOperationException("listProcedures"); // TODO
  }

  @Override
  public Future<Boolean> abortProcedureAsync(long procId, boolean mayInterruptIfRunning)
      throws IOException {
    throw new UnsupportedOperationException("abortProcedureAsync"); // TODO
  }

  @Override
  public List<SecurityCapability> getSecurityCapabilities() throws IOException {
    throw new UnsupportedOperationException("getSecurityCapabilities"); // TODO
  }

  @Override
  public boolean balancer(boolean arg0) throws IOException {
    throw new UnsupportedOperationException("balancer"); // TODO
  }

  @Override
  public boolean isSplitOrMergeEnabled(MasterSwitchType arg0) throws IOException {
    throw new UnsupportedOperationException("isSplitOrMergeEnabled"); // TODO
  }

  @Override
  public boolean[] setSplitOrMergeEnabled(boolean arg0, boolean arg1, MasterSwitchType... arg2)
      throws IOException {
    throw new UnsupportedOperationException("setSplitOrMergeEnabled"); // TODO
  }
}
