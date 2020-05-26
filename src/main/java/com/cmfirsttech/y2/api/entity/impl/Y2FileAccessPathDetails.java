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
 * MDL File,access path details.
 * 
 */
@Entity
@Table(name="YFILDTA1JL")
public class Y2FileAccessPathDetails extends AbstractEntity {


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "@@FIL", nullable = false)
	public Y2File y2File;

	@Column(name="OBJTYP", length=3)
	public String objectTypeForObjectType;

	@Column(name="FILATR", length=3)
	public String fileObjectAttribute;

	@Column(name="FIL", length=25)
	public String fileName;

	@Column(name="DOCSEQ", precision=7, scale=2)
	public BigDecimal documentationSequence;

	@Column(name="FILSRCMBR", length=10)
	public String fileSourceMember;

	@Column(name="RPGPFX", length=2)
	public String rpgFormatPrefix;

	@Column(name="OLDDBS", length=1)
	public String preExistentDatabase;

	@Column(name="REQSYN", length=1)
	public String requiredBySynon_2;

	@Column(name="@@NOTFND")
	public Integer recordNotFoundMsgSgt;

	@Column(name="@@RCDEXS")
	public Integer recordExistsMsgSgt;

	@Column(name="DSTFIL", length=1)
	public String distributedFile;

	@Column(name="ACP", length=25)
	public String accessPathName;

	@Column(name="ACPTYP", length=3)
	public String accessPathType;

	@Id
	@Column(name="@@ACP")
	public Integer accessPathSurrogate;

	@Column(name="OBJATR", length=3)
	public String objectAttribute;

	@Column(name="SRCMBR", length=10)
	public String sourceMember;

	@Column(name="@@MSG")
	public Integer messageSurrogate;

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

	@Column(name="DBFGEN", length=1)
	public String databaseGenerationFlag;

	@Column(name="DBFACCMTH", length=1)
	public String dataAccessMethod;

	@Column(name="@@DBFMSG")
	public Integer dataAccessFunctionSgt;

	@Column(name="@@DBFFIL")
	public Integer dataAccessFileSgt;

}