package com.cmfirsttech.y2.api.model.impl;

import static com.cmfirsttech.y2.api.mapper.MappingType.SKIP;

import java.util.List;
import java.util.Optional;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2Field;
import com.cmfirsttech.y2.api.entity.impl.Y2FieldDomain;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.IMapper;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.model.internal.ModelClass;

@Y2EntityClass(entityClass = Y2FieldDomain.class)
public class FieldDomain extends AbstractModel{

	public Integer domainSurrogate;
	
	@DirectMapped(mappingType = SKIP)
	public String domainFieldName;

	public String objectType;

	public FieldAttribute fieldAttribute;

	public String requiredBySynon_2;

	public String relationUsage;

	public String valueMappingInd;

	public String userModifiableField;

	public String reportEditCode;

	public String screenInputEditCode;

	public String screenOutputEditCode;

	public String allowLowerCase;

	public String applyModulus10_11Check;

	public String checkValidName;

	public Integer externalDecimalPlaces;

	public Integer externalIntegers;

	public Integer externalLength;

	public String internalDataType;

	public Integer internalDecimalPlaces;

	public Integer internalIntegers;

	public Integer internalLength;

	public String keyboardShift;

	public String mandatoryFill;
	
	@ModelClass(modelClass = FieldCondition.class) 
	@DirectMapped(mappingType = MappingType.COLLECTION, mapSource = "mdlConditions")
	public List<FieldCondition> conditions;

	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		super.customMapping(entity, mapper);
		Optional<Y2Field> y2Field = Optional.ofNullable(Y2Field.findById(domainSurrogate));
		if (y2Field.isPresent()) {
			domainFieldName = y2Field.get().fieldName;
		}
	}

}
