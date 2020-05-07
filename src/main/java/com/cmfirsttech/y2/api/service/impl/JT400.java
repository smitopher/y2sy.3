/*******************************************************************************
 * Copyright 2020 christopher.smith
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.cmfirsttech.y2.api.service.impl;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.Table;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.bean.JtToken;
import com.cmfirsttech.y2.api.bean.Y2Token;
import com.cmfirsttech.y2.api.entity.impl.Y2ModelListEntry;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.SecureAS400;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class JT400 {
	
	private Logger logger = Logger.getLogger(getClass());
	private CommandCall cmdCall;
	private ZoneId zoneId;
	
	public static final String CHGLIBL_FORMAT = "CHGLIBL LIBL(%1$s)";
	public static final String OVRDBF_CMD = "OVRDBF FILE(%1$s) TOFILE(%2$s/%3$s) MBR(%4$s) OVRSCOPE(*JOB)";
	public static final String QCMDEXEC_CMD = "CMD";
	public static final String QCMDEXEC_LEN = "CMD_LEN";
	public static final String QCMDEXEC_STORED_PROCEEDURE = String.format("QSYS.QCMDEXC", 
			QCMDEXEC_CMD, QCMDEXEC_LEN);
	public static final String ALIAS_FORMAT = 
			"CREATE OR REPLACE ALIAS %1$s.%2$s FOR %3$s.%4$s (%5$s)";

	@Inject
	JtToken jtToken;
	
	@Inject
	Y2Token y2Token;
	
	@ConfigProperty(name = "as400host")
	String as400host;

	@ConfigProperty(name = "as400user")
	String as400user;
	
	@ConfigProperty(name = "as400password")
    String as400password;

	@ConfigProperty(name = "quarkus.hibernate-orm.database.default-schema")
	String dbSchema;
	
	private AS400 defaultAs400;

	private AS400JDBCDriver jdbcDriver = new AS400JDBCDriver();
	
	public AS400 getAS400() {
		return defaultAs400;
	}
	
	public Connection getJdbcConnection() {
		return getJdbcConnection(defaultAs400, new Properties(), dbSchema);
	}
	
	public Connection getJdbcConnection(AS400 as400, Properties properties, String schema) {
		as400 = Objects.requireNonNull(as400);
		properties = Objects.requireNonNull(properties);
		schema = Objects.requireNonNull(schema);
		try {
			return jdbcDriver.connect(as400, properties, schema);
		} catch (SQLException e) {
			logger.error("", e);
			throw new JT400Exception(e.getLocalizedMessage(), e);
		}
	}
	
	public boolean runCmd(String cmd) {
		if (logger.isDebugEnabled()) {
			logger.debug("Running AS400 command " + cmd);
		}
		boolean callSuccess = false;
		try {
			callSuccess = cmdCall.run(cmd);
			if (!callSuccess) {
				for (AS400Message msg : cmdCall.getMessageList()) {
					logger.error(msg.getText());
				}
				logger.error("Command " + cmd);
			}
			return callSuccess;
		} catch (AS400SecurityException | ErrorCompletingRequestException | IOException | InterruptedException
				| PropertyVetoException e) {
			logger.error("Error executing AS400 command", e);
			throw new JT400Exception(e.getLocalizedMessage(), e);
		}
	}
	
	public ZoneId getZoneId() {
		return zoneId;
	}
	
	void onStart(@Observes StartupEvent ev) {
		init();
	}
	
	public String createAliasString() {
		String db2alias = Y2ModelListEntry.class.getAnnotation(Table.class).name();
		return String.format(ALIAS_FORMAT, dbSchema, db2alias, dbSchema, y2Token.fileForAlias, y2Token.listName);
	}
	
	private void init() {
		if (jtToken.useSsl) {
			defaultAs400 = new SecureAS400(as400host, as400user, as400password);
		} else {
			defaultAs400 = new AS400(as400host, as400user, as400password);
		}
		try {
			defaultAs400.connectService(AS400.SIGNON);
			zoneId = defaultAs400.getTimeZone().toZoneId();
		} catch (AS400SecurityException | IOException | ErrorCompletingRequestException | InterruptedException
				| ObjectDoesNotExistException e) {
			String message = String.format("Unable to connect to AS400 host name '%1$s'", as400host);
			logger.fatal(message, e);
			Quarkus.asyncExit(1);
		}
		cmdCall = new CommandCall(defaultAs400);
		String createAlias = createAliasString();
		try {
			Connection conn = getJdbcConnection();
			PreparedStatement ps = conn.prepareStatement(createAlias);
			ps.execute();
		} catch (Exception e) {
			logger.error(createAlias);
			logger.error("Error create Model List alias" + '\n' + createAlias, e);
		}
	}
	
}
