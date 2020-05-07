package com.cmfirsttech.y2.api.constants;

import org.reflections.Reflections;

public enum TestReflections {
	REFLECT;
	public final Reflections instance = new Reflections("com.cmfirsttech");
}
