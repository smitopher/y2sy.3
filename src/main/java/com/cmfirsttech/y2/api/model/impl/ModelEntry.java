package com.cmfirsttech.y2.api.model.impl;

import static com.cmfirsttech.y2.api.mapper.MappingType.SKIP;

import java.math.BigDecimal;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2ModelEntry;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2ModelEntry.class)
public class ModelEntry extends AbstractModel {

	public Integer fileSurrogate;

	public Integer fieldSurrogate;

	public Integer modelEntrySurrogate;

	public Integer renamedFromFieldSgt;

	public BigDecimal entrySequenceNo;

	public String expandedFlag;

	public Integer expansionSequenceNo;

	public Integer definingRelationSgt;

	@DirectMapped(mappingType = SKIP)
	public Integer designatingRelationSgt;

	public Integer refEntrySurrogate;

	public String entryType;

	public Integer refFileSurrogate;

	public Integer keyNumber;

	public String duplicateItemInd;

	public String forcedItemInd;

	public String renamedItemInd;

	public String foreignKeyInd;

	public Integer auxillaryEntrySurrogate;

	public Integer redirectingEntrySgt;

	public String redirectionType;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		Y2ModelEntry y2ModelEntry = (Y2ModelEntry) entity;
		designatingRelationSgt = y2ModelEntry.designatingRelation.relationSurrogate;
	}

}
