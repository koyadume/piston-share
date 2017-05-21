/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
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

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.basic.exception.ErrorCodes;
import in.koyad.piston.common.util.LogUtil;

public class EntityUtil {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(EntityUtil.class);
	
	public static <T> String getTableName(Class<T> entity) {
		Table anno = entity.getAnnotation(Table.class);
		if(null != anno) {
			return anno.name();
		}
		return entity.getSimpleName();
	}
	
	public static <T> String getColName(Class<T> entity, String field) throws FrameworkException {
		String colName = null;
		try {
			Column anno = entity.getField(field).getAnnotation(Column.class);
			if(null != anno) {
				colName = anno.name();
			}
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ErrorCodes.NO_SUCH_FIELD);
		}
		return colName;
	} 
	
	public static <T> String getJoinColName(Class<T> entity, String field) throws FrameworkException {
		String colName = null;
		try {
			JoinColumn anno = entity.getField(field).getAnnotation(JoinColumn.class);
			if(null != anno) {
				colName = anno.name();
			}
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ErrorCodes.NO_SUCH_FIELD);
		}
		return colName;
	} 
}
