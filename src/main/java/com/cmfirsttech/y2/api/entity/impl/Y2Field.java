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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * The persistent class for the CFLDDTARFP database table.
 * 
 */
@Entity
@Table(name="YFLDDTARFP")
public class Y2Field extends AbstractEntity {
	
	@Id
	@Column(name="@@FLD")
	public Integer fieldSurrogate;

	@Column(name="objtyp", length=3)
	public String objectType;

	@Column(name="objatr", length=3)
	public String objectAttribute;

	@Column(name="fld", length=25)
	public String fieldName;

	@Column(name="docseq", precision=7, scale=2)
	public BigDecimal documentationSequence;

	@Column(name="txt", length=50)
	public String fieldText;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "@@DOM", nullable = false)
	public Y2FieldDomain mdlFieldDomain;

	@Column(name="@@GRPFLD")
	public Integer groupFunctionsFieldSgt;

	@Column(name="@@CND")
	public Integer conditionSurrogate;

	@Column(name="@@DFT")
	public Integer defaultConstantSgt;

	@Column(name="@@VALRQD")
	public Integer valueRequiredMsgSgt;

	@Column(name="ddsvnm", length=10)
	public String ddsName;

	/**
	 * For existing database
	 */
	@Column(name="intddsvnm", length=10)
	public String internalDdsName;

	@Column(name="oldddsvnm", length=10)
	public String oldDdsName;

	@Column(name="colhdg1", length=20)
	public String columnHeading1;

	@Column(name="colhdg2", length=20)
	public String columnHeading2;

	@Column(name="colhdg3", length=20)
	public String columnHeading3;

	@Column(name="lhshdg", length=40)
	public String lhsHeading;

	@Column(name="rhshdg", length=40)
	public String rhsHeading;

	@Column(name="ocrcnt")
	public Integer occursCount;

	@Column(name="alwblk", length=1)
	public String allowBlankZero;

	@Column(name="fldtxov", length=1)
	public String overridePromptTextForField;

	@Column(name="fldmsid", length=7)
	public String defaultPromptMsgidForField;

	@Column(name="txttxov", length=1)
	public String overridePromptTextForTXT;

	@Column(name="txtmsid", length=7)
	public String defaultPromptMsgidForTXT;

	@Column(name="colhd1txov", length=1)
	public String overridePromptTextForCOLHDG1;

	@Column(name="colhd1msid", length=7)
	public String defaultPromptMsgidForCOLHDG1;

	@Column(name="colhd2txov", length=1)
	public String overridePromptTextForCOLHDG2;

	@Column(name="colhd2msid", length=7)
	public String defaultPromptMsgidForCOLHDG2;

	@Column(name="colhd3txov", length=1)
	public String overridePromptTextForCOLHDG3;

	@Column(name="colhd3msid", length=7)
	public String defaultPromptMsgidForCOLHDG3;

	@Column(name="lhshdgtxov", length=1)
	public String overridePromptTextForLHSHDG;

	@Column(name="lhshdgmsid", length=7)
	public String defaultPromptMsgidForLHSHDG;

	@Column(name="rhshdgtxov", length=1)
	public String overridePromptTextForRHSHDG;

	@Column(name="rhshdgmsid", length=7)
	public String defaultPromptMsgidForRHSHDG;

	@Column(name="colminlen")
	public Integer columnMinimumLength;

	@Column(name="rhsminlen")
	public Integer rightHandMinimumLength;

	@Column(name="lhsminlen")
	public Integer lightHandMinimumLength;

	@Column(name="TYPE_I", length=3)
	public String typeInitialize;

	@Column(name="@@FILE_I")
	public Integer fileSurrogateInit;

	@Column(name="@@MSG_I")
	public Integer messageSurrogateInit;

	@Column(name="TYPE_P", length=3)
	public String typePrompt;

	@Column(name="@@FILE_P")
	public Integer fileSurrogatePrompt;

	@Column(name="@@MSG_PR")
	public Integer messageSurrogatePrompt;

	@Column(name="TYPE_V", length=3)
	public String typeValidate;

	@Column(name="@@FILE_V")
	public Integer fileSurrogateValidate;

	@Column(name="@@MSG_V")
	public Integer messageSurrogateValidate;

	@Column(name="TYPE_GP", length=3)
	public String typeGuiPrompt;

	@Column(name="@@FILE_GP")
	public Integer fileSurrogateGuiPrompt;

	@Column(name="@@MSG_GP")
	public Integer messageSurrogateGuiPrompt;

	@Column(name="@@GUI")
	public Integer guiDataSurrogate;

	@Column(name="scrmask", length=1)
	public String screenInputMask;

	@Column(name="nptapp", length=3)
	public String ntpAppearance;

	@Column(name="nptht")
	public Integer nptHeight;

	@Column(name="nptwd")
	public Integer nptWidth;

}
