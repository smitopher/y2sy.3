package com.cmfirsttech.y2.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.bean.Y2Token;
import com.cmfirsttech.y2.api.mapper.MappingException;
import com.cmfirsttech.y2.api.model.internal.ActionDiagram;
import com.ibm.as400.access.AS400FileRecordDescription;
import com.ibm.as400.access.FieldDescription;
import com.ibm.as400.access.KeyedFile;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;

@ApplicationScoped
public class ActionDiagramImport {

	private Logger logger = Logger.getLogger(getClass());

	@Inject
	JT400 jt400;

	@Inject
	Y2Token y2Token;

	public ActionDiagram readActionDiagram(Integer functionId) throws MappingException {
		try {
			String path = QSYSObjectPathName.toPath(y2Token.library, y2Token.fileForActionDiagram, "FILE");
			AS400FileRecordDescription rfd = new AS400FileRecordDescription(jt400.getAS400(), path);
			KeyedFile file = new KeyedFile(jt400.getAS400(), path);
			ActionDiagram actionDiagram = new ActionDiagram();
			for (RecordFormat recordFormat : rfd.retrieveRecordFormat()) {
				file.setRecordFormat();
				List<Record> recordList = filterRecords(functionId, file.readAll());
				actionDiagram.addRecords(recordFormat, recordList);
			}
			return actionDiagram;
		} catch (Exception e) {
			throw new MappingException(e.getLocalizedMessage(), e);
		}
	}

	private List<Record> filterRecords(Integer functionId, Record[] records) {
		return Arrays.stream(records).filter(r -> filterRecord(functionId, r)).collect(Collectors.toList());
	}

	private boolean filterRecord(Integer functionId, Record record) {
		boolean include = false;
		try {
			Integer messageSurrogate = ((BigDecimal)record.getField("@@MSG")).intValue();
			include = messageSurrogate.equals(functionId);
		} catch (UnsupportedEncodingException e) {
			throw new MappingException(e.getLocalizedMessage(), e);
		}
		return include;
	}

}
