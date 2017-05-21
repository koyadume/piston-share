/*
 * Copyright 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.koyad.piston.common.util.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;

/**
*
*
* @author shailendra
*/
public class DBManager {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(DBManager.class);
	
//	private static String getDBURL() {
//		String dbURL = null;
//		
//		if(null != System.getenv("PISTON_DB")) {
//			dbURL = "jdbc:derby:" + System.getenv("PISTON_DB") + "/pistonDb";
//		} else {
//			dbURL = "jdbc:derby:" + System.getProperty("user.home") + "/pistonDb";
//		}
//		
//		return dbURL;
//	}
	
	public static Connection getConnection() throws FrameworkException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/pistonDB");
			
			conn = ds.getConnection();
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException("Unable to create connection to DB.");
		}
		
		return conn;
	}
	
	public static Connection getConnection(String url, String username, String password) throws FrameworkException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ex.getMessage());
		}
		return conn;
	}
	
	public static JDBCObjectsWrapper execute(String query, Connection conn) {
		LOGGER.enterMethod("execute");
		
		JDBCObjectsWrapper jdbcObjectsWrapper = new JDBCObjectsWrapper();
		try {
			Statement stmt = conn.createStatement();
			jdbcObjectsWrapper.setStatement(stmt);
			
			boolean result = stmt.execute(query);
			LOGGER.info("Result  : " + result);
			
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		
		LOGGER.exitMethod("execute");
		return jdbcObjectsWrapper;
	}
	
	public static JDBCObjectsWrapper executeUpdate(String query, Connection conn) {
		LOGGER.enterMethod("executeUpdate");
		
		JDBCObjectsWrapper jdbcObjectsWrapper = new JDBCObjectsWrapper();
		try {
			Statement stmt = conn.createStatement();
			jdbcObjectsWrapper.setStatement(stmt);
			
			int result = stmt.executeUpdate(query);
			LOGGER.info("Result  : " + result);
			
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		
		LOGGER.exitMethod("executeUpdate");
		return jdbcObjectsWrapper;
	}
	
	public static JDBCObjectsWrapper executeQuery(String query, Connection conn) {
		LOGGER.enterMethod("executeQuery");
		
		JDBCObjectsWrapper jdbcObjectsWrapper = new JDBCObjectsWrapper();
		try {
			Statement stmt = conn.createStatement();
			jdbcObjectsWrapper.setStatement(stmt);
			
			ResultSet rs = stmt.executeQuery(query);
			jdbcObjectsWrapper.setResultSet(rs);
			
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		
		LOGGER.exitMethod("executeQuery");
		return jdbcObjectsWrapper;
	}
	
//	public static void shutdownDB() {
//		try {
//			
//			String shutDownURL = getDBURL() + ";shutdown=true";
//			LOGGER.info("URL for shutdown DB : " + shutDownURL);
//			DriverManager.getConnection(shutDownURL);
//			
//		} catch (SQLException ex) {
//			//ignore exception
//		}
//	}

}

