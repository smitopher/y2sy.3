package com.cmfirsttech.y2.api.mapper;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Collection;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.model.IModel;

public final class MappingConfig {

	public final Field field;
	public final String mapSource;
	public final String instantDateSource;
	public final String instantTimeSource;
	public final boolean skip;
	public final boolean nullIfEmpty;
	public final boolean isSubMap;
	public final boolean isCollection;

	private static final Logger LOGGER = Logger.getLogger(MappingConfig.class);
	private static final String ERROR_MSG_FORMAT = "Instant field %1$s in Model % missing required DirectMapped annotation for Date or Time source";
	public MappingConfig(Field field) {
		String mapSource;
		this.field = field;
		DirectMapped directMapped = field.getAnnotation(DirectMapped.class);
		if (directMapped == null) {
			directMapped = IDirectMapped.getDirectMapped();
		}
		this.nullIfEmpty = directMapped.nullIfEmpty();
		this.skip = directMapped.skip();
		this.instantDateSource = directMapped.instantDateSource();
		this.instantTimeSource = directMapped.instantTimeSource();
		mapSource = directMapped.mapSource();
		if (field.isAnnotationPresent(DirectMapped.class)) {
		}
		if (field.getType().equals(Instant.class)) {
			if (this.instantDateSource.isBlank() || this.instantTimeSource.isBlank()) {
				instantMappingError(field);
			}
		}
		if (mapSource.isBlank()) {
			this.mapSource = this.field.getName();
		} else {
			this.mapSource = mapSource;
		}
		this.isSubMap = IModel.class.isAssignableFrom(field.getType());
		this.isCollection = Collection.class.isAssignableFrom(field.getType());
	}
	
	private void instantMappingError(Field field) {
		String fieldName = field.getName(); 
		String modelName = field.getDeclaringClass().getSimpleName();
		String message = String.format(ERROR_MSG_FORMAT, fieldName, modelName);
		LOGGER.error(message);
	}
}
