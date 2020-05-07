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
package com.cmfirsttech.y2.api.model.internal;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.validation.ListObjectType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ModelListQuery {
	@Valid 
	public Optional<@Size(max = 25) String> owner;
	@Valid 
	public Optional<@Size(max = 25) String> object;
	public Optional<ListObjectType> objectType;
	@JsonIgnore
	private final boolean factoryStatic;
	@JsonIgnore
	private final String factoryString;
	private static final String TO_STRING_FORMAT = "(%1$s %2$s %3$s)";
	private static final String NO_OWNER_FORMAT = "(*NONE %1$s %2$s)";
	private static final String ANY_OWNER_FORMAT = "(*ANY %1$s %2$s)";
	private static final String ARRAYS_OWNER_FORMAT = "(*ARRAYS %1$s)";
	private static final String MESSAGES_OWNER_FORMAT = "*MESSAGES %1$s)";
	private static final String ALL_OBJECTS = "(*ALLOBJ)";

	protected ModelListQuery(ModelQueryType mqt, String owner, String object, ObjectType objectType) {
		this.owner = Optional.empty();
		this.object = Optional.empty();
		this.objectType = Optional.empty();
		String objectName = "";
		if (object != null) {
			objectName = escape2eName(object);
		}
		this.factoryStatic = true;
		switch (mqt) {
		case ALL_OBJ:
			this.factoryString = ALL_OBJECTS;
			break;
		case ANY_OWNER:
			this.factoryString = String.format(ANY_OWNER_FORMAT, objectName, objectType);
			break;
		case NO_OWNER:
			
			this.factoryString = String.format(NO_OWNER_FORMAT, objectName, objectType);
			break;
		case ARRAYS_OWNER:
			this.factoryString = String.format(ARRAYS_OWNER_FORMAT, objectName);
			break;
		case MESSAGES_OWNER:
			this.factoryString = String.format(MESSAGES_OWNER_FORMAT, objectName);
			break;
		case HAS_OWNER:
			this.factoryString = String.format(TO_STRING_FORMAT, owner, object, objectType);
			break;
		default:
			this.factoryString = "";
			break;
		}

	}

	public ModelListQuery() {
		factoryStatic = false;
		factoryString = null;
	}
	
	@Override
	public String toString() {
		if (factoryStatic) {
			return factoryString;
		}
		if (owner.isPresent()) {
			if (owner.get().trim().equalsIgnoreCase("*ALLOBJ")) {
				return ALL_OBJECTS;
			}
		}
		return String.format(TO_STRING_FORMAT, 
				escape2eName(owner.orElse("*ANY")), 
				escape2eName(object.orElse("*ALL")),
				objectType.orElse(ListObjectType.ALL));
	}

	public boolean isFactoryStatic() {
		return factoryStatic;
	}
	
	private String escape2eName(String name) {
		switch (name) {
		case "*ANY":
		case "*ALL":
		case "*NONE":
			return name;
		}
		StringBuilder sb = new StringBuilder();
		char apostrophe = '\'';
		sb.append(apostrophe);
		for (char c : name.toCharArray()) {
			sb.append(c);
			if (c == apostrophe) {
				sb.append(apostrophe);
			}
		}
		sb.append(apostrophe);
		return sb.toString();
	}
	
}
