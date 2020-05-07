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
package com.cmfirsttech.y2.api.resource;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.service.impl.CoreService;

@Path("/y2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(value = "y2api")
public class Y2Resource {
	
	private Logger logger = Logger.getLogger(getClass());

	@Inject
	CoreService objectService;
	
	@GET
	@Path("/{type}/{surrogate}")
	public Response getModelObject(@PathParam Y2ResourceType type, @PathParam Integer surrogate) {
		try {
			IModel model;
			Class<? extends IEntity> entityClass = 
					type.modelClass.getAnnotation(Y2EntityClass.class).entityClass();
			Optional<IEntity> entity = objectService.fetchEntity(entityClass, surrogate);
			if (entity.isEmpty()) {
				model = type.modelClass.getConstructor().newInstance();
				return Response.status(Status.NOT_FOUND).entity(model).build();
			}
			model = objectService.fetchModelObject(type, entity.get());
			return Response.ok(model).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

}
