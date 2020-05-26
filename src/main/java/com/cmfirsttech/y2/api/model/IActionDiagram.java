package com.cmfirsttech.y2.api.model;

import java.util.Map;
import java.util.Optional;

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;

public interface IActionDiagram extends IModel {
	
	public void mapId(Y2FunctionActionDiagram ad);
	public Integer getElementNo();
	public ADType getADType();
	public Optional<Integer> getNext();
	public Map<Integer, IActionDiagram> fetchBlock(Integer nextKey, Map<Integer, IActionDiagram> actionDiagramTree, Map<Integer, IActionDiagram> actionDiagramRoots);
}
