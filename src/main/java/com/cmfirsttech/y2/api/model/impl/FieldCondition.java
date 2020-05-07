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

import com.cmfirsttech.y2.api.entity.impl.Y2ConditionDetails;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2ConditionDetails.class)
public class FieldCondition extends AbstractModel {

	public Integer conditionSurrogate;

	public String objectTypeForObjectType;

	public String objectAttributeForObjectAttribute;

	public String condition;

	public String logicalOperator;

	public Integer objectSurrogateForObject1;

	public String relationalOperator;

	public Integer objectSurrogateForObject2;

	public String overridePromptTextForCnd;

	public String defaultPromptMsgidForCnd;

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
