package com.cmfirsttech.y2.api.service.impl;

import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.cmfirsttech.y2.api.entity.impl.Y2ModelListEntry;
import com.cmfirsttech.y2.api.mapper.CoreMapper;
import com.cmfirsttech.y2.api.model.impl.ObjectListEntry;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class Y2ListServiceAsync {
	
	@Inject
	CoreMapper mapper;
	
	@Transactional
	public Multi<ObjectListEntry> asyncAll() {
		return Multi.createFrom().items(getStream()).map(this::map);
	}
	
	private Stream<Y2ModelListEntry> getStream() {
		return Y2ModelListEntry.streamAll();
	}
	
	private ObjectListEntry map(Y2ModelListEntry y) {
		ObjectListEntry listEntry = new ObjectListEntry();
		mapper.directMap(listEntry, y);
		return listEntry;
	}
}
