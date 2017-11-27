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

import org.apache.hadoop.hbase.TableName;

import com.google.cloud.bigtable.config.BigtableOptions;
import com.google.cloud.bigtable.grpc.BigtableDataClient;
import com.google.cloud.bigtable.hbase.BigtableRegionLocator;

/**
 * Wrapper for {@link com.google.cloud.bigtable.hbase.BigtableRegionLocator} to compile it with 2.x
 * classpath. Primarily to adapt to {@link org.apache.hadoop.hbase.HRegionInfo} class hierarchy
 * changes
 * @author spollapally
 */
public class BigtableRegionLocator2x extends BigtableRegionLocator {

  /**
   * <p>
   * Constructor for BigtableRegionLocator.
   * </p>
   * @param tableName a {@link org.apache.hadoop.hbase.TableName} object.
   * @param options a {@link com.google.cloud.bigtable.config.BigtableOptions} object.
   * @param client a {@link com.google.cloud.bigtable.grpc.BigtableDataClient} object.
   */
  public BigtableRegionLocator2x(TableName tableName, BigtableOptions options,
      BigtableDataClient client) {
    super(tableName, options, client);
  }

}
