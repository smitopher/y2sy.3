package com.cmfirsttech.y2.api.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.cmfirsttech.y2.api.bean.Y2Token;
import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2ModelListEntry;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.impl.ObjectListEntry;
import com.cmfirsttech.y2.api.model.internal.ModelListQuery;
import com.cmfirsttech.y2.api.model.internal.ModelListQueryFactory;
import com.cmfirsttech.y2.api.model.internal.ModelQueryType;
import com.cmfirsttech.y2.api.service.AbstractService;

@ApplicationScoped
public class CoreService extends AbstractService {

	public static final String YBMDMDLLST_CMD = "YBLDMDLLST";
	public static final String YBMDMDLLST_FORMAT = "%1$s OBJNAM(%2$s) MDLLST(%3$s/%4$s) LSTOPT(*ADD) INCSYSOBJ(%5$s)";
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0000000000.00000");

	@Inject
	EntityManager em;
	
	@Inject
	ModelListQueryFactory queryFactory;
	
	@Inject
	Y2Token y2Token;
	
	@Inject
	JT400 jt400;
	
	public Optional<IEntity> fetchEntity(Class<? extends IEntity> entityClass, Integer surrogate) {
		return Optional.ofNullable(em.find(entityClass, surrogate));
	}
	
	public List<IModel> scopedObjects(Optional<String> object, 
			Optional<String> owner, ObjectType objectType, Optional<Boolean> sysObjects) {
		ModelQueryType mqt;
		if (owner.isPresent()) {
			mqt = ModelQueryType.HAS_OWNER;
		} else {
			mqt = ModelQueryType.ANY_OWNER;
		}
		String ownerName = owner.orElse("*ANY");
		String objectName = object.orElse("*ALL");
		List<ModelListQuery> queries = buildFactoryQuery(mqt, ownerName, objectName, objectType);
		return fetchList(queries, sysObjects.orElse(Boolean.FALSE));

	}
	
	public List<IModel> unscopedObjects(Optional<String> object, 
			ObjectType objectType, Optional<Boolean> sysObjects) {
		ModelQueryType mqt = ModelQueryType.NO_OWNER;
		String objectName = object.orElse("*ALL");
		List<ModelListQuery> queries = buildFactoryQuery(mqt, null, objectName, objectType);
		return fetchList(queries, sysObjects.orElse(Boolean.TRUE));
	}	
	
	public List<IModel> fetchList(List<ModelListQuery> queries, boolean includeSystemObjects) {
		// if queries is null, return all
		if(queries == null)	{
			queries = buildFactoryQuery(ModelQueryType.ALL_OBJ, null, null, null);		
		}
		
		String sysObj;
		if (includeSystemObjects) {
			sysObj = "*YES";
		} else {
			sysObj = "*NO";
		}
		runBuildModelList(queries, sysObj);
		return mapListEntries();
	}
	
	public void runBuildModelList(List<ModelListQuery> queries, String sysObj) {
		String cmd = String.format(JT400.CHGLIBL_FORMAT, y2Token.libl);
		jt400.runCmd(cmd);
		String queryString = queries.stream().map(ModelListQuery::toString).collect(Collectors.joining(" "));
		cmd = String.format(YBMDMDLLST_FORMAT, YBMDMDLLST_CMD, queryString, y2Token.library, y2Token.listName, sysObj);
		clearModelList();
		jt400.runCmd(cmd);
	}
	
	@Transactional
	private List<IModel> mapListEntries() {
		try (Stream<Y2ModelListEntry> stream = Y2ModelListEntry.streamAll())  {
			return stream.map(this::map).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error fetching Model List", e);
		}
		return Collections.emptyList();
	}
	
	private IModel map(IEntity entity) {
		ObjectListEntry model = new ObjectListEntry();
		mapper.directMap(model, entity);
		return model;
	}
	
	private List<ModelListQuery> buildFactoryQuery(ModelQueryType type, String owner, String object,
			ObjectType objectType) {
		List<ModelListQuery> queries = new ArrayList<>();
		ModelListQuery query = queryFactory.getInstance(type, owner, object, objectType);
		queries.add(query);
		return queries;

	}
	@Transactional
	void clearModelList() {
		Y2ModelListEntry.deleteAll();
	}

	
}
