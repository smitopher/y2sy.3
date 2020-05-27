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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.model.IModel;

@ApplicationScoped
public class CoreMapper extends AbstractMapper {

	@Inject
	MapperFields mapperFields;

	@Override
	public void directMap(IModel modelObject, IEntity entity) {
		entity.stripTrailing();
		for (MappingConfig config : mapperFields.getMappingFields(modelObject.getClass())) {
			mapObject(config, modelObject, entity);
		}
		modelObject.customMapping(entity, this);
	}

}
