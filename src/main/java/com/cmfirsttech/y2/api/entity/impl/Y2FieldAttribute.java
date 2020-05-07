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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;



/**
 * MDL Field attribute           Physical file
 * 
 */
@Entity
@Table(name="YFLDATRRFP")
public class Y2FieldAttribute extends AbstractEntity {


	@Id
	@Column(name="OBJATR", length = 3)
	public String fieldAttribute;

	@Column(name="OBJTYP", length=3)
	public String objectType;

	@Column(name="TXT", length=50)
	public String typeDescription;

	@Column(name="REFFLDIND", length=1)
	public String refFieldInd;

	@Column(name="EXTDTATYP", length=1)
	public String externalDataType;

	@Column(name="EXTDEC")
	public Integer externalDecimalPlaces;

	@Column(name="EXTINT")
	public Integer externalIntegers;

	@Column(name="EXTLEN")
	public Integer externalLength;

	@Column(name="OVREXTLEN")
	public Integer ovrExternalLength;

	@Column(name="INTDTATYP", length=1)
	public String internalDataType;

	@Column(name="INTDEC")
	public Integer internalDecimalPlaces;

	@Column(name="INTINT")
	public Integer internalIntegers;

	@Column(name="INTLEN")
	public Integer internalLength;

	@Column(name="INTLENMAP", length=1)
	public String int_extLengthMapOption;

	@Column(name="ALWOCR", length=1)
	public String allowOccurs;

	@Column(name="ALWVALMAP", length=1)
	public String allowValueMapping;

	@Column(name="VALMAP", length=1)
	public String valueMappingInd;

	@Column(name="@@EXTINT")
	public Integer ext_intMappingFunction;

	@Column(name="@@INTEXT")
	public Integer int_extMappingFunction;

	@Column(name="ALWVALLST", length=1)
	public String allowValuesList;

	@Column(name="ALWLWRCAS", length=1)
	public String allowLowerCase;

	@Column(name="KYBSHFLST", length=10)
	public String keyboardShiftList;

	@Column(name="KYBSHF", length=1)
	public String keyboardShift;

	@Column(name="MNCCDE", length=3)
	public String mnemonicCode;

	@Column(name="ALWBLK", length=1)
	public String allowBlankZero;

	@Column(name="FLDEXT", length=1)
	// (r=raz,b=rab,y=fe)
	public String fieldExit;

	@Column(name="MODCHK", length=2)
	public String applyModulus10_11Check;

	@Column(name="CHKVNM", length=1)
	public String checkValidName;

	@Column(name="MNDFLL", length=1)
	public String mandatoryFill;

	@Column(name="RPTEDT", length=1)
	public String reportEditCode;

	@Column(name="SCREDTINP", length=1)
	public String screenInputEditCode;

	@Column(name="SCREDTOUT", length=1)
	public String screenOutputEditCode;

	@Column(name="ERRATR", length=1)
	public String displayAttr_Error;

	@Column(name="INPATR", length=1)
	public String displayAttr_Input;

	@Column(name="OUTATR", length=1)
	public String displayAttr_Output;

	@Column(name="ERRATRCLR", length=1)
	public String colourAttr_Error;

	@Column(name="INPATRCLR", length=1)
	public String colourAttr_Input;

	@Column(name="OUTATRCLR", length=1)
	public String colourAttr_Output;

	@Column(name="EXTLENOPT", length=1)
	public String externalLengthIoOption;

	@Column(name="INTLENOPT", length=1)
	public String internalLengthIoOption;

	@Column(name="INTDTPOPT", length=1)
	public String intDataTypeIoOption;

	@Column(name="KYBSHFOPT", length=1)
	public String keyboardShiftIoOption;

	@Column(name="LWRCASOPT", length=1)
	public String lowercaseIoOption;

	@Column(name="CHKVNMOPT", length=1)
	public String checkCpfNameIoOption;

	@Column(name="MNDFLLOPT", length=1)
	public String mandatoryFillIoOption;

	@Column(name="MODCHKOPT", length=1)
	public String modulusCheckIoOption;

	@Column(name="FIXDFN", length=1)
	public String fixedDefinitionInd;

	@Column(name="TXTMSID", length=7)
	public String defaultPromptMsgidForTxt;

	@Column(name="MNCCDEMSID", length=7)
	public String defaultPromptMsgidForMnccde;

	@Column(name="RHSHDG", length=40)
	public String text;

	@Column(name="RHSHDGMSID", length=7)
	public String textMsgid;

	@Column(name="RHSHDGTXOV", length=1)
	public String textOvr;

	@Column(name="ENTCLRATR", length=1)
	public String colorAtr_Entry;

	@Column(name="ENTATR", length=1)
	public String displayAtr_Entry;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fieldAttribute")
	public Set<Y2FieldDomain> domainFields;

}