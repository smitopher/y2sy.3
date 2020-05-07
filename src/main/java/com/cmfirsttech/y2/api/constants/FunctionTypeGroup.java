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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FunctionTypeGroup {
	FUNCTION_FIELD,
	MESSAGE,
	DATABASE,
	DEVICE,
	USER,
	BUILT_IN;
	
	@JsonValue
	public final String jsonValue;
	
	private FunctionTypeGroup() {
		jsonValue = name();
	}
	
	public Set<FunctionType> getGroupFunctions(FunctionTypeGroup group) {
		return fillSet(Objects.requireNonNull(group, "Group may not be null"));
	}

	private Set<FunctionType> fillSet(FunctionTypeGroup group) {
		return Arrays.stream(FunctionType.values()).filter(l -> group.equals(l.group)).collect(Collectors.toSet());
	}
	
}
