package com.cmfirsttech.y2.api.model.impl;

import java.util.Optional;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2AccessPath;
import com.cmfirsttech.y2.api.entity.impl.Y2FunctionDetails;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.sun.istack.logging.Logger;

@Y2EntityClass(entityClass = Y2FunctionDetails.class)

public class FileFunctions extends AbstractModel{

	@DirectMapped(mapSource = "messageSurrogate")
	public Integer functionSurrogate;

	@DirectMapped(mapSource = "messageName")
	public String functionName;

	public String text;

	public String nptObjectAttribute;
	
	public Integer accessPathSurrogate;
	
	@DirectMapped(skip = true)
	public String accessPathname;

	@Override
	public void customMapping(IEntity entity) {
		super.customMapping(entity);
		if (accessPathSurrogate != null) {
			Optional<Y2AccessPath> accessPath = Optional.ofNullable(Y2AccessPath.findById(accessPathSurrogate));
			if (accessPath.isPresent()) {
				accessPathname = accessPath.get().accessPathName;
			} else {
				Logger.getLogger(getClass()).warning(String.format(
						"Access Path name is null for function surrogate %1$s", functionSurrogate));
			}
		} else {
			Logger.getLogger(getClass()).warning(String.format(
					"Access Path surrogate is null for function surrogate %1$s", functionSurrogate));
		}
	}


}
