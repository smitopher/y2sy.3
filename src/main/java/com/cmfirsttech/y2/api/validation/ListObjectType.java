package com.cmfirsttech.y2.api.validation;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ListObjectType {
	ALL("*ALL"),
	ACCESS_PATH("*ACP"),
	APPLICATION_AREA("*APP"),
	ARRAY("*ARR"),
	CONDITION("*CND"),
	FIELD("*FLD"),
	FILE("*FIL"),
	FUNCTION("*FUN"),
	MESSAGE("*MSG");
	
	private ListObjectType(String cmdText) {
		this.cmdText = cmdText;
	}

	@JsonValue
	public final String cmdText;
	
	public static Optional<ListObjectType> fromString(String s) {
		String type = s.stripTrailing();
		return Arrays.stream(values()).filter(l -> l.cmdText.equalsIgnoreCase(type)).findAny();
	}

	@Override
	public String toString() {
		return cmdText;
	}

}
