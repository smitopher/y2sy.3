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

import org.hibernate.annotations.Immutable;

import com.cmfirsttech.y2.api.entity.AbstractEntity;

@Entity
@Table(name = "YRELDTA0JL")
@Immutable
public class Y2EntityRelations extends AbstractEntity {

	@Id
	@Column(name = "@@REL")
	public Integer relationSurrogate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "@@OBJ", nullable = false)
	public Y2File y2File;
	
	@Column(name = "OBJ", length = 25)
	public String object;
	
	@Column(name = "OBJTYP", length = 3)
	public 	String objectType;
	
	@Column(name = "RELLVL")
	public Integer relationLevel;
	
	@Column(name = "RELSEQ")
	public Integer relationSequence;
	
	@Column(name = "RELCDE", length = 3)
	public String relationType;
	
	@Column(name = "RELNME", length = 12)
	public String relationName;
	
	@Column(name = "ALWCHG", length = 1)
	public Character allowChange;
	
	@Column(name = "@@REF")
	public Integer referencedObjectSurrogate;
	
	@Column(name = "REFFIL", length = 25)
	public String referencedFileName;
	
	@Column(name = "REFFILTYP", length = 3)
	public String referencedFileObjectType;
	
	@Column(name = "REFFLD", length = 25)
	public String referencedFieldName;
	
	@Column(name = "REFFLDTYP", length = 3)
	public String referencedFieldObjectType;
	
	@Column(name = "FOR", length = 25)
	public String forName;
	
	@Column(name = "@@SHR")
	public Integer sharingObjectSurrogate;
	
	@Column(name = "SHR", length = 25)
	public String sharingFileName;
	
	@Column(name = "ALWDLT", length = 1)
	public Character allowDelete;
	
	@Column(name = "CHGDAT")
	public Integer dateChanged;
	
	@Column(name = "CHGTIM")
	public Integer timeChanged;
	
	@Column(name = "@@MINCRDN")
	public Integer minCardinality;
	
	@Column(name = "@@MAXCRDN")
	public Integer maxCardinality;
	
	@Column(name = "OPTIONAL")
	public Character optionality;
	
	@Column(name = "@@SELRCD")
	public Integer selectRecordSurrogate;

	@Column(name = "@@NOTFND")
	public Integer recordNotFoundMsgSgt;

	@Column(name = "@@RELFUN1")
	public Integer relationFunctionMsgSgt1;

	@Column(name = "@@RELFUN2")
	public Integer relationFunctionMsgSgt2;

	@Column(name = "@@RELFUN3")
	public Integer relationFunctionMsgSgt3;

	@Column(name = "@@RELFUN4")
	public Integer relationFunctionMsgSgt4;
	
	@Column(name = "@@RELFUN5")
	public Integer relationFunctionMsgSgt5;
}
