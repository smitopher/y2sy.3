package com.cmfirsttech.y2.api.model.impl;

import static com.cmfirsttech.y2.api.mapper.MappingType.SKIP;

import java.util.Optional;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2FieldAttribute;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionDetails;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2FieldAttribute.class)
public class FieldAttribute extends AbstractModel{

	public String fieldAttribute;

	public String objectType;

	public String typeDescription;

//	@Column(name="REFFLDIND", length=1)
	public String refFieldInd;

//	@Column(name="EXTDTATYP", length=1)
	public String externalDataType;

	public Integer externalDecimalPlaces;

	public Integer externalIntegers;

	public Integer externalLength;

	public Integer ovrExternalLength;

// 	@Column(name="INTDTATYP", length=1)
	public String internalDataType;

	public Integer internalDecimalPlaces;

	public Integer internalIntegers;

	public Integer internalLength;

//	@Column(name="INTLENMAP", length=1)
	public String int_extLengthMapOption;

//	@Column(name="ALWOCR", length=1)
	public String allowOccurs;

//	@Column(name="ALWVALMAP", length=1)
	public String allowValueMapping;

//	@Column(name="VALMAP", length=1)
	public String valueMappingInd;

//	@Column(name="@@EXTINT")
	public Integer ext_intMappingFunction;
	@DirectMapped(mappingType = SKIP)
	public String ext_intMappingFunctionName;

//	@Column(name="@@INTEXT")
	public Integer int_extMappingFunction;
	@DirectMapped(mappingType = SKIP)
	public String int_extMappingFunctionName;

//	@Column(name="ALWVALLST", length=1)
	public String allowValuesList;

//	@Column(name="ALWLWRCAS", length=1)
	public String allowLowerCase;

//	@Column(name="KYBSHFLST", length=10)
	public String keyboardShiftList;

//	@Column(name="KYBSHF", length=1)
	public String keyboardShift;

//	@Column(name="MNCCDE", length=3)
	public String mnemonicCode;

//	@Column(name="ALWBLK", length=1)
	public String allowBlankZero;

//	@Column(name="FLDEXT", length=1)
	// (r=raz,b=rab,y=fe)
	public String fieldExit;

//	@Column(name="MODCHK", length=2)
	public String applyModulus10_11Check;

//	@Column(name="CHKVNM", length=1)
	public String checkValidName;

//	@Column(name="MNDFLL", length=1)
	public String mandatoryFill;

//	@Column(name="RPTEDT", length=1)
	public String reportEditCode;

//	@Column(name="SCREDTINP", length=1)
	public String screenInputEditCode;

//	@Column(name="SCREDTOUT", length=1)
	public String screenOutputEditCode;

//	@Column(name="ERRATR", length=1)
	public String displayAttr_Error;

//	@Column(name="INPATR", length=1)
	public String displayAttr_Input;

//	@Column(name="OUTATR", length=1)
	public String displayAttr_Output;

//	@Column(name="ERRATRCLR", length=1)
	public String colourAttr_Error;

//	@Column(name="INPATRCLR", length=1)
	public String colourAttr_Input;

//	@Column(name="OUTATRCLR", length=1)
	public String colourAttr_Output;

//	@Column(name="EXTLENOPT", length=1)
	public String externalLengthIoOption;

//	@Column(name="INTLENOPT", length=1)
	public String internalLengthIoOption;

//	@Column(name="INTDTPOPT", length=1)
	public String intDataTypeIoOption;

//	@Column(name="KYBSHFOPT", length=1)
	public String keyboardShiftIoOption;

//	@Column(name="LWRCASOPT", length=1)
	public String lowercaseIoOption;

//	@Column(name="CHKVNMOPT", length=1)
	public String checkCpfNameIoOption;

//	@Column(name="MNDFLLOPT", length=1)
	public String mandatoryFillIoOption;

//	@Column(name="MODCHKOPT", length=1)
	public String modulusCheckIoOption;

//	@Column(name="FIXDFN", length=1)
	public String fixedDefinitionInd;

//	@Column(name="TXTMSID", length=7)
	public String defaultPromptMsgidForTxt;

//	@Column(name="MNCCDEMSID", length=7)
	public String defaultPromptMsgidForMnccde;

//	@Column(name="RHSHDG", length=40)
	public String text;

//	@Column(name="RHSHDGMSID", length=7)
	public String textMsgid;

//	@Column(name="RHSHDGTXOV", length=1)
	public String textOvr;

//	@Column(name="ENTCLRATR", length=1)
	public String colorAtr_Entry;

//	@Column(name="ENTATR", length=1)
	public String displayAtr_Entry;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		if (ext_intMappingFunction != null) {
			Optional<Y2FunctionDetails> functionDetails = 
					Optional.ofNullable(Y2FunctionDetails.findById(ext_intMappingFunction));
			if (functionDetails.isPresent()) {
				String fileName = functionDetails.get().fileName.stripTrailing();
				String fncName = functionDetails.get().functionName.stripTrailing();
				ext_intMappingFunctionName = String.format("%1$s.%2$s", fileName, fncName);
			}
		}
		if (int_extMappingFunction != null) {
			Optional<Y2FunctionDetails> functionDetails = 
					Optional.ofNullable(Y2FunctionDetails.findById(ext_intMappingFunction));
			if (functionDetails.isPresent()) {
				String fileName = functionDetails.get().fileName.stripTrailing();
				String fncName = functionDetails.get().functionName.stripTrailing();
				int_extMappingFunctionName = String.format("%1$s.%2$s", fileName, fncName);
			}
		}
	}
}
