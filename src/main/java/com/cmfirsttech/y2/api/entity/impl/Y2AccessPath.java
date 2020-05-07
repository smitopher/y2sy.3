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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * MDL Access path               Physical file
 * 
 */
@Entity
@Table(name="YACPDTARFP")
public class Y2AccessPath extends AbstractEntity {


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "@@FIL", nullable = false)
	public Y2File y2File;

	@Column(name="ACP", length=25)
	public String accessPathName;

	@Column(name="ACPTYP", length=3)
	public String accessPathType;

	@Id
	@Column(name="@@ACP")
	public Integer accessPathSurrogate;

	@Column(name="OBJTYP", length=3)
	public String objectTypeForObjectType;

	@Column(name="OBJATR", length=3)
	public String objectAttributeForObjectAttribute;

	@Column(name="SRCMBR", length=10)
	public String sourceMember;

	@Column(name="@@MSG")
	public Integer messageSurrogateForMessagEDetails;

	@Column(name="ACPTXT", length=50)
	public String accessPathText;

	@Column(name="ALTCOLTBL", length=10)
	public String altcolTable;

	@Column(name="MNTSTS", length=1)
	public String maintenanceStatus;

	@Column(name="UNQKEY", length=1)
	public String uniqueKeyInd;

	@Column(name="DUPKEYSEQ", length=1)
	public String duplicateKeySequence;

	@Column(name="ALWSELOMT", length=1)
	public String allowSelect_omit;

	@Column(name="DFTACPIND", length=1)
	public String defaultAccessPathInd;

	@Column(name="AUTADDIND", length=1)
	public String automaticAdditionInd;

	@Column(name="ACPTXOV", length=1)
	public String overridePromptTextForAcp;

	@Column(name="ACPMSID", length=7)
	public String defaultPromptMsgidForAcp;

	@Column(name="ACPTXTTXOV", length=1)
	public String overridePromptTextForAcptxt;

	@Column(name="ACPTXTMSID", length=7)
	public String defaultPromptMsgidForAcptxt;

	@Column(name="DBFGEN", length=1)
	public String databaseGenerationFlag;

	@Column(name="DBFACCMTH", length=1)
	public String dataAccessMethod;

	@Column(name="@@DBFMSG")
	public Integer dataAccessFunctionSgt;

	@Column(name="@@DBFFIL")
	public Integer dataAccessFileSgt;

	@Column(name="SHRODP", length=1)
	public String sharedOpenDataPaths;

}