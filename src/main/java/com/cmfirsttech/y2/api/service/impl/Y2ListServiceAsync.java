package com.cmfirsttech.y2.api.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cmfirsttech.y2.api.model.internal.ModelObjectList;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class Y2ListServiceAsync {
	
	@Inject
	CoreService service;
	
	Uni<ModelObjectList> asyncAll() {
		
		
		return null;
	}
}
