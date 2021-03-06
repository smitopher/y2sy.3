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

import com.cmfirsttech.y2.api.entity.impl.Y2ConditionDetails1JL;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2ConditionDetails1JL.class)
public class FieldCondition extends AbstractModel {

	public Integer conditionSurrogate;

	public String objectType;

	public String objectAttribute;

	public String condition;

	public Integer mdlFieldDomain;

	public String logicalOperatorAndOr;

	public Integer object1Sgt;

	public String internalValue;

	public String relOperator;

	public Integer object2Sgt;

	public String externalValue;

	public String choiceMnemonic;

	public String typePrompt;

	public Integer fileSurrogatePrompt;

	public Integer messageSurrogatePrompt;

	public String typeGuiPrompt;

	public Integer fileSurrogateGuiPrompt;

	public Integer messageSurrogateGuiPrompt;

	public String nptAppearance;

	public Integer nptHeight;

	public Integer nptWidth;

	public String overridePromptTextForMne;

	public String defaultPromptMsgidForMne;

	public Integer guiDataSurrogate;

}
