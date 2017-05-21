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

/**
 *
 *
 * @author Shailendra Singh
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.koyad.piston.common.util.LogUtil;

public class JDBCObjectsWrapper {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(JDBCObjectsWrapper.class);
	
	private ResultSet resultSet = null;
	private Statement statement = null;
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public void close() {
		if(null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				LOGGER.logException(ex);
			}
		}
		
		if(null != statement) {
			try {
				statement.close();
			} catch (SQLException ex) {
				LOGGER.logException(ex);
			}
		}
	}
	
}

