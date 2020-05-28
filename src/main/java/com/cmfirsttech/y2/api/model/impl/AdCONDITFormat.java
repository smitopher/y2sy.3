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
import java.util.Map;

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.HasNextModel;
import com.cmfirsttech.y2.api.model.IActionDiagram;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Action Diagram DDS format @CONDIT
 * 
 */
@Y2EntityClass(entityClass = Y2FunctionActionDiagram.class)
@JsonInclude(Include.NON_NULL)
public class AdCONDITFormat extends HasNextModel {
	
	public String elementTitle;

	@DirectMapped(mapSource = "subChain1Sgt")
	public Integer owningField;
	
	@DirectMapped(mappingType = SKIP)
	public String owningFieldName;

	@DirectMapped(mapSource = "subChain2Sgt")
	public Integer conditionSurrogate;
	
	@DirectMapped(mappingType = SKIP)
	public FieldCondition fieldCondition;

	@DirectMapped(mapSource = "subChain3Sgt")
	public Integer comparisonSurrogate;

	@DirectMapped(mapSource = "elementAttribute")
	public String sourceContextGroup;

	public String userDefined;

	public String systemGenerated;

	@DirectMapped(mappingType = MappingType.INSTANT, instantDateSource = "dateAddedLastChanged", instantTimeSource = "timeAddedLastChanged")
	public Instant auditStamp;

	@DirectMapped(mapSource = "levelFieldSurrogate")
	public Integer compoundConditionChain;
	
	@DirectMapped(mappingType = SKIP)
	public Map<Integer, IActionDiagram> compoundCondition;

	@DirectMapped(mapSource = "deviceStructureLeafSgt")
	public Integer userExpressionChain;

	@DirectMapped(mappingType = SKIP)
	public Map<Integer, IActionDiagram> userExpression;

	public String hideElement;

	@DirectMapped(mapSource = "allowElementDeletion")
	public String operator;

	@DirectMapped(mapSource = "allowElementInsertion")
	public String not;

	public String expressionToken;

	public Integer tokenSubIdentifier;

}