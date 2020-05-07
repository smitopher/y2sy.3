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
package com.cmfirsttech.y2.api.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.internal.ModelClass;
import com.cmfirsttech.y2.api.service.impl.JT400;

@ApplicationScoped
public class CoreMapper implements IMapper{
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Inject
	JT400 jt400;
	
	@Inject
	MapperFields mapperFields;
	
	@Inject
	EntityManager em;
	
	@Override
	public void directMap(IModel modelObject, IEntity entity) {
		entity.stripTrailing();
		for (MappingConfig config : mapperFields.getMappingFields(modelObject.getClass())) {
			if (config.field.getType().equals(Instant.class)) {
				mapInstant(config, modelObject, entity);
				continue;
			}
			mapObject(config, modelObject, entity);
		}
		modelObject.customMapping(entity);
	}
	
	private void mapObject(MappingConfig mappingConfig, IModel modelObject, IEntity entity) {
		try {
			Optional<Field> entityField = entity.getMappedField(mappingConfig.mapSource);
			Object entityValue;
			if (entityField.isPresent()) {
				entityValue = entityField.get().get(entity);
				if (entityValue == null) {
					String message = String.format("2E mappingField %1$s in Entity %2$s is null", 
							mappingConfig.mapSource, entity.getClass().getSimpleName());
					logger.error(message);
					throw new MappingException(message);
				}
			} else {
				String message = 
						String.format("2E mappingField %1$s in Entity %2$s for Model %3$s is not found", 
						mappingConfig.mapSource, entity.getClass().getSimpleName(), modelObject.getClass().getSimpleName());
				logger.error(message);
				throw new MappingException(message);
			}
			if (mappingConfig.nullIfEmpty) {
				if (isEmpty(entityValue)) {
					return;
				}
			}
			if(mappingConfig.field.getType().isEnum()) {
				entityValue = mapEnum(mappingConfig.field, entityValue);
				mappingConfig.field.set(modelObject, entityValue);
				return;
			}
			if (mappingConfig.isCollection) {
				entityValue = collectionMapping(mappingConfig, entity);
				mappingConfig.field.set(modelObject, entityValue);
				return;
			}
			if (mappingConfig.isSubMap) {
				IModel subModel = (IModel)mappingConfig.field.getType().getConstructor().newInstance();
				IEntity subEntity = (IEntity)entityValue;
				directMap(subModel, subEntity);
				mappingConfig.field.set(modelObject, subModel);
				return;
			}
			mappingConfig.field.set(modelObject, entityValue);
		} catch (Exception e) {
			String message = String.format("Error mapping %1$s", mappingConfig.field.getName());
			logger.error(message);
			throw new MappingException(message, e);
		}
	}
	
	private List<IModel> collectionMapping(MappingConfig mappingConfig, IEntity entity) {
		try {
			Optional<Field> entityField = entity.getMappedField(mappingConfig.mapSource);
			if (entityField.isEmpty()) {
				logger.warn(String.format("%1$s was not found in Entity %2$s",
						mappingConfig.mapSource, entity.getClass().getSimpleName()));
				return null;
			}
			@SuppressWarnings("unchecked")
			Collection<IEntity> entities = (Collection<IEntity>)entityField.get().get(entity);
			if(entities.isEmpty()) {
				return Collections.emptyList();
			}
			List<IModel> models = new ArrayList<>();
			for (IEntity subEntity : entities) {
				ModelClass modelClass = mappingConfig.field.getAnnotation(ModelClass.class);
				if (modelClass == null) {
					logger.error(String.format("Model %1$s field %2$s is not annotated witn ModelClass",
							mappingConfig.field.getDeclaringClass().getSimpleName(), mappingConfig.field.getName()));
					return null;
				}
				IModel model = modelClass.modelClass().getConstructor().newInstance();
				directMap(model, subEntity);
				models.add(model);
			}
			return models;
		} catch (Exception e) {
			logger.error("Error mapping Model to a list", e);
			return null;
		}
	}
	
	private boolean isEmpty(Object entityValue) {
		if (entityValue instanceof String) {
			return entityValue.toString().isBlank();
		}

		if (entityValue instanceof Number) {
			return ((Number) entityValue).doubleValue() == EMPTY_NUMBER;
		}
		return false;
	}

	private Object mapEnum(Field modelField, Object entityValue)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String enumName = entityValue.toString().replace(' ', '_').replace('#', '_');
		Method valueOf = modelField.getType().getDeclaredMethod("valueOf", String.class);
		entityValue = valueOf.invoke(null, enumName);
		return entityValue;
	}

	private void mapInstant(MappingConfig mappingConfig, IModel modelObject, IEntity entity) {
		try {
			Optional<Field> date = entity.getMappedField(mappingConfig.instantDateSource);
			Optional<Field> time = entity.getMappedField(mappingConfig.instantTimeSource);
			Integer dateInteger = (Integer)date.get().get(entity);
			Integer timeInteger = (Integer)time.get().get(entity);
			if (dateInteger.intValue() == EMPTY_NUMBER) {
				if (mappingConfig.nullIfEmpty) {
					return;
				}
			}
			Instant stamp = createInstant(dateInteger, timeInteger);
			mappingConfig.field.set(modelObject, stamp);
		} catch (Exception e) {
			String message = String.format("Error mapping %1$s", mappingConfig.field.getName());
			logger.error(message, e);
			throw new MappingException(message, e);
		}
	}

	private Instant createInstant(Integer date, Integer time) {
		Instant instant = null;
		if (date.intValue() == EMPTY_NUMBER) {
			logger.warn("Date is zero, null Instant returned");
			return instant;
		}
		try {
			int intDate = date.intValue() + 19000000;
			int year = intDate / 10000;
			intDate = intDate % 10000;
			int month = intDate / 100;
			int day = intDate % 100;
			int intTime = time.intValue();
			int hours = intTime / 10000;
			intTime = intDate % 10000;
			int minutes = intTime / 100;
			int seconds = intTime % 100;
			int nanos = 0;
			ZoneId zone = jt400.getZoneId();
			ZonedDateTime stamp = ZonedDateTime.of(year, month, day, hours, minutes, seconds, nanos, zone);
			instant = stamp.toInstant();
		} catch (Exception e) {
			logger.error("Error parsing audit stamp", e);
		}
		return instant;
	}
	
}
