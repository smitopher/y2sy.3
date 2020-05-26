package com.cmfirsttech.y2.api.model;

import java.util.Arrays;
import java.util.Optional;

public enum ADType {
	ACT(false),
	BLK(true),
	CMP(false),
	CND(true),
	CTX(false),
	EXP(true),
	PAR(true),
	SUB(true);
	
	private ADType(boolean hasNextElement) {
		this.hasNextElement = hasNextElement;
	}

	public final boolean hasNextElement;
	
	public static Optional<ADType> fromString(String s) {
		return Arrays.stream(values()).filter(t -> t.name().equals(s)).findAny();
	}
	
	
}