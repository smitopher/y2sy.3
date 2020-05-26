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

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2File;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.entity.impl.Y2Message;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.AbstractActionDiagramElement;
import com.cmfirsttech.y2.api.model.IActionDiagram;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

/**
 * Action Diagram DDS format @ACTION
 * 
 */
@Y2EntityClass(entityClass = Y2FunctionActionDiagram.class)
public class AdACTIONFormat extends AbstractActionDiagramElement {

	public String elementTitle;

	@DirectMapped(mapSource = "subChain1Sgt")
	public Integer parameterChain;
	
	@DirectMapped(mappingType = SKIP)
	public Map<Integer, IActionDiagram> parameters;

	@DirectMapped(mapSource = "subChain2Sgt")
	public Integer messageSurrogate;

	@DirectMapped(mappingType = SKIP)
	public String messageName;

	@DirectMapped(mapSource = "subChain3Sgt")
	public Integer receivingFile;

	@DirectMapped(mappingType = SKIP)
	public String fileName;

	@DirectMapped(mapSource = "deviceStructureLeafSgt")
	public Integer userExpressionChain;

	@DirectMapped(mappingType = SKIP)
	public Map<Integer, AdUSREXPFormat> userExpression;

	public String expressionToken;

	public Integer tokenSubIdentifier;

	public String userDefined;

	public String systemGenerated;

	public String sbmjobAction;

	public String sbmjobOverride;

	public Integer sbmjobOverrideSeclvl;

	@DirectMapped(mappingType = MappingType.INSTANT, instantDateSource = "dateAddedLastChanged", instantTimeSource = "timeAddedLastChanged")
	public Instant auditStamp;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		if (receivingFile != null) {
			Y2File file = Y2File.findById(receivingFile);
			fileName = file.fileName.stripTrailing();
		}
		if (functionSurrogate != null) {
			Y2Message message = Y2Message.findById(functionSurrogate);
			messageName = message.messageName.stripTrailing();
		}
	}
	

}