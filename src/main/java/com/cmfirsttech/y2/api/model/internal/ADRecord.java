package com.cmfirsttech.y2.api.model.internal;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cmfirsttech.y2.api.mapper.MappingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.ibm.as400.access.AS400DataType;
import com.ibm.as400.access.FieldDescription;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;

public class ADRecord {
	@JsonSerialize(using = MapSerializer.class)
	public Map<String, Object> adField = new LinkedHashMap<>();
	public ADRecord(Record record) {
		RecordFormat recordFormat = record.getRecordFormat();
		for (FieldDescription fieldDescription : recordFormat.getFieldDescriptions()) {
			String name = fieldDescription.getTEXT();
			Object value;
			try {
				value = record.getField(fieldDescription.getFieldName());
			} catch (UnsupportedEncodingException e) {
				throw new MappingException(e.getLocalizedMessage(), e);
			}
			switch (fieldDescription.getDataType().getInstanceType()) {
			case AS400DataType.TYPE_TEXT:
				value = value.toString().stripTrailing();
			default:
				break;
			}
			adField.put(name, value);
		}
	}
}