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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * Function details.
 * 
 */
@Entity
@Table(name="YMSGDTA1JL")
public class Y2FunctionDetails extends AbstractEntity {


	@Column(name="@@FIL")
	public Integer fileSurrogate;

	@Column(name="FIL", length=25)
	public String fileName;

	@Column(name="FILREQSYN", length=1)
	public String fileRequiredBySynon_2;

	@Column(name="MSG", length=25)
	public String messageName;

	@Id
	@Column(name="@@MSG")
	public Integer messageSurrogate;

	@Column(name="@@FLD")
	public Integer fieldSurrogate;

	@Column(name="OBJTYP", length=3)
	public String objectType;

	@Column(name="OBJATR", length=3)
	public String nptObjectAttribute;

	@Column(name="OS2ATR", length=3)
	public String os2ObjectAttribute;

	@Column(name="@@ACP")
	public Integer accessPathSurrogate;

	@Column(name="@@MSG_P")
	public Integer messagePrototypeSgt;

	@Column(name="SRCMBR", length=10)
	public String sourceMember;

	@Column(name="TXT", length=50)
	public String text;

	@Column(name="DOCSEQ", precision=7, scale=2)
	public BigDecimal documentationSequence;

	@Column(name="RSTMSGIND", length=1)
	public String restricetedMessageInd;

	@Column(name="CNDSTB", length=8)
	public String conditionStub;

	@Column(name="TYPOPT", length=200)
	public String typeSpecificOptions;

	@Column(name="REQSYN", length=1)
	public String fncRequiredBySynon_2;

	@Column(name="DFTMSGIND", length=1)
	public String defaultMessageInd;

	@Column(name="@@SBMOVR")
	public Integer sbmjobOverrideSgt;

}