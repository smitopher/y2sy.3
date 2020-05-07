package com.cmfirsttech.y2.api.resource;

import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.impl.AccessPath;
import com.cmfirsttech.y2.api.model.impl.FieldDetails;
import com.cmfirsttech.y2.api.model.impl.FileDetails;
import com.cmfirsttech.y2.api.model.impl.FunctionDetails;

public enum Y2ResourceType {
	field(FieldDetails.class),
	file(FileDetails.class),
	function(FunctionDetails.class),
	accessPath(AccessPath.class);
	
	private Y2ResourceType(Class<? extends IModel> modelClass) {
		this.modelClass = modelClass;
	}

	public final Class<? extends IModel> modelClass;

}
