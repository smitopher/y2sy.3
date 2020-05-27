package com.cmfirsttech.y2.api.model.impl;

import java.math.BigDecimal;

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionPrototype;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2FunctionPrototype.class)
public class FunctionPrototype extends AbstractModel {
	
	public String functionType;

	public Integer functionPrototype;

	public String objectType;

	public String objectAttribute;

	public String messageTypeAbbrev;

	public String subprogramInd;

	public String messageFunctionCode;

	public String text;

	public BigDecimal displaySeqNo;

	public String promptText;

	public String msgidActionFormat;

	public String msgidDftFunctionName;

	public String requiredBySynon_2;

	public String allowRestrictedMessages;

	public String allowMessageSource;

	public String allowDeviceSource;

	public String allowDeviceStruct;

	public String allowActionDiagram;

	public String allowSourceGeneration;

	public String allowDropRelation;

	public String allowVryParameters;

	public String allowDeviceDesign;

	public String allowSource;

	public String allowedFileAttributes;

	public String allowedAccpthTypes;

	public String functionCategories;

	public String checkAllowedAccpthPgm;

	public String generatorProgram;

	public String initiatorProgram;

	public String creatorProgram;

	public Integer creationSequence;

	public String conditionStub;

	public String typeSpecificOptions;

	public String overridePromptTextForMsgtyp;

	public String defaultPromptMsgidForMsgtyp;

	public String overridePromptTextForMsgtypabr;

	public String defaultPromptMsgidForMsgtypabr;

	public String overridePromptTextForPmttxt;

	public String defaultPromptMsgidForPmttxt;

	public String overridePromptTextForMsgfuncde;

	public String defaultPromptMsgidForMsgfuncde;

	public String overridePromptTextForTxt;

	public String defaultPromptMsgidForTxt;

	public String loadProcessingProgram;

}
