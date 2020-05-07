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

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.cmfirsttech.y2.api.bean.Y2Token;
import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.internal.ModelListQueries;
import com.cmfirsttech.y2.api.model.internal.ModelObjectList;
import com.cmfirsttech.y2.api.service.impl.CoreService;

@Path("/y2List")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(value = "y2api")
public class Y2ListResource {
	public static final String INCLUDE_SYSTEM_OBJECTS = "sysObjects";
	public static final String OBJECT = "object";
	public static final String OWNER = "owner";
	public static final String SURROGATE = "surrogate";
	
	private Logger logger = Logger.getLogger(getClass());

	@Inject
	CoreService service;
	
	@Inject
	Y2Token y2Token;
	
	@GET
	@Path("/all")
	public Response getAll(@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObj) {
		try {
			List<IModel> entries = service.fetchList(null, sysObj.orElse(Boolean.FALSE));
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	@GET
	@Path("/entity")
	public Response getEntities(
			@QueryParam(OBJECT) @Valid Optional<@Size(max = 25) String> object,
			@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObjects) {
		try {
			List<IModel> entries = service.unscopedObjects(object, ObjectType.FIL, sysObjects);
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	@GET
	@Path("/function")
	public Response getFunctions(
			@QueryParam(OBJECT) @Valid Optional<@Size(max = 25) String> object,
			@QueryParam(OWNER) @Valid Optional<@Size(max = 25) String> owner,
			@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObjects) {
		try {
			List<IModel> entries = 
					service.scopedObjects(object, owner, ObjectType.FUN, sysObjects);
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	@GET
	@Path("/accessPath")
	public Response getAccessPaths(
			@QueryParam(OBJECT) @Valid Optional<@Size(max = 25) String> object,
			@QueryParam(OWNER) @Valid Optional<@Size(max = 25) String> owner,
			@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObjects) {
		try {
			List<IModel> entries = 
					service.scopedObjects(object, owner, ObjectType.ACP, sysObjects);
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	@GET
	@Path("/field")
	public Response getFields(
			@QueryParam(OBJECT) @Valid Optional<@Size(max = 25) String> object,
			@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObjects) {
		try {
			List<IModel> entries = 
					service.unscopedObjects(object, ObjectType.FLD, sysObjects);
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	@GET
	public Response getModelList(
			@RequestBody(required = true) @Valid ModelListQueries queries,
			@QueryParam(INCLUDE_SYSTEM_OBJECTS) Optional<Boolean> sysObj) {
		try {
			List<IModel> entries = 
					service.fetchList(queries.queries, sysObj.orElse(Boolean.FALSE));
			return Response.ok(newModelObjectList(entries)).build();
		} catch (RuntimeException e) {
			logger.error(e.getLocalizedMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Response.serverError().build();
	}

	
	private ModelObjectList newModelObjectList(List<IModel> entries) {
		ModelObjectList modelList = new ModelObjectList();
		modelList.y2model = y2Token.library;
		modelList.listName = y2Token.listName;
		modelList.entries = entries;
		return modelList;
	}
	
	
}
