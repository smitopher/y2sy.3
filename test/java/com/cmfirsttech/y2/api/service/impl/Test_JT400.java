package com.cmfirsttech.y2.api.service.impl;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.persistence.Table;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import com.cmfirsttech.y2.api.bean.JtToken;
import com.cmfirsttech.y2.api.bean.Y2Token;
import com.cmfirsttech.y2.api.entity.impl.Y2ModelListEntry;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Test_JT400 {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Inject
	JT400 jt400;
	
	@Inject
	JtToken jtToken;
	
	@Inject
	Y2Token y2Token;
	
	@Test
	public void testJT400() {
		logger.info("Starting JT400 tests");
		logger.info("get AS400 instance");
		assertNotNull(jt400.getAS400());
		logger.info("AS400 instance is connected");
		assertTrue(jt400.getAS400().isConnected());
		logger.info("AS400 time zone fetched");
		assertNotNull(jt400.getZoneId());
		logger.info("JDBC Connection fetched");
		assertNotNull(jt400.getJdbcConnection());
		logger.info("Test execute CHGLIBL");
		String cmd = String.format(JT400.CHGLIBL_FORMAT, y2Token.libl);
		assertTrue(jt400.runCmd(cmd));
		logger.info("Test create model list alias");
		boolean createAliasResult = true;
		String alias = Y2ModelListEntry.class.getAnnotation(Table.class).name();
		String createAlias = String.format(JT400.ALIAS_FORMAT, 
				alias, y2Token.fileForAlias, y2Token.listName);
		try {
			jt400.getJdbcConnection().prepareStatement(createAlias).execute();
		} catch (SQLException e) {
			createAliasResult = false;
		}
		assertTrue(createAliasResult);
	}
	
	

}
