package com.cmfirsttech.y2.api.model.internal;

import java.util.LinkedList;
import java.util.List;

public class ADFormat {
	public String name;
	
	public List<ADRecord> records;
	public ADFormat(String name) {
		this.name = name;
		this.records = new LinkedList<>();
	}
}