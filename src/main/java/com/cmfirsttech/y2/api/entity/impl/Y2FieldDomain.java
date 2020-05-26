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

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * MDL Field domain              Physical file
 * 
 */
@Entity
@Table(name="YFLDDOMRFP")
public class Y2FieldDomain extends AbstractEntity {


	@Id
	@Column(name="@@DOM")
	public Integer domainSurrogate;

//	@Column(name="OBJATR", length=3)
//	public String fieldAttribute;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OBJATR", nullable = false)
	public Y2FieldAttribute fieldAttribute; 
	
	@Column(name="OBJTYP", length=3)
	public String objectType;

	@Column(name="REQSYN", length=1)
	public String requiredBySynon_2;

	@Column(name="RELUSG", length=3)
	public String relationUsage;

	@Column(name="VALMAP", length=1)
	public String valueMappingInd;

	@Column(name="ALWUSRMOD", length=1)
	public String userModifiableField;

	@Column(name="RPTEDT", length=1)
	public String reportEditCode;

	@Column(name="SCREDTINP", length=1)
	public String screenInputEditCode;

	@Column(name="SCREDTOUT", length=1)
	public String screenOutputEditCode;

	@Column(name="ALWLWRCAS", length=1)
	public String allowLowerCase;

	@Column(name="MODCHK", length=2)
	public String applyModulus10_11Check;

	@Column(name="CHKVNM", length=1)
	public String checkValidName;

	@Column(name="EXTDEC")
	public Integer externalDecimalPlaces;

	@Column(name="EXTINT")
	public Integer externalIntegers;

	@Column(name="EXTLEN")
	public Integer externalLength;

	@Column(name="INTDTATYP", length=1)
	public String internalDataType;

	@Column(name="INTDEC")
	public Integer internalDecimalPlaces;

	@Column(name="INTINT")
	public Integer internalIntegers;

	@Column(name="INTLEN")
	public Integer internalLength;

	@Column(name="KYBSHF", length=1)
	public String keyboardShift;

	@Column(name="MNDFLL", length=1)
	public String mandatoryFill;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mdlFieldDomain")
	public Set<Y2Field> mdlFields;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mdlFieldDomain")
	public Set<Y2ConditionDetails1JL> mdlConditions;
	
}