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
package com.cmfirsttech.y2.api.model.impl;

import static com.cmfirsttech.y2.api.mapper.MappingType.SKIP;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.transaction.Transactional;

import com.cmfirsttech.y2.api.constants.ADBlockAttribute;
import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2ConditionDetails;
import com.cmfirsttech.y2.api.entity.impl.Y2Field;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionDetails;
import com.cmfirsttech.y2.api.mapper.CoreMapper;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.model.ADElementFactory;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.IActionDiagram;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.panache.common.Sort;

@Y2EntityClass(entityClass = Y2FunctionDetails.class)
public class FunctionDetails extends AbstractModel {

	public Integer fileSurrogate;

	public String fileRequiredBySynon_2;

	public String functionName;

	public Integer functionSurrogate;

	public Integer fieldSurrogate;

	public String objectType;

	public String nptObjectAttribute;

	public String os2ObjectAttribute;

	public Integer accessPathSurrogate;

	public Integer messagePrototypeSgt;

	public String sourceMember;

	public String text;

	public BigDecimal documentationSequence;

	public String restricetedMessageInd;

	public String conditionStub;

	public String typeSpecificOptions;

	public String fncRequiredBySynon_2;

	public String defaultMessageInd;

	public Integer sbmjobOverrideSgt;
	

	@DirectMapped(mappingType = SKIP)
	public Map<Integer, IActionDiagram> actionDiagram;

	@DirectMapped(mappingType = SKIP)
	@JsonIgnore()
	public Map<Integer, IActionDiagram> actionDiagramTree;

	@DirectMapped(mappingType = SKIP)
	@JsonIgnore()
	public Map<Integer, IActionDiagram> actionDiagramRoots;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		actionDiagramTree = new LinkedHashMap<>();
		actionDiagramRoots = new LinkedHashMap<>();
		actionDiagram = new TreeMap<>();
		Y2FunctionDetails function = (Y2FunctionDetails) entity;
		mapAD(function, mapper);
		for (IActionDiagram iActionDiagram : actionDiagramRoots.values()) {
			switch (iActionDiagram.getADType()) {
			case ACT -> processACT(iActionDiagram);
			case BLK -> processBLK(iActionDiagram);
			case CMP -> processCMP(iActionDiagram);
			case CND -> processCND(iActionDiagram);
			case CTX -> processCTX(iActionDiagram);
			case EXP -> processEXP(iActionDiagram);
			case PAR -> processPAR(iActionDiagram);
			case SUB -> processSUB(iActionDiagram);
			}
		}
		int index = 0;
		for (IActionDiagram iActionDiagram : actionDiagramTree.values()) {
			actionDiagram.put(index++, iActionDiagram);
		}
		
	}

	@Transactional
	private void mapAD(Y2FunctionDetails function, IMapper mapper) {
//		try (Stream<Y2FunctionActionDiagram> stream = Y2FunctionActionDiagram.stream("adId.functionSurrogate",
//				Sort.ascending("adId.elementNo"), function.functionSurrogate)) {
//			stream.forEach(e -> mapADEntry(e, mapper));
//		}
		List<Y2FunctionActionDiagram> list = Y2FunctionActionDiagram.list("adId.functionSurrogate",
				Sort.ascending("adId.elementNo"), function.functionSurrogate);
		for (Y2FunctionActionDiagram y2FunctionActionDiagram : list) {
			mapADEntry(y2FunctionActionDiagram, mapper);
		}
	}

	private void mapADEntry(Y2FunctionActionDiagram adEntry, IMapper mapper) {
		IActionDiagram adElement = ADElementFactory.newInstance(adEntry);
		((CoreMapper) mapper).directMap(adElement, adEntry);
		actionDiagramRoots.put(adElement.getElementNo(), adElement);
		actionDiagramTree.put(adElement.getElementNo(), adElement);
	}

	private void processSUB(IActionDiagram iActionDiagram) {
		AdSUBBLKFormat adSUBBLKFormat = (AdSUBBLKFormat) iActionDiagram;
		if (adSUBBLKFormat.blockChain != null) {
			adSUBBLKFormat.adBlocks = adSUBBLKFormat.fetchBlock(
					adSUBBLKFormat.blockChain, actionDiagramTree, actionDiagramRoots);
		}
		
		if (adSUBBLKFormat.controllingCondition != null) {
			adSUBBLKFormat.adCONDITFormat = (AdCONDITFormat)actionDiagramRoots.get(adSUBBLKFormat.controllingCondition);
			actionDiagramTree.remove(adSUBBLKFormat.controllingCondition);
		}
		
	}

	private void processPAR(IActionDiagram iActionDiagram) {
	}

	private void processEXP(IActionDiagram iActionDiagram) {
	}

	private void processCTX(IActionDiagram iActionDiagram) {
	}

	private void processCND(IActionDiagram iActionDiagram) {
		AdCONDITFormat adCONDITFormat = (AdCONDITFormat) iActionDiagram;
		if (adCONDITFormat.owningField != null) {
			Optional<Y2Field> y2Field = Y2Field.findByIdOptional(adCONDITFormat.owningField);
			if (y2Field.isPresent()) {
				adCONDITFormat.owningFieldName = y2Field.get().fieldName;
			}
		}
		if (adCONDITFormat.compoundConditionChain != null) {
			adCONDITFormat.compoundCondition = adCONDITFormat.fetchBlock(
					adCONDITFormat.compoundConditionChain, actionDiagramTree, actionDiagramRoots);
		}
		if (adCONDITFormat.userExpressionChain != null) {
			adCONDITFormat.userExpression = adCONDITFormat.fetchBlock(
					adCONDITFormat.userExpressionChain, actionDiagramTree, actionDiagramRoots);
		}
		
		
	}

	private void processCMP(IActionDiagram iActionDiagram) {
		
	}

	private void processBLK(IActionDiagram iActionDiagram) {
		AdBLOCKFormat adBLOCKFormat = (AdBLOCKFormat) iActionDiagram;
		ADBlockAttribute attribute = ADBlockAttribute.valueOf(adBLOCKFormat.elementAttribute);
		switch (attribute) {
		case ACT -> processActBlk(adBLOCKFormat);
		case CAS -> processCasBlk(adBLOCKFormat);
		case EXT -> processExtBlk(adBLOCKFormat);
		case ITR -> processItrBlk(adBLOCKFormat);
		case SEQ -> processSeqBlk(adBLOCKFormat);
		case TXT -> processTxtBlk(adBLOCKFormat);
		}
		
	}

	private void processSeqBlk(AdBLOCKFormat adBLOCKFormat) {
		adBLOCKFormat.adNotActions = adBLOCKFormat.fetchBlock(
				adBLOCKFormat.subChainNotActBlock, actionDiagramTree, actionDiagramRoots);
		}

	private void processCasBlk(AdBLOCKFormat adBLOCKFormat) {
		adBLOCKFormat.adNotActions = adBLOCKFormat.fetchBlock(
				adBLOCKFormat.subChainNotActBlock, actionDiagramTree, actionDiagramRoots);
	}

	private void processTxtBlk(AdBLOCKFormat adBLOCKFormat) {
		
	}

	private void processItrBlk(AdBLOCKFormat adBLOCKFormat) {
		
	}

	private void processExtBlk(AdBLOCKFormat adBLOCKFormat) {
		
	}

	private void processActBlk(AdBLOCKFormat adBLOCKFormat) {
		Integer actKey = adBLOCKFormat.actionActSurrogate;
		AdACTIONFormat adACTIONFormat = (AdACTIONFormat)actionDiagramRoots.get(actKey);
		adBLOCKFormat.adAction = adACTIONFormat;
		actionDiagramTree.remove(actKey);
	}

	private void processACT(IActionDiagram iActionDiagram) {
		AdACTIONFormat ad = (AdACTIONFormat) iActionDiagram;
		ad.parameters = ad.fetchBlock(ad.parameterChain, actionDiagramTree, actionDiagramRoots);
	}

}