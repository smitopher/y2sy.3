package com.cmfirsttech.y2.api.mapper;

import java.lang.reflect.Field;

public interface IDirectMapped {
	@DirectMapped
	public Object object = null;
	
	public static DirectMapped getDirectMapped() {
		for (Field field : IDirectMapped.class.getFields()) {
			return field.getAnnotation(DirectMapped.class);
		}
		return null;
	}
}
