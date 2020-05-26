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
package com.cmfirsttech.y2.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * MDL Message action           Master index
 * 
 */
@Entity
@Table(name="YMSGACT00L")
public class Y2MessageActionMasterIndex extends AbstractEntity {


	@Id
	@Column(name="@@MSG")
	public Integer messageSurrogateForMessagEDetails;

	@Column(name="@@ELM")
	public Integer elementNo;

	@Column(name="ELMTYP", length=3)
	public String elementType;

	@Column(name="ELMTTL", length=132)
	public String elementTitle;

	@Column(name="ELMATR", length=3)
	public String elementAttribute;

	@Column(name="@@NXTELM")
	public Integer nextElementNo;

	@Column(name="@@SUB1")
	public Integer subChain1Sgt;

	@Column(name="@@SUB2")
	public Integer subChain2Sgt;

	@Column(name="@@SUB3")
	public Integer subChain3Sgt;

	@Column(name="HIDE", length=1)
	public String hideElement;

	@Column(name="ALWELMDLT", length=1)
	public String allowElementDeletion;

	@Column(name="ALWELMEDT", length=1)
	public String allowElementEdit;

	@Column(name="ALWELMINS", length=1)
	public String allowElementInsertion;

	@Column(name="USRDEF", length=1)
	public String userDefined;

	@Column(name="SYSGEN", length=1)
	public String systemGenerated;

	@Column(name="CNDSTB", length=8)
	public String conditionStub;

	@Column(name="SBMJOB", length=1)
	public String sbmjobAction;

	@Column(name="SBMJOBOVR", length=1)
	public String sbmjobOverride;

	@Column(name="@@SBMOVR")
	public Integer sbmjobOverrideSgt;

	@Column(name="CHGDAT")
	public Integer dateAddedLastChanged;

	@Column(name="CHGTIM")
	public Integer timeAddedLastChanged;

	@Column(name="@@LVLFLD")
	public Integer levelFieldSurrogate;

	@Column(name="FMTTYP", length=3)
	public String formatType;

	@Column(name="@@LEFELM")
	public Integer deviceStructureLeafSgt;

	@Column(name="TOKEN", length=5)
	public String expressionToken;

	@Column(name="TKNSUBID")
	public Integer tokenSubdentifier;

	@Column(name="STATUS", length=1)
	public String blockStatus;

}