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

import java.math.BigDecimal;

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionDetails;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
 
@Y2EntityClass(entityClass = Y2FunctionDetails.class)
public class FunctionDetails extends AbstractModel{

	public Integer fileSurrogate;

//	@Column(name="FILREQSYN", length=1)
	public String fileRequiredBySynon_2;

	@DirectMapped(mapSource = "messageName")
	public String functionName;

	@DirectMapped(mapSource = "messageSurrogate")
	public Integer functionSurrogate;

	public Integer fieldSurrogate;

	public String objectType;

	public String nptObjectAttribute;

	public String os2ObjectAttribute;

	public Integer accessPathSurrogate;

	public Integer messagePrototypeSgt;

	public String sourceMember;

	public String text;

	public BigDecimal documentationSequence;

//	@Column(name="RSTMSGIND", length=1)
	public String restricetedMessageInd;

//	@Column(name="CNDSTB", length=8)
	public String conditionStub;

//	@Column(name="TYPOPT", length=200)
	public String typeSpecificOptions;

//	@Column(name="REQSYN", length=1)
	public String fncRequiredBySynon_2;

//	@Column(name="DFTMSGIND", length=1)
	public String defaultMessageInd;

//	@Column(name="@@SBMOVR")
	public Integer sbmjobOverrideSgt;
}
