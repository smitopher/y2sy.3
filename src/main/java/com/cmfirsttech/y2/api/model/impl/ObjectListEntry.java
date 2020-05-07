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
package com.cmfirsttech.y2.api.model.impl;

import java.time.Instant;

import com.cmfirsttech.y2.api.constants.FunctionType;
import com.cmfirsttech.y2.api.constants.ObjectAttribute;
import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.entity.impl.Y2ModelListEntry;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2ModelListEntry.class)
public class ObjectListEntry extends AbstractModel{
	
	public Integer objectSurrogate;
	
	public String object;
	
	public ObjectType objectType;
	
	public ObjectAttribute objectAttribute;
	
	public Integer ownerSurrogate;
	
	public String owner;
	
	public String implName;
	
	public FunctionType functionType;

	@DirectMapped(instantDateSource = "creationDate", instantTimeSource = "creationTime")
	public Instant creationTimestamp;
	@DirectMapped(instantDateSource = "changeDate", instantTimeSource = "changeTime")
	public Instant updateTimestamp;
}
