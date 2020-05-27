package com.cmfirsttech.y2.api.model;

import static com.cmfirsttech.y2.api.mapper.MappingType.*;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractActionDiagramElement extends AbstractModel implements IActionDiagram {
	
	@DirectMapped(mappingType = SKIP)
	public Integer functionSurrogate;

	@DirectMapped(mappingType = SKIP)
	public Integer elementNo;
	
	public ADType elementType;
	
	@Override
	public void mapId(Y2FunctionActionDiagram ad) {
		functionSurrogate = ad.adId.functionSurrogate;
		elementNo = ad.adId.elementNo;
	}


	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		Y2FunctionActionDiagram y2ad = (Y2FunctionActionDiagram) entity;
		functionSurrogate = y2ad.adId.functionSurrogate;
		elementNo = y2ad.adId.elementNo;
	}


	@Override
	public Integer getElementNo() {
		return elementNo;
	}


	@Override
	public ADType getADType() {
		return elementType;
	}


	@Override
	public Optional<Integer> getNext() {
		return Optional.empty();
	}

	@JsonIgnore 
	public Map<Integer, IActionDiagram> fetchBlock(Integer nextKey,
			Map<Integer, IActionDiagram> actionDiagramTree, 
			Map<Integer, IActionDiagram> actionDiagramRoots ) {
		Map<Integer, IActionDiagram> map = new TreeMap<>();
		int index = 0;
		while (nextKey != null) {
			IActionDiagram next = actionDiagramRoots.get(nextKey);
			map.put(index++, next);
			if (actionDiagramTree.remove(nextKey) == null) {
				String message = String.format("Element %1$d type %2$s was not found in in AD Tree", 
						elementNo, elementType);
				Logger.getLogger(getClass()).warn(message);
				return map;
			};
			nextKey = next.getNext().orElse(null);
		}
		return map;
	}


}
