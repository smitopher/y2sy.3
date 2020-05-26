package com.cmfirsttech.y2.api.model.internal;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;

public class ActionDiagram {
	public List<ADFormat> actionDiagram = new LinkedList<>();
	
	public void addRecords(RecordFormat format, List<Record> records) throws UnsupportedEncodingException {
		ADFormat adFormat = new ADFormat(format.getName());
		actionDiagram.add(adFormat);
		for (Record record : records) {
			ADRecord adRecord = new ADRecord(record);
			adFormat.records.add(adRecord);
		}
	}
}
