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
 * MDL Entry                     Physical file
 * 
 */
@Entity
@Table(name="YENTDTARFP")
public class Y2ModelEntry extends AbstractEntity {


	@Column(name="@@FIL")
	public Integer fileSurrogate;

	@Column(name="@@FLD")
	public Integer fieldSurrogate;

	@Id
	@Column(name="@@ENT")
	public Integer modelEntrySurrogate;

	@Column(name="@@RNMFLD")
	public Integer renamedFromFieldSgt;

	@Column(name="ENTSEQ", precision=5, scale=2)
	public BigDecimal entrySequenceNo;

	@Column(name="EXPFLG", length=1)
	public String expandedFlag;

	@Column(name="EXPSEQ")
	public Integer expansionSequenceNo;

	@Column(name="@@RELDEF")
	public Integer definingRelationSgt;

//	@Column(name="@@RELDSG")
//	public Integer designatingRelationSgt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "@@RELDSG", nullable = false)
	public Y2EntityRelations designatingRelation;

	@Column(name="@@REFENT")
	public Integer refEntrySurrogate;

	@Column(name="ENTTYP", length=1)
	public String entryType;

	@Column(name="@@REFFIL")
	public Integer refFileSurrogate;

	@Column(name="KEYNBR")
	public Integer keyNumber;

	@Column(name="DUPFLDIND", length=1)
	public String duplicateItemInd;

	@Column(name="FRCFLDIND", length=1)
	public String forcedItemInd;

	@Column(name="RNMIND", length=1)
	public String renamedItemInd;

	@Column(name="FGNKEYIND", length=1)
	public String foreignKeyInd;

	@Column(name="@@AUXENT")
	public Integer auxillaryEntrySurrogate;

	@Column(name="@@RDRENT")
	public Integer redirectingEntrySgt;

	@Column(name="RDRTYPE", length=1)
	public String redirectionType;
	
}