package com.cmfirsttech.y2.api.model.internal;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.cmfirsttech.y2.api.model.IModel;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ModelClass {
	public Class<? extends IModel> modelClass();

}
