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
package com.google.cloud.bigtable.hbase2_x.adapters;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.RegionInfoBuilder;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.bigtable.v2.SampleRowKeysResponse;
import com.google.cloud.bigtable.hbase.adapters.SampledRowKeysAdapter;

/**
 * <p>Pretty much same code as in Hbase 1.x. Needed here as it is not binary compatible with Hbase 2.x.
 *    Explore better options to avoid dup code. 
 * </p>
 * 
 * @author spollapally
 */
public class SampledRowKeysAdapter2_x extends SampledRowKeysAdapter {

  public SampledRowKeysAdapter2_x(TableName tableName, ServerName serverName) {
    super(tableName, serverName);
  }
  
  @Override
  public List<HRegionLocation> adaptResponse(List<SampleRowKeysResponse> responses) {
    List<HRegionLocation> regions = new ArrayList<>();

    // Starting by the first possible row, iterate over the sorted sampled row keys and create regions.
    byte[] startKey = HConstants.EMPTY_START_ROW;

    for (SampleRowKeysResponse response : responses) {
      byte[] endKey = response.getRowKey().toByteArray();

      // Avoid empty regions.
      if (Bytes.equals(startKey, endKey)) {
        continue;
      }
      RegionInfo regionInfo =
          RegionInfoBuilder.newBuilder(tableName).setStartKey(startKey).setEndKey(endKey).build();
      startKey = endKey;

      regions.add(new HRegionLocation(regionInfo, serverName));
    }

    // Create one last region if the last region doesn't reach the end or there are no regions.
    byte[] endKey = HConstants.EMPTY_END_ROW;
    if (regions.isEmpty() || !Bytes.equals(startKey, endKey)) {
      RegionInfo regionInfo =
          RegionInfoBuilder.newBuilder(tableName).setStartKey(startKey).setEndKey(endKey).build();
      regions.add(new HRegionLocation(regionInfo, serverName));
    }
    return regions;
  }
}
