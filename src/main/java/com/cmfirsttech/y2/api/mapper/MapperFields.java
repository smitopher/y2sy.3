package com.cmfirsttech.y2.api.mapper;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.model.IModel;

@ApplicationScoped
public class MapperFields {

	private final Logger logger = Logger.getLogger(getClass());
	private final Map<Class<? extends IModel>, Set<MappingConfig>> mapperFields = new HashMap<>();
	public Set<MappingConfig> getMappingFields(Class<? extends IModel> mappingClass) {
		Set<MappingConfig> fields = mapperFields.get(mappingClass);
		if (fields == null) {
			synchronized (mapperFields) {
				fields = mapperFields.get(mappingClass);
				if (fields == null) {
					fields = Collections.unmodifiableSet(buildMapperFields(mappingClass));
					mapperFields.put(mappingClass, fields);
				}
			}
		}
		return fields;
	}

	private Set<MappingConfig> buildMapperFields(Class<? extends IModel> mappingClass) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Building mapping fields for %1$s", mappingClass.getSimpleName()));
		}
		Set<MappingConfig> mapperSet = new LinkedHashSet<>();
		for (Field field : mappingClass.getFields()) {
			MappingConfig mappingConfig = new MappingConfig(field);
			if (mappingConfig.skip) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Skipping mapping field %1$s for %2$s", mappingClass.getSimpleName()));
				}
				continue;
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Adding mapping field %1$s for %2$s", mappingClass.getSimpleName()));
			}
			mapperSet.add(mappingConfig);
		}
		return mapperSet;
	}
}