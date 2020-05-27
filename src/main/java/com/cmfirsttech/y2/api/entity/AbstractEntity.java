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
package com.cmfirsttech.y2.api.entity;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public abstract class AbstractEntity extends PanacheEntityBase implements IEntity {
	
	private static final Map<Class<? extends IEntity>, Map<String, Field>> FIELD_MAP = new HashMap<>();
	private final Logger logger = Logger.getLogger(getClass()); 
	
	@Inject
	EntityManager em;

	
	@Override
	public Optional<Field> getMappedField(String name) {
		return Optional.ofNullable(getFieldMap(getClass()).get(name));
	}

	@Override
	public void stripTrailing() {
		getFieldMap(getClass()).values().stream().filter(f -> f.getType().equals(String.class)).forEach(f -> {
			try {
				String s = f.get(this).toString().stripTrailing();
				f.set(this, s);
			} catch (Exception e) {
				logger.error(String.format("Error trimming 2E entity %1$s field %2$s String value",
						this.getClass().getSimpleName(), f.getName()), e);
			}
		});
		return;
	}

	@Override
	public Set<Field> getFields() {
		return getFieldMap(getClass()).values().stream().collect(Collectors.toSet());
	}

	private Map<String, Field> getFieldMap(Class<? extends IEntity> entity) {
		Map<String, Field> fieldMap = FIELD_MAP.get(entity);
		if (fieldMap == null) {
			synchronized (FIELD_MAP) {
				fieldMap = FIELD_MAP.get(entity);
				if (fieldMap == null) {
					fieldMap = new HashMap<>();
					for (Field field : getClass().getFields()) {
						fieldMap.put(field.getName(), field);
					}
					fieldMap = Collections.unmodifiableMap(fieldMap);
					FIELD_MAP.put(entity, fieldMap);
				}
			}
		}
		return fieldMap;
	}

}
