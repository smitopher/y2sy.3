package com.cmfirsttech.y2.api.model;

import java.util.Optional;

public abstract class HasNextModel extends AbstractActionDiagramElement {
	
	public Integer nextElementNo;

	@Override
	public Optional<Integer> getNext() {
		return Optional.ofNullable(nextElementNo);
	}

}
