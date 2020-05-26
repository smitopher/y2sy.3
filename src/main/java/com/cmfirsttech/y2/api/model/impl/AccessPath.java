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

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2AccessPath;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
 
@Y2EntityClass(entityClass = Y2AccessPath.class)
public class AccessPath extends AbstractModel {	

	@DirectMapped(mappingType = SKIP)
	public Integer y2File;

	public String accessPathName;

	public String accessPathType;

	public Integer accessPathSurrogate;

	public String objectTypeForObjectType;

	public String objectAttributeForObjectAttribute;

	public String sourceMember;

	public Integer messageSurrogateForMessagEDetails;

	public String accessPathText;

	public String altcolTable;

	public String maintenanceStatus;

	public String uniqueKeyInd;

	public String duplicateKeySequence;

	public String allowSelect_omit;

	public String defaultAccessPathInd;

	public String automaticAdditionInd;

	public String overridePromptTextForAcp;

	public String defaultPromptMsgidForAcp;

	public String overridePromptTextForAcptxt;

	public String defaultPromptMsgidForAcptxt;

	public String databaseGenerationFlag;

	public String dataAccessMethod;

	public Integer dataAccessFunctionSgt;

	public Integer dataAccessFileSgt;

	public String sharedOpenDataPaths;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		// TODO Auto-generated method stub
		super.customMapping(entity, mapper);
		Y2AccessPath ap = (Y2AccessPath)entity;
		y2File = ap.y2File.fileSurrogate;
	}

}