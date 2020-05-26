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
package com.cmfirsttech.y2.api.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * MDL Condition details - (@@OWN|OBJATR|CND)
 * 
 */
@Entity
@Table(name="YCNDDTA1JL")
public class Y2ConditionDetails1JL extends AbstractEntity {


	@Id
	@Column(name="@@CND")
	public Integer conditionSurrogate;

	@Column(name="OBJTYP", length=3)
	public String objectType;

	@Column(name="OBJATR", length=3)
	public String objectAttribute;

	@Column(name="CND", length=25)
	public String condition;

	@Column(name="@@OWN")
	public Integer mdlFieldDomain;

	@Column(name="LGLOPR", length=3)
	public String logicalOperatorAndOr;

	@Column(name="@@OBJ1")
	public Integer object1Sgt;

	@Column(name="INTVAL", length=25)
	public String internalValue;

	@Column(name="RELOPR", length=2)
	public String relOperator;

	@Column(name="@@OBJ2")
	public Integer object2Sgt;

	@Column(name="EXTVAL", length=25)
	public String externalValue;

	@Column(name="MNEMON", length=1)
	public String choiceMnemonic;

	@Column(name="TYPE_P", length=3)
	public String typePrompt;

	@Column(name="@@FILE_P")
	public Integer fileSurrogatePrompt;

	@Column(name="@@MSG_PR")
	public Integer messageSurrogatePrompt;

	@Column(name="TYPE_GP", length=3)
	public String typeGuiPrompt;

	@Column(name="@@FILE_GP")
	public Integer fileSurrogateGuiPrompt;

	@Column(name="@@MSG_GP")
	public Integer messageSurrogateGuiPrompt;

	@Column(name="NPTAPP", length=3)
	public String nptAppearance;

	@Column(name="NPTHT")
	public Integer nptHeight;

	@Column(name="NPTWD")
	public Integer nptWidth;

	@Column(name="MNETXOV", length=1)
	public String overridePromptTextForMne;

	@Column(name="MNEMSID", length=7)
	public String defaultPromptMsgidForMne;

	@Column(name="@@GUI")
	public Integer guiDataSurrogate;

}