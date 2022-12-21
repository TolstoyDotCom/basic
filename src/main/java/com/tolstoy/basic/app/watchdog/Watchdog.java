/*
 * Copyright 2022 Chris Kelly
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
package com.tolstoy.basic.app.watchdog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import de.ailis.pherialize.Pherialize;

import com.tolstoy.basic.api.watchdog.IWatchdog;
import com.tolstoy.basic.api.watchdog.IWatchdogEntry;

public class Watchdog implements IWatchdog {
	private static final String QUERY_WATCHDOG_INSERT = "INSERT INTO watchdog( uid, type, message, variables, severity, " +
															"link, location, referer, hostname, timestamp ) " +
															"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final DataSource dataSource;

	Watchdog( DataSource dataSource ) {
		this.dataSource = dataSource;
	}

	public void save( IWatchdogEntry entry ) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			ps = connection.prepareStatement( QUERY_WATCHDOG_INSERT );

			final Blob blob = connection.createBlob();
			blob.setBytes( 1, Pherialize.serialize( entry.getVariables() ).getBytes() );

			int i = 1;
			ps.setInt( i++, entry.getUID() );
			ps.setString( i++, entry.getType() );
			ps.setString( i++, entry.getMessage() );
			ps.setBlob( i++, blob );
			ps.setInt( i++, entry.getSeverity().getNumber() );
			ps.setString( i++, entry.getLink() );
			ps.setString( i++, entry.getLocation() );
			ps.setString( i++, entry.getReferer() );
			ps.setString( i++, entry.getHostname() );
			ps.setInt( i++, entry.getTimestamp() );

			ps.executeUpdate();
		}
		finally {
			if ( rs != null ) {
				rs.close();
			}
			if ( ps != null ) {
				ps.close();
			}
			if ( connection != null ) {
				connection.close();
			}
		}
	}
}
