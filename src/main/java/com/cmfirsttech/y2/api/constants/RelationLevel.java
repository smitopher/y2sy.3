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

public enum RelationLevel {
	FILE(1),
	KEY(2),
	ATTRIBUTE(3);
	
	public final int relationLevel;
	
	@JsonValue
	public final String jsonString;
	
	private RelationLevel(int relationLevel) {
		this.relationLevel = relationLevel;
		this.jsonString = name();
	}
	
	public static Optional<RelationLevel> getRelationLevel(int i) {
		return Arrays.stream(RelationLevel.values()).filter(l -> l.relationLevel == i).findAny();
	}

	@Override
	public String toString() {
		return jsonString;
	}
	
	
}
