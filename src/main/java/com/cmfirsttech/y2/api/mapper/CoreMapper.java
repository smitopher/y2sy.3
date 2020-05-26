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

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.internal.ModelClass;
import com.cmfirsttech.y2.api.service.impl.ActionDiagramImport;
import com.cmfirsttech.y2.api.service.impl.JT400;

@ApplicationScoped
public class CoreMapper implements IMapper {

	private final Logger logger = Logger.getLogger(getClass());

	@Inject
	JT400 jt400;

	@Inject
	MapperFields mapperFields;

	@Inject
	ActionDiagramImport adImport;

	@Override
	public void directMap(IModel modelObject, IEntity entity) {
		entity.stripTrailing();
		for (MappingConfig config : mapperFields.getMappingFields(modelObject.getClass())) {
			mapObject(config, modelObject, entity);
		}
		modelObject.customMapping(entity, this);
	}

	private void mapObject(MappingConfig mappingConfig, IModel modelObject, IEntity entity) {
		try {
			Object entityValue = switch (mappingConfig.mappingType) {
			case NAMED -> namedMapping(mappingConfig, entity);
			case NAMED_BOOLEAN -> booleanMapping(mappingConfig, entity);
			case NAMED_ENUM -> enumMapping(mappingConfig, entity);
			case INSTANT -> instantMapping(mappingConfig, modelObject, entity);
			case SUB_MAP -> subMapping(mappingConfig, entity);
			case COLLECTION -> collectionMapping(mappingConfig, entity);
			default -> throw new IllegalArgumentException("Unexpected value: " + mappingConfig.mappingType);
			};
			if (mappingConfig.nullIfEmpty) {
				if (isEmpty(entityValue)) {
					return;
				}
			}
			mappingConfig.field.set(modelObject, entityValue);
		} catch (ReflectiveOperationException e) {
			String message = String.format("Error mapping %1$s", mappingConfig.field.getName());
			logger.error(message);
			throw new MappingException(message, e);
		}
	}

	private Object namedMapping(MappingConfig mappingConfig, IEntity entity) throws ReflectiveOperationException {
		Field entityField = getEntityField(mappingConfig.mapSource, entity);
		return entityField.get(entity);
	}

	private Object booleanMapping(MappingConfig mappingConfig, IEntity entity) throws ReflectiveOperationException {
		return namedMapping(mappingConfig, entity).toString().equals("Y");
	}

	private Object enumMapping(MappingConfig mappingConfig, IEntity entity) throws ReflectiveOperationException {
		Object entityValue = namedMapping(mappingConfig, entity);
		String enumName = entityValue.toString().replace(' ', '_').replace('#', '_');
		return mappingConfig.field.getType().getDeclaredMethod("valueOf", String.class).invoke(null, enumName);
	}

	private Object instantMapping(MappingConfig mappingConfig, IModel modelObject, IEntity entity) throws ReflectiveOperationException {
		Field date = getEntityField(mappingConfig.instantDateSource, entity);
		Field time = getEntityField(mappingConfig.instantTimeSource, entity);;
		Integer dateInteger = (Integer) date.get(entity);
		Integer timeInteger = (Integer) time.get(entity);
		if (dateInteger.intValue() == EMPTY_NUMBER) {
			logger.warn("Date is zero, null Instant returned");
			return null;
		}
		try {
			return createInstant(dateInteger, timeInteger);
		} catch (Exception e) {
			logger.error("Date parsing error", e);
		}
		return null;
	}

	private Object subMapping(MappingConfig mappingConfig, IEntity entity) throws ReflectiveOperationException {
		IEntity subEntity = (IEntity)namedMapping(mappingConfig, entity);
		IModel subModel = (IModel) mappingConfig.field.getType().getConstructor().newInstance();
		directMap(subModel, subEntity);
		return subModel;
	}

	@SuppressWarnings("unchecked")
	private Object collectionMapping(MappingConfig mappingConfig, IEntity entity) throws ReflectiveOperationException {
		Collection<IEntity> entities = (Collection<IEntity>) namedMapping(mappingConfig, entity);
		if (entities.isEmpty()) {
			return Collections.emptyList();
		}
		List<IModel> models = new ArrayList<>();
		for (IEntity subEntity : entities) {
			ModelClass modelClass = mappingConfig.field.getAnnotation(ModelClass.class);
			IModel model = modelClass.modelClass().getConstructor().newInstance();
			directMap(model, subEntity);
			models.add(model);
		}
		return models;
	}

	private boolean isEmpty(Object entityValue) {
		if (entityValue == null) {
			return true;
		}
		if (entityValue instanceof String) {
			return entityValue.toString().isBlank();
		}

		if (entityValue instanceof Number) {
			return ((Number) entityValue).doubleValue() == EMPTY_NUMBER;
		}
		return false;
	}

	private Instant createInstant(Integer date, Integer time) {
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
		return stamp.toInstant();
	}

	public ActionDiagramImport getADImport() {
		return adImport;
	}
	
	private Field getEntityField(String fieldName, IEntity entity) {
		Optional<Field> field = entity.getMappedField(fieldName);
		if (field.isEmpty()) {
			String message = String.format("2E mappingField %1$s in Entity %2$s is not found",
					fieldName, entity.getClass().getSimpleName());
			logger.error(message);
			throw new MappingException(message);
		}
		return field.get();
	}
}
