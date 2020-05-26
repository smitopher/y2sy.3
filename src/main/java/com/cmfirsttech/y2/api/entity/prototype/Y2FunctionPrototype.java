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
package com.cmfirsttech.y2.api.entity.prototype;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * LDO Message prototype         Physical file
 * 
 */
@Entity
@Table(schema = "Y2SY", name="YMSGDTAPDP")
public class Y2FunctionPrototype extends AbstractEntity {

	@Column(name="MSGTYP", length=25)
	public String messageType;

	@Id
	@Column(name="@@MSG_P")
	public Integer functionPrototype;

	@Column(name="OBJTYP", length=3)
	public String objectTypeForObjectType;

	@Column(name="OBJATR", length=3)
	public String objectAttributeForObjectAttribute;

	@Column(name="MSGTYPABR", length=10)
	public String messageTypeAbbrev;

	@Column(name="SUBPGMIND", length=1)
	public String subprogramInd;

	@Column(name="MSGFUNCDE", length=3)
	public String messageFunctionCode;

	@Column(name="TXT", length=50)
	public String text;

	@Column(name="DSPSEQ_P", precision=5, scale=2)
	public BigDecimal displaySeqNo;

	@Column(name="PMTTXT", length=25)
	public String promptText;

	@Column(name="ACTFMTVMG", length=7)
	public String msgidActionFormat;

	@Column(name="DFTFUNVMG", length=7)
	public String msgidDftFunctionName;

	@Column(name="REQSYN", length=1)
	public String requiredBySynon_2;

	@Column(name="ALWRSTMSG", length=1)
	public String allowRestrictedMessages;

	@Column(name="ALWMSGSRC", length=1)
	public String allowMessageSource;

	@Column(name="ALWDEVSRC", length=1)
	public String allowDeviceSource;

	@Column(name="ALWDEVSTR", length=1)
	public String allowDeviceStruct;

	@Column(name="ALWACTDIA", length=1)
	public String allowActionDiagram;

	@Column(name="ALWSRCGEN", length=1)
	public String allowSourceGeneration;

	@Column(name="ALWDRPREL", length=1)
	public String allowDropRelation;

	@Column(name="ALWVRYPAR", length=1)
	public String allowVryParameters;

	@Column(name="ALWDEVDSN", length=1)
	public String allowDeviceDesign;

	@Column(name="ALWHLLSRC", length=1)
	public String allowSource;

	@Column(name="FILATRLST", length=30)
	public String allowedFileAttributes;

	@Column(name="ACPTYPLST", length=30)
	public String allowedAccpthTypes;

	@Column(name="FUNCATLST", length=20)
	public String functionCategories;

	@Column(name="CHKACPPGM", length=10)
	public String checkAllowedAccpthPgm;

	@Column(name="GENPGM", length=10)
	public String generatorProgram;

	@Column(name="INTPGM", length=10)
	public String initiatorProgram;

	@Column(name="CRTPGM", length=10)
	public String creatorProgram;

	@Column(name="CRTSEQ")
	public Integer creationSequence;

	@Column(name="CNDSTB", length=8)
	public String conditionStub;

	@Column(name="TYPOPT", length=200)
	public String typeSpecificOptions;

	@Column(name="MSGTYPTXOV", length=1)
	public String overridePromptTextForMsgtyp;

	@Column(name="MSGTYPMSID", length=7)
	public String defaultPromptMsgidForMsgtyp;

	@Column(name="MSGABRTXOV", length=1)
	public String overridePromptTextForMsgtypabr;

	@Column(name="MSGABRMSID", length=7)
	public String defaultPromptMsgidForMsgtypabr;

	@Column(name="PMTTXTTXOV", length=1)
	public String overridePromptTextForPmttxt;

	@Column(name="PMTTXTMSID", length=7)
	public String defaultPromptMsgidForPmttxt;

	@Column(name="MSGFUCTXOV", length=1)
	public String overridePromptTextForMsgfuncde;

	@Column(name="MSGFUCMSID", length=7)
	public String defaultPromptMsgidForMsgfuncde;

	@Column(name="TXTTXOV", length=1)
	public String overridePromptTextForTxt;

	@Column(name="TXTMSID", length=7)
	public String defaultPromptMsgidForTxt;

	@Column(name="LODPGM", length=10)
	public String loadProcessingProgram;

}