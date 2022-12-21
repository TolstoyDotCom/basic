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

import java.util.Map;
import javax.sql.DataSource;

import com.tolstoy.basic.api.watchdog.IWatchdog;
import com.tolstoy.basic.api.watchdog.IWatchdogEntry;
import com.tolstoy.basic.api.watchdog.IWatchdogFactory;
import com.tolstoy.basic.api.watchdog.WatchdogSeverity;

public class WatchdogFactory implements IWatchdogFactory {
	public IWatchdog createWatchdog( DataSource dataSource ) {
		return new Watchdog( dataSource );
	}

	public IWatchdogEntry createEntry( String message ) {
		return new WatchdogEntry( message );
	}

	public IWatchdogEntry createEntry( String type, String message ) {
		return new WatchdogEntry( type, message );
	}

	public IWatchdogEntry createEntry( String type, String message, WatchdogSeverity severity ) {
		return new WatchdogEntry( type, message, severity );
	}

	public IWatchdogEntry createEntry( String type, String message, Map<String,Object> variables ) {
		return new WatchdogEntry( type, message, variables );
	}

	public IWatchdogEntry createEntry( String type, String message, Map<String,Object> variables, WatchdogSeverity severity ) {
		return new WatchdogEntry( type, message, variables, severity );
	}
}
