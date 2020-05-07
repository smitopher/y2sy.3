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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * MDL File                      Physical file
 * 
 */
@Entity
@Table(name="YFILDTARFP")
public class Y2File extends AbstractEntity {


	@Id
	@Column(name="@@FIL")
	public Integer fileSurrogate;

	@Column(name="OBJTYP", length=3)
	public String objectTypeForObjectType;

	@Column(name="OBJATR", length=3)
	public String objectAttributeForObjectAttribute;

	@Column(name="FIL", length=25)
	public String fileName;

	@Column(name="DOCSEQ", precision=7, scale=2)
	public BigDecimal documentationSequence;

	@Column(name="SRCMBR", length=10)
	public String sourceMember;

	@Column(name="RPGPFX", length=2)
	public String rpgFormatPrefix;

	@Column(name="OLDDBS", length=1)
	public String pre_existantDatabase;

	@Column(name="REQSYN", length=1)
	public String requiredBySynon_2;

	@Column(name="@@NOTFND")
	public Integer recordNotFoundMsgSgt;

	@Column(name="@@RCDEXS")
	public Integer recordExistsMsgSgt;

	@Column(name="FILTXOV", length=1)
	public String overridePromptTextForFil;

	@Column(name="FILMSID", length=7)
	public String defaultPromptMsgidForFil;

	@Column(name="DSTFIL", length=1)
	public String distributedFile;

	@Column(name="EXPDTE")
	public Integer exportDate;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "y2File")
	public Set<Y2AccessPath> accessPaths; 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "y2File")
	public Set<Y2EntityRelations> relations;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "fileSurrogate")
	public Set<Y2FunctionDetails> functions;

}