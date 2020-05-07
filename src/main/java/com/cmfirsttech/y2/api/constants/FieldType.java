/*******************************************************************************
 * Copyright 2020 christopher.smith
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.cmfirsttech.y2.api.constants;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FieldType {
	CDE,
	CMP,
	DAT,
	DT_,
	DTE,
	NAR,
	NBR,
	SGT,
	STR,
	STS,
	TM,
	TM_,
	TME,
	TS_,
	TXT,
	USR,
	VNM,
	CUSTOM;
	
	@JsonValue
	public final String jsonString;

	private FieldType() {
		jsonString = name().replace('_', '#');
	}
	
	public static Optional<FieldType> fromJsonValue(String s) {
		return Arrays.stream(FieldType.values()).filter(l -> s.equalsIgnoreCase(l.jsonString)).findAny();
	}

	@Override
	public String toString() {
		return jsonString;
	}
}
