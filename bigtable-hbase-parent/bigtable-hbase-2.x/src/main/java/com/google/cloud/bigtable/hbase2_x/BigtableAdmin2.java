package com.google.cloud.bigtable.hbase2_x;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.RegionLoad;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.ClusterStatus.Option;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.AbstractBigtableAdmin;
import org.apache.hadoop.hbase.client.AbstractBigtableConnection;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.CompactType;
import org.apache.hadoop.hbase.client.CompactionState;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.MasterSwitchType;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.SnapshotType;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.replication.TableCFs;
import org.apache.hadoop.hbase.client.security.SecurityCapability;
import org.apache.hadoop.hbase.ipc.CoprocessorRpcChannel;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos.SnapshotDescription;
import org.apache.hadoop.hbase.quotas.QuotaFilter;
import org.apache.hadoop.hbase.quotas.QuotaRetriever;
import org.apache.hadoop.hbase.quotas.QuotaSettings;
import org.apache.hadoop.hbase.regionserver.wal.FailedLogCloseException;
import org.apache.hadoop.hbase.snapshot.HBaseSnapshotException;
import org.apache.hadoop.hbase.snapshot.RestoreSnapshotException;
import org.apache.hadoop.hbase.snapshot.SnapshotCreationException;
import org.apache.hadoop.hbase.snapshot.UnknownSnapshotException;
import org.apache.hadoop.hbase.util.Pair;

import com.google.cloud.bigtable.config.BigtableOptions;
import com.google.cloud.bigtable.grpc.BigtableTableAdminClient;

public class BigtableAdmin2 implements Admin {
  BigtableAdmin2(BigtableOptions options, Configuration configuration,
      AbstractBigtableConnection connection, BigtableTableAdminClient bigtableTableAdminClient,
      Set<TableName> disabledTables) {
//    super(options, configuration, connection, bigtableTableAdminClient, disabledTables);
  }

  @Override
  public void abort(String arg0, Throwable arg1) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean abortProcedure(long arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Future<Boolean> abortProcedureAsync(long arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addColumnFamily(TableName arg0, ColumnFamilyDescriptor arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> addColumnFamilyAsync(TableName arg0, ColumnFamilyDescriptor arg1)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void assign(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean balance() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean balance(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean balancerSwitch(boolean arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean catalogJanitorSwitch(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean cleanerChoreSwitch(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void clearCompactionQueues(ServerName arg0, Set<String> arg1)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<ServerName> clearDeadServers(List<ServerName> arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void cloneSnapshot(byte[] arg0, TableName arg1)
      throws IOException, TableExistsException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void cloneSnapshot(String arg0, TableName arg1)
      throws IOException, TableExistsException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void cloneSnapshot(String arg0, TableName arg1, boolean arg2)
      throws IOException, TableExistsException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> cloneSnapshotAsync(String arg0, TableName arg1)
      throws IOException, TableExistsException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void closeRegion(String arg0, String arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void closeRegion(byte[] arg0, String arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void closeRegion(ServerName arg0, HRegionInfo arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean closeRegionWithEncodedRegionName(String arg0, String arg1) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void compact(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compact(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compact(TableName arg0, CompactType arg1) throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compact(TableName arg0, byte[] arg1, CompactType arg2)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compactRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compactRegion(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compactRegionServer(ServerName arg0, boolean arg1)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public CoprocessorRpcChannel coprocessorService() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CoprocessorRpcChannel coprocessorService(ServerName arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void createNamespace(NamespaceDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> createNamespaceAsync(NamespaceDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void createTable(TableDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void createTable(TableDescriptor arg0, byte[][] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void createTable(TableDescriptor arg0, byte[] arg1, byte[] arg2, int arg3)
      throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> createTableAsync(TableDescriptor arg0, byte[][] arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void decommissionRegionServers(List<ServerName> arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteColumn(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteColumnFamily(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> deleteColumnFamilyAsync(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteNamespace(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> deleteNamespaceAsync(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteSnapshot(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteSnapshot(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteSnapshots(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteSnapshots(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteTable(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> deleteTableAsync(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteTableSnapshots(String arg0, String arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteTableSnapshots(Pattern arg0, Pattern arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public HTableDescriptor[] deleteTables(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] deleteTables(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void disableTable(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> disableTableAsync(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void disableTableReplication(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public HTableDescriptor[] disableTables(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] disableTables(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void enableTable(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> enableTableAsync(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void enableTableReplication(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public HTableDescriptor[] enableTables(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] enableTables(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void execProcedure(String arg0, String arg1, Map<String, String> arg2) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public byte[] execProcedureWithReturn(String arg0, String arg1, Map<String, String> arg2)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void flush(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void flushRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Pair<Integer, Integer> getAlterStatus(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Pair<Integer, Integer> getAlterStatus(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ClusterStatus getClusterStatus() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ClusterStatus getClusterStatus(EnumSet<Option> arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompactionState getCompactionState(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompactionState getCompactionState(TableName arg0, CompactType arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompactionState getCompactionStateForRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Configuration getConfiguration() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Connection getConnection() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableDescriptor getDescriptor(TableName arg0) throws TableNotFoundException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long getLastMajorCompactionTimestamp(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getLastMajorCompactionTimestampForRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getLocks() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getMasterCoprocessors() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NamespaceDescriptor getNamespaceDescriptor(String arg0)
      throws NamespaceNotFoundException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<HRegionInfo> getOnlineRegions(ServerName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getOperationTimeout() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getProcedures() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public QuotaRetriever getQuotaRetriever(QuotaFilter arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<byte[], RegionLoad> getRegionLoad(ServerName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<byte[], RegionLoad> getRegionLoad(ServerName arg0, TableName arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<RegionInfo> getRegions(ServerName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<RegionInfo> getRegions(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<SecurityCapability> getSecurityCapabilities() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor getTableDescriptor(TableName arg0)
      throws TableNotFoundException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] getTableDescriptors(List<String> arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] getTableDescriptorsByTableName(List<TableName> arg0)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<HRegionInfo> getTableRegions(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAborted() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isBalancerEnabled() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCatalogJanitorEnabled() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCleanerChoreEnabled() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMasterInMaintenanceMode() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isNormalizerEnabled() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isProcedureFinished(String arg0, String arg1, Map<String, String> arg2)
      throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSnapshotFinished(org.apache.hadoop.hbase.client.SnapshotDescription arg0)
      throws IOException, HBaseSnapshotException, UnknownSnapshotException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTableAvailable(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTableAvailable(TableName arg0, byte[][] arg1) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTableDisabled(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTableEnabled(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<ServerName> listDeadServers() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ServerName> listDecommissionedRegionServers() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NamespaceDescriptor[] listNamespaceDescriptors() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableCFs> listReplicatedTableCFs() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots()
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots(String arg0)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots(Pattern arg0)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableDescriptor> listTableDescriptors() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableDescriptor> listTableDescriptors(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableDescriptor> listTableDescriptors(List<TableName> arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableDescriptor> listTableDescriptors(Pattern arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTableDescriptorsByNamespace(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TableDescriptor> listTableDescriptorsByNamespace(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNames() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNames(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNames(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNames(Pattern arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNames(String arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TableName[] listTableNamesByNamespace(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listTableSnapshots(String arg0,
      String arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listTableSnapshots(Pattern arg0,
      Pattern arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTables() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTables(Pattern arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTables(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTables(Pattern arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HTableDescriptor[] listTables(String arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void majorCompact(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void majorCompact(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void majorCompact(TableName arg0, CompactType arg1)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void majorCompact(TableName arg0, byte[] arg1, CompactType arg2)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void majorCompactRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void majorCompactRegion(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mergeRegions(byte[] arg0, byte[] arg1, boolean arg2) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> mergeRegionsAsync(byte[][] arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> mergeRegionsAsync(byte[] arg0, byte[] arg1, boolean arg2) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void modifyColumnFamily(TableName arg0, ColumnFamilyDescriptor arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> modifyColumnFamilyAsync(TableName arg0, ColumnFamilyDescriptor arg1)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void modifyNamespace(NamespaceDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> modifyNamespaceAsync(NamespaceDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void modifyTable(TableDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void modifyTable(TableName arg0, TableDescriptor arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> modifyTableAsync(TableDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> modifyTableAsync(TableName arg0, TableDescriptor arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void move(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean normalize() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean normalizerSwitch(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void offline(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void recommissionRegionServer(ServerName arg0, List<byte[]> arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restoreSnapshot(byte[] arg0) throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restoreSnapshot(String arg0) throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restoreSnapshot(byte[] arg0, boolean arg1)
      throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restoreSnapshot(String arg0, boolean arg1)
      throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void restoreSnapshot(String arg0, boolean arg1, boolean arg2)
      throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> restoreSnapshotAsync(String arg0)
      throws IOException, RestoreSnapshotException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void rollWALWriter(ServerName arg0) throws IOException, FailedLogCloseException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int runCatalogJanitor() throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean runCleanerChore() throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setQuota(QuotaSettings arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void shutdown() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void snapshot(org.apache.hadoop.hbase.client.SnapshotDescription arg0)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void snapshot(String arg0, TableName arg1)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void snapshot(byte[] arg0, TableName arg1)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void snapshot(String arg0, TableName arg1, SnapshotType arg2)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void snapshotAsync(org.apache.hadoop.hbase.client.SnapshotDescription arg0)
      throws IOException, SnapshotCreationException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void split(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void split(TableName arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean splitOrMergeEnabledSwitch(MasterSwitchType arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean[] splitOrMergeEnabledSwitch(boolean arg0, boolean arg1, MasterSwitchType... arg2)
      throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void splitRegion(byte[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void splitRegion(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> splitRegionAsync(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void stopMaster() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stopRegionServer(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean tableExists(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void truncateTable(TableName arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> truncateTableAsync(TableName arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void unassign(byte[] arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateConfiguration() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateConfiguration(ServerName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

}