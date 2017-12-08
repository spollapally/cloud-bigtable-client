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

import org.apache.hadoop.hbase.client.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * These tests check various factory instantiations of the Table.
 */
public class TestGetTable extends AbstractTest {
  @Test
  public void testGetTable1() throws Exception {
    Table table = getConnection().getTable(sharedTestEnv.getDefaultTableName());
    Assert.assertEquals(sharedTestEnv.getDefaultTableName(), table.getName());
    table.close();
  }

  @Test
  public void testGetTable2() throws Exception {
    Table table = getConnection().getTable(sharedTestEnv.getDefaultTableName(), Executors.newFixedThreadPool(1));
    Assert.assertEquals(sharedTestEnv.getDefaultTableName(), table.getName());
    table.close();
  }
}
