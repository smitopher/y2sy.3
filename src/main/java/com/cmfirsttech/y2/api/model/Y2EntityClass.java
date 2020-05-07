package com.cmfirsttech.y2.api.model;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.cmfirsttech.y2.api.entity.IEntity;

@Retention(RUNTIME)
@Target({TYPE})
public @interface Y2EntityClass {
	public Class<? extends IEntity> entityClass();
}
