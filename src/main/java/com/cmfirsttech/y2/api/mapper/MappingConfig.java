package com.cmfirsttech.y2.api.mapper;

import java.lang.reflect.Field;
import java.util.Collection;

import com.cmfirsttech.y2.api.model.IModel;

public final class MappingConfig {

	public final Field field;
	public final String mapSource;
	public final String instantDateSource;
	public final String instantTimeSource;
	public final MappingType mappingType;
	public final boolean nullIfEmpty;

	public MappingConfig(Field field) {
		this.field = field;
		DirectMapped directMapped = field.getAnnotation(DirectMapped.class);
		if (directMapped == null) {
			directMapped = IDirectMapped.getDirectMapped();
		}
		this.nullIfEmpty = directMapped.nullIfEmpty();
		this.mappingType = setMappingType(field, directMapped);
		if (field.isAnnotationPresent(DirectMapped.class)) {
			this.mapSource = directMapped.mapSource();
		} else {
			this.mapSource = this.field.getName();
		}
		this.instantDateSource = directMapped.instantDateSource();
		this.instantTimeSource = directMapped.instantTimeSource();
	}
	
	private MappingType setMappingType(Field field, DirectMapped directMapped) {
		MappingType mappingType = directMapped.mappingType();
		if (mappingType.equals(MappingType.NAMED)) {
			if (!field.isAnnotationPresent(DirectMapped.class)) {
				if (field.getType().isEnum()) {
					mappingType = MappingType.NAMED_ENUM;
				} else if (field.getType().equals(Boolean.class)) {
					mappingType = MappingType.NAMED_BOOLEAN;
				} else if (Collection.class.isAssignableFrom(field.getType())) {
					mappingType = MappingType.COLLECTION;
				} else if (IModel.class.isAssignableFrom(field.getType())) {
					mappingType = MappingType.SUB_MAP;
				}
			}
		}
		return mappingType;
	}
}
