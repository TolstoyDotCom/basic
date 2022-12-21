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

public enum WatchdogSeverity {
	INFO( 6 ),
	WARNING( 4 ),
	ERROR( 3 );

	private int number;

	WatchdogSeverity( final int number ) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public static WatchdogSeverity fromNumber( int num ) {
		for ( WatchdogSeverity p : WatchdogSeverity.values() ) {
			if ( p.getNumber() == num) {
				return p;
			}
		}

		throw new IllegalArgumentException( "no enum found for " + num );
	}
}
