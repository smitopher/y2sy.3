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

/**
 * Action Diagram DDS format @SUBBLK
 * 
 */
@Y2EntityClass(entityClass = Y2FunctionActionDiagram.class)
public class AdSUBBLKFormat extends HasNextModel {
	
	@DirectMapped(mapSource = "subChain1Sgt")
	public Integer controllingCondition;
	
	@DirectMapped(mappingType = SKIP)
	public AdCONDITFormat adCONDITFormat;

	@DirectMapped(mapSource = "subChain2Sgt")
	public Integer blockChain;
	
	@DirectMapped(mappingType = SKIP)
	public Map<Integer, IActionDiagram> adBlocks;

	public String allowElementDeletion;

	public String allowElementInsertion;

	public String userDefined;

	public String systemGenerated;

	public String conditionStub;

	public String blockStatus;

	@DirectMapped(mappingType = MappingType.INSTANT, instantDateSource = "dateAddedLastChanged", instantTimeSource = "timeAddedLastChanged")
	public Instant auditStamp;

}