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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataModelRelationType {
	DEFINED_AS("Defined as", "005", ObjectType.FIL),
	EXTENDED_BY("Extended by", "030", ObjectType.FIL),
	OWNED_BY("Owned by", "050", ObjectType.FIL),
	KNOWN_BY("Known by", "060", ObjectType.FLD),
	QUALIFIED_BY("Qualified by", "070", ObjectType.FLD),
	REFERS_TO("Refers to", "100", ObjectType.FIL),
	HAS("Has", "110", ObjectType.FLD),
	INCLUDES("Includes", "120", ObjectType.FIL),
	DENOTED_BY("Denoted by", "130", ObjectType.FLD);
	
	private static class JsonHolder {
		static final Map<String, DataModelRelationType> JSON_MAP;
		static {
			Map<String, DataModelRelationType> map = new HashMap<>();
			for (DataModelRelationType type : DataModelRelationType.values()) {
				map.put(type.jsonString, type);
			}
			JSON_MAP = Collections.unmodifiableMap(map);
		}
	}
	
	private static class CodeHolder {
		static final Map<String, DataModelRelationType> CODE_MAP;
		static {
			Map<String, DataModelRelationType> map = new HashMap<>();
			for (DataModelRelationType type : DataModelRelationType.values()) {
				map.put(type.relationCode, type);
			}
			CODE_MAP = Collections.unmodifiableMap(map);
		}
	}
	
	@JsonValue
	public final String jsonString;
	
	public final String relationCode;
	
	public final ObjectType targetObjectType;

	private DataModelRelationType(String jsonString, String relationCode, ObjectType type) {
		this.jsonString = jsonString;
		this.relationCode = relationCode;
		this.targetObjectType = type;
	}
	
	public static DataModelRelationType fromJsonValue(String s) {
		return JsonHolder.JSON_MAP.get(s);
	}
	
	public static DataModelRelationType fromCodeValue(String s) {
		return CodeHolder.CODE_MAP.get(s);
	}


	@Override
	public String toString() {
		return jsonString;
	}
}
