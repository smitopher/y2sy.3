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

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.cmfirsttech.y2.api.model.EntityDataModel;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.service.impl.ModelObjectXService;

@Path("/modelObjectX")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Deprecated
public class ModelObjectXResource {
	public static final String INCLUDE_SYSTEM_OBJECTS = "sysObjects";
	public static final String OBJECT = "object";
	public static final String OWNER = "owner";
	public static final String SURROGATE = "surrogate";


	@Inject
	ModelObjectXService listService;

	@POST
	@Path("/init")
	public boolean Init(@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObj) {
		return listService.init(sysObj);
	}

	@GET
	public IModel getObjectBySurrogate(@QueryParam(SURROGATE)  Integer surrogate) {
			return listService.getObjectBySurrogate(surrogate);
	}

	@GET
	@Path("/DataModel")
	public EntityDataModel getEntityDataModelBySurrogate(
		@QueryParam(SURROGATE)  Integer surrogate) {
			return listService.getEntityDataModelBySurrogate(surrogate);
	}
}
