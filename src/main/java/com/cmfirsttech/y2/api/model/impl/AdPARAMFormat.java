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

import static com.cmfirsttech.y2.api.mapper.MappingType.SKIP;

import java.time.Instant;
import java.util.Optional;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2Field;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.HasNextModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

/**
 * Action Diagram DDS format @PARAM
 * 
 */
@Y2EntityClass(entityClass = Y2FunctionActionDiagram.class)
public class AdPARAMFormat extends HasNextModel {
	
	@DirectMapped(mapSource = "subChain1Sgt")
	public Integer parameterBlockNumber;

	@DirectMapped(mapSource = "subChain2Sgt")
	public Integer fieldSurrogate;
	
	@DirectMapped(mappingType = SKIP)
	public String fieldName;

	@DirectMapped(mapSource = "subChain3Sgt")
	public Integer sourceFieldSurrogate;

	@DirectMapped(mappingType = SKIP)
	public String sourceFieldname;

	@DirectMapped(mapSource = "elementAttribute")
	public String sourceGroupContext;

	@DirectMapped(mapSource = "levelFieldSurrogate")
	public Integer actionIfParIsMsg;

	public String expressionToken;

	public Integer tokenSubIdentifier;

	public String userDefined;

	public String systemGenerated;

	@DirectMapped(mappingType = MappingType.INSTANT, instantDateSource = "dateAddedLastChanged", instantTimeSource = "timeAddedLastChanged")
	public Instant auditStamp;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		if (fieldSurrogate != null) {
			Optional<Y2Field> y2Field = Y2Field.findByIdOptional(fieldSurrogate);
			if (y2Field.isPresent()) {
				fieldName = y2Field.get().fieldName.stripTrailing();
			} else {
				Logger.getLogger(getClass()).error(String.format(
						"AdPARAM: Y2Field not found for fieldSurrogate with surrogate %1$d", fieldSurrogate));
			}
			
		}
		if (sourceFieldSurrogate != null) {
			Optional<Y2Field> y2Field = Y2Field.findByIdOptional(sourceFieldSurrogate);
			if (y2Field.isPresent()) {
				sourceFieldname = y2Field.get().fieldName.stripTrailing();
			} else {
				Logger.getLogger(getClass()).error(String.format(
						"AdPARAM: Y2Field not found for sourceFieldSurrogate with surrogate %1$d", sourceFieldSurrogate));
			}
		}
	}

}