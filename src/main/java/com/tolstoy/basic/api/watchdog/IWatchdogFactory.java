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
package com.tolstoy.basic.api.watchdog;

import java.util.Map;
import javax.sql.DataSource;

/**
 * Save an entry to the Drupal 7/8 watchdog table
 * 
 * Usage:
 *
 * 	javax.sql.DataSource dataSource = ...;
 *	IWatchdogFactory watchdogFactory = new WatchdogFactory();
 *	IWatchdog watchdog = watchdogFactory.createWatchdog( dataSource );
 *	watchdog.save( watchdogFactory.createEntry( "this is my message", "a=@a, b=@b" )
 *		.add( "@a", "some value" )
 * 		.add( "@b", "some other value" )
 * 	);
 *
 * @see https://api.drupal.org/api/drupal/includes%21bootstrap.inc/function/watchdog/7.x
 * 
 */
public interface IWatchdogFactory {
	IWatchdog createWatchdog( DataSource dataSource );
	IWatchdogEntry createEntry( String message );
	IWatchdogEntry createEntry( String type, String message );
	IWatchdogEntry createEntry( String type, String message, WatchdogSeverity severity );
	IWatchdogEntry createEntry( String type, String message, Map<String,Object> variables );
	IWatchdogEntry createEntry( String type, String message, Map<String,Object> variables, WatchdogSeverity severity );
}
