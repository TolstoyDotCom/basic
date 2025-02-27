/*
 * Copyright 2018 Chris Kelly
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tolstoy.basic.api.storage;

import java.util.List;
import java.sql.Connection;

public interface IStorage {
	void connect() throws Exception;

	void ensureTables() throws Exception;
	void dropTables() throws Exception;

	IStorable getRecordByID( final IStorageTable table, final long id ) throws Exception;
	List<IStorable> getRecords( final IStorageTable table, final StorageOrdering ordering, final int max ) throws Exception;
	List<IStorable> getRecords( final IStorageTable table, final String searchkey, final StorageOrdering ordering, final int max ) throws Exception;

	void saveRecord( final IStorageTable table, final IStorable record ) throws Exception;

	Connection getConnection() throws Exception;
}
