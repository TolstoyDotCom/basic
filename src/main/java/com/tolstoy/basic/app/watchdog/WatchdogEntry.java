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

import java.lang.invoke.MethodHandles;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.tolstoy.basic.api.watchdog.WatchdogSeverity;
import com.tolstoy.basic.api.watchdog.IWatchdogEntry;

public class WatchdogEntry implements IWatchdogEntry {
	private static final Logger logger = LogManager.getLogger( MethodHandles.lookup().lookupClass() );

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

	private final Map<String,Object> variables;
	private final WatchdogSeverity severity;
	private final String message, type, link, location, referer, hostname;
	private final int timestamp;

	public WatchdogEntry( String message ) {
		this.message = message;
		this.type = "java";
		this.variables = new HashMap<String,Object>();
		this.severity = WatchdogSeverity.INFO;
		this.link = "";
		this.location = "";
		this.referer = "";
		this.hostname = "";
		this.timestamp = (int) ( Instant.now().toEpochMilli() / 1000 );
	}

	public WatchdogEntry( String type, String message ) {
		this.message = message;
		this.type = "java " + type;
		this.variables = new HashMap<String,Object>();
		this.severity = WatchdogSeverity.INFO;
		this.link = "";
		this.location = "";
		this.referer = "";
		this.hostname = "";
		this.timestamp = (int) ( Instant.now().toEpochMilli() / 1000 );
	}

	public WatchdogEntry( String type, String message, WatchdogSeverity severity ) {
		this.message = message;
		this.type = "java " + type;
		this.variables = new HashMap<String,Object>();
		this.severity = severity;
		this.link = "";
		this.location = "";
		this.referer = "";
		this.hostname = "";
		this.timestamp = (int) ( Instant.now().toEpochMilli() / 1000 );
	}

	public WatchdogEntry( String type, String message, Map<String,Object> variables ) {
		this.message = message;
		this.type = "java " + type;
		this.variables = variables;
		this.severity = WatchdogSeverity.INFO;
		this.link = "";
		this.location = "";
		this.referer = "";
		this.hostname = "";
		this.timestamp = (int) ( Instant.now().toEpochMilli() / 1000 );
	}

	public WatchdogEntry( String type, String message, Map<String,Object> variables, WatchdogSeverity severity ) {
		this.message = message;
		this.type = "java " + type;
		this.variables = variables;
		this.severity = severity;
		this.link = "";
		this.location = "";
		this.referer = "";
		this.hostname = "";
		this.timestamp = (int) ( Instant.now().toEpochMilli() / 1000 );
	}

	public IWatchdogEntry add( String key, Object value ) {
		variables.put( key, value );

		return this;
	}

	public int getUID() {
		return 1;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public Map<String,Object> getVariables() {
		return variables;
	}

	public WatchdogSeverity getSeverity() {
		return severity;
	}

	public String getLink() {
		return link;
	}

	public String getLocation() {
		return location;
	}

	public String getReferer() {
		return referer;
	}

	public String getHostname() {
		return hostname;
	}

	public int getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE )
			.append( "type", type )
			.append( "message", message )
			.append( "severity", severity )
			.append( "variables", variables )
			.append( "timestamp", timestamp )
			.toString();
	}
}
