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
package com.cmfirsttech.y2.api.service.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.impl.Y2EntityRelations;
import com.cmfirsttech.y2.api.mapper.CoreMapper;
import com.cmfirsttech.y2.api.model.EntityDataModel;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.impl.DataModelTarget;
import com.cmfirsttech.y2.api.service.AbstractService;

@ApplicationScoped
@Deprecated
public class ModelObjectXService extends AbstractService {

	@Inject
	CoreService service;
	
	@Inject
	CoreMapper mapper;
	
	@Inject
	EntityManager em;

	private Logger logger = Logger.getLogger(getClass());

	public ModelObjectXService() {
	}

	public boolean init(Optional<Boolean> sysObj) {
		// Get all obje list, transfrom and store
		boolean result;
		try {
			result = !service.fetchList(null, true).isEmpty();
		} catch (Exception e) {
			logger.error("Init Model List failed", e);
			result = false;
		}
		return result;
	}

	public IModel getObjectBySurrogate(Integer surrogate) {
		return null;
	}

	@Transactional
	public EntityDataModel getEntityDataModelBySurrogate(Integer surrogate) {
		EntityDataModel dataModel = new EntityDataModel();
		dataModel.surrogate = surrogate;
		try (Stream<Y2EntityRelations> relationStream = Y2EntityRelations.stream("objectSurrogate", surrogate)) {
			dataModel.simpleModelObjects = relationStream.map(t -> {
				return map(t);
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			dataModel.simpleModelObjects = Collections.emptyList();

		}
		return dataModel;
	} 
	
	public IModel map(Y2EntityRelations entity) {
		DataModelTarget model = new DataModelTarget();
		mapper.directMap(model, entity);
		return model;
	}
	
}
