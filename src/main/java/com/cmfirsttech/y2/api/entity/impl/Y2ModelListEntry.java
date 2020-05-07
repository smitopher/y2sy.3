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
//import javax.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;

@Entity
@Table(name = "Y2APIMDLLST")
public class Y2ModelListEntry extends AbstractEntity {

	@Column(name = "OBJSEL", length = 1)
	public String objectSelected;

	@Column(name = "IGNENT", length = 1)
	public String ignoreEntry;
	
	@Id
	@Column(name = "@@OBJ")
	public Integer objectSurrogate;
	
	@Column(name = "OBJNME", length = 25)
	public String object;
	
	@Column(name = "OBJTYP", length = 3)
	public String objectType;
	
	@Column(name = "OBJATR", length = 3)
	public String objectAttribute;
	
	@Column(name = "FUNTYP", length = 10)
	public String functionType;
	
	@Column(name = "@@OWN")
	public Integer ownerSurrogate;
	
	@Column(name = "OBJOWN", length = 25)
	public String owner;
	
	@Column(name = "IMPNME", length = 10)
	public String implName;
	
	@Column(name = "CRTDTE")
	public Integer creationDate;
	
	@Column(name = "CRTTIM")
	public Integer creationTime;
	
	@Column(name = "CHGDTE")
	public Integer changeDate;
	
	@Column(name = "CHGTIM")
	public Integer changeTime;
	
	@Column(name = "CHGTYP", length = 3)
	public String changeType;
	
	@Column(name = "CPYOBJ", length = 1)
	public String copyObject;
	
	@Column(name = "CPYRQD", length = 1)
	public String copyRequired;
	
	@Column(name = "CPYSTS", length = 1)
	public String copyStatus;
	
	@Column(name = "CPYNME", length = 25)
	public String copyMappingField;
	
	@Column(name = "@@CPY")
	public Integer copySurrogate;
	
	@Column(name = "TGTNME", length = 25)
	public String targetObjectName;
	
}
