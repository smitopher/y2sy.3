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

import java.math.BigDecimal;
import java.util.Optional;

import com.cmfirsttech.y2.api.constants.FieldType;
import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2Field;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2Field.class)
public class FieldDetails extends AbstractModel {
	
	public Integer fieldSurrogate;

	public ObjectType objectType;

	public String objectAttribute;

	@DirectMapped(mappingType = SKIP)
	public FieldType fieldType;

	public String fieldName;
	
	public BigDecimal documentationSequence;
	
	public String fieldText;
	
	public FieldDomain mdlFieldDomain;

	public String ddsName;
	
	public String internalDdsName;
	
	public String oldDdsName;
	
	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		Y2Field y2Field = (Y2Field)entity;
		Optional<FieldType> type = FieldType.fromJsonValue(y2Field.objectAttribute);
		fieldType = type.orElse(FieldType.CUSTOM);
	}
	

	public Integer groupFunctionsFieldSgt;

	public Integer conditionSurrogate;

	public Integer defaultConstantSgt;

	public Integer valueRequiredMsgSgt;

	public String columnHeading1;

	public String columnHeading2;

	public String columnHeading3;

	public String lhsHeading;

	public String rhsHeading;

	public Integer occursCount;

	public String allowBlankZero;

	public String overridePromptTextForField;

	public String defaultPromptMsgidForField;

	public String overridePromptTextForTXT;

	public String defaultPromptMsgidForTXT;

	public String overridePromptTextForCOLHDG1;

	public String defaultPromptMsgidForCOLHDG1;

	public String overridePromptTextForCOLHDG2;

	public String defaultPromptMsgidForCOLHDG2;

	public String overridePromptTextForCOLHDG3;

	public String defaultPromptMsgidForCOLHDG3;

	public String overridePromptTextForLHSHDG;

	public String defaultPromptMsgidForLHSHDG;

	public String overridePromptTextForRHSHDG;

	public String defaultPromptMsgidForRHSHDG;

	public Integer columnMinimumLength;

	public Integer rightHandMinimumLength;

	public Integer lightHandMinimumLength;

	public String typeInitialize;

	public Integer fileSurrogateInit;

	public Integer messageSurrogateInit;

	public String typePrompt;

	public Integer fileSurrogatePrompt;

	public Integer messageSurrogatePrompt;

	public String typeValidate;

	public Integer fileSurrogateValidate;

	public Integer messageSurrogateValidate;

	public String typeGuiPrompt;

	public Integer fileSurrogateGuiPrompt;

	public Integer messageSurrogateGuiPrompt;

	public Integer guiDataSurrogate;

	public String screenInputMask;

	public String ntpAppearance;

	public Integer nptHeight;

	public Integer nptWidth;

}
