package com.cmfirsttech.y2.api.model.impl;

import com.cmfirsttech.y2.api.entity.impl.Y2AccessPath;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

@Y2EntityClass(entityClass = Y2AccessPath.class)
public class FileAccessPaths extends AbstractModel {

	public Integer accessPathSurrogate;
	
	public String accessPathName;

	public String accessPathType;

	public String objectTypeForObjectType;

	public String objectAttributeForObjectAttribute;

	public String sourceMember;
}
