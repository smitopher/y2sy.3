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
package com.cmfirsttech.y2.api.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.mapper.CoreMapper;
import com.cmfirsttech.y2.api.mapper.MappingException;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.resource.Y2ResourceType;

public abstract class AbstractService implements IService {
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	@Inject
	protected CoreMapper mapper;
	
	@Inject
	EntityManager em;

	@Override
	public IModel fetchModelObject(Y2ResourceType type, IEntity entity) {
		IModel model = null;
		try {
			model = type.modelClass.getConstructor().newInstance();
		} catch (Exception e) {
			logger.error("should not happen", e);
			throw new MappingException("", e);
		} 
		mapper.directMap(model, entity);
		return model;
	}
	
}
