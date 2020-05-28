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

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.AbstractActionDiagramElement;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Action Diagram DDS format @CONTEXT
 * 
 */
@Y2EntityClass(entityClass = Y2FunctionActionDiagram.class)
@JsonInclude(Include.NON_NULL)
public class AdCONTEXTFormat extends AbstractActionDiagramElement {
	
	@DirectMapped(mapSource = "elementTitle")
	public String contextProgramDescribed;

	public String userDefined;

	public String systemGenerated;

	@DirectMapped(mappingType = MappingType.INSTANT, instantDateSource = "dateAddedLastChanged", instantTimeSource = "timeAddedLastChanged")
	public Instant auditStamp;

}