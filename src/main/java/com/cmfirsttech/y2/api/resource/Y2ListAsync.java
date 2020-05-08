package com.cmfirsttech.y2.api.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cmfirsttech.y2.api.model.impl.ObjectListEntry;
import com.cmfirsttech.y2.api.service.impl.Y2ListServiceAsync;

import io.smallrye.mutiny.Multi;

@Path("/y2ListAsync")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(value = "y2api")
public class Y2ListAsync {
	
	@Inject
	Y2ListServiceAsync asyncService;
	
	@GET
	@Path("/all")
	public Multi<ObjectListEntry> all() {
		return asyncService.asyncAll();
	}

}
