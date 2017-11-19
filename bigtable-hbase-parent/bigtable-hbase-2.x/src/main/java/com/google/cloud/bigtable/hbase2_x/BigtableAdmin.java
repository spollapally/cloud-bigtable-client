package com.google.cloud.bigtable.hbase2_x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.ClusterStatus.Option;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.RegionLoad;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.AbstractBigtableAdmin;
import org.apache.hadoop.hbase.client.AbstractBigtableConnection;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.CompactType;
import org.apache.hadoop.hbase.client.CompactionState;
import org.apache.hadoop.hbase.client.MasterSwitchType;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.SnapshotType;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.replication.TableCFs;
import org.apache.hadoop.hbase.client.security.SecurityCapability;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.quotas.QuotaFilter;
import org.apache.hadoop.hbase.quotas.QuotaRetriever;
import org.apache.hadoop.hbase.quotas.QuotaSettings;
import org.apache.hadoop.hbase.snapshot.HBaseSnapshotException;
import org.apache.hadoop.hbase.snapshot.RestoreSnapshotException;
import org.apache.hadoop.hbase.snapshot.SnapshotCreationException;
import org.apache.hadoop.hbase.snapshot.UnknownSnapshotException;
import org.apache.hadoop.hbase.shaded.protobuf.generated.MasterProtos;
import com.google.cloud.bigtable.config.BigtableOptions;
import com.google.cloud.bigtable.grpc.BigtableTableAdminClient;

/**
 * 
 * @author spollapally
 */
public class BigtableAdmin extends AbstractBigtableAdmin {

  public BigtableAdmin(BigtableOptions options, Configuration configuration,
      AbstractBigtableConnection connection, BigtableTableAdminClient bigtableTableAdminClient,
      Set<TableName> disabledTables) {
    super(options, configuration, connection, bigtableTableAdminClient, disabledTables);
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots()
      throws IOException {
    throw new IllegalArgumentException("listSnapshots is not supported");
  }

  @Override
  public void createTable(TableDescriptor desc) throws IOException {
    createTable((HTableDescriptor) desc);
  }
  

  @Override
  public void createTable(TableDescriptor desc, byte[][] splitKeys) throws IOException {
    createTable((HTableDescriptor) desc, splitKeys);    
  }

  @Override
  public void createTable(TableDescriptor desc, byte[] startKey, byte[] endKey, int numRegions)
      throws IOException {
    createTable((HTableDescriptor) desc, startKey, endKey, numRegions);
  }

  @Override
  public Future<Void> createTableAsync(TableDescriptor desc, byte[][] splitKeys) throws IOException {
    createTableAsync((HTableDescriptor) desc, splitKeys);
    return null;
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots(String regex)
      throws IOException {
    return listSnapshots(Pattern.compile(regex));
  }

  @Override
  public List<org.apache.hadoop.hbase.client.SnapshotDescription> listSnapshots(Pattern pattern)
      throws IOException {
    List<org.apache.hadoop.hbase.client.SnapshotDescription> response = new ArrayList<>();
    for (org.apache.hadoop.hbase.client.SnapshotDescription description : listSnapshots()) {
      if (pattern.matcher(description.getName()).matches()) {
        response.add(description);
      }
    }
    return response;

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
  public void compact(TableName arg0, CompactType arg1) throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void compact(TableName arg0, byte[] arg1, CompactType arg2)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Future<Void> createNamespaceAsync(NamespaceDescriptor arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void decommissionRegionServers(List<ServerName> arg0, boolean arg1) throws IOException {
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
  public Future<Void> deleteNamespaceAsync(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
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
  public Future<Void> disableTableAsync(TableName tableName) throws IOException {
    disableTable(tableName);
    return null;
  }

  @Override
  public void disableTableReplication(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
  }

  @Override
  public Future<Void> enableTableAsync(TableName tableName) throws IOException {
    enableTable(tableName);
    return null;
  }

  @Override
  public void enableTableReplication(TableName arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public byte[] execProcedureWithReturn(String arg0, String arg1, Map<String, String> arg2)
      throws IOException {
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
    throw new UnsupportedOperationException("getCompactionState");
  }

  @Override
  public CompactionState getCompactionState(TableName arg0, CompactType arg1) throws IOException {
    throw new UnsupportedOperationException("getCompactionState");
  }

  @Override
  public CompactionState getCompactionStateForRegion(byte[] arg0) throws IOException {
    throw new UnsupportedOperationException("getCompactionStateForRegion");
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
  public boolean isBalancerEnabled() throws IOException {
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
  public boolean isSnapshotFinished(org.apache.hadoop.hbase.client.SnapshotDescription arg0)
      throws IOException, HBaseSnapshotException, UnknownSnapshotException {
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
  public List<TableCFs> listReplicatedTableCFs() throws IOException {
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
  public List<TableDescriptor> listTableDescriptorsByNamespace(byte[] arg0) throws IOException {
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
  public void recommissionRegionServer(ServerName arg0, List<byte[]> arg1) throws IOException {
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
  public void snapshot(org.apache.hadoop.hbase.client.SnapshotDescription snapshot)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    snapshot(snapshot.getName(), snapshot.getTableName());
  }

  @Override
  public void snapshot(String snapshotName, TableName tableName, SnapshotType arg2)
      throws IOException, SnapshotCreationException, IllegalArgumentException {
    snapshot(snapshotName, tableName);
  }

  @Override
  public void snapshotAsync(org.apache.hadoop.hbase.client.SnapshotDescription snapshot)
      throws IOException, SnapshotCreationException {
    snapshotTable(snapshot.getName(), snapshot.getTableName());
    LOG.warn("isSnapshotFinished() is not currently supported by BigtableAdmin.\n"
        + "You may poll for existence of the snapshot with listSnapshots(snpashotName)");   
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
  public Future<Void> splitRegionAsync(byte[] arg0, byte[] arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> truncateTableAsync(TableName arg0, boolean arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

}