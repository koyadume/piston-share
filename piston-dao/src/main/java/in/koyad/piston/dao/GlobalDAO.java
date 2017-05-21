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
package in.koyad.piston.dao;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import in.koyad.piston.common.basic.StringUtil;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;
import in.koyad.piston.common.util.jpa.EntityUtil;
import in.koyad.piston.core.dao.context.ORMContext;
import in.koyad.piston.core.dao.utils.DBConstants;
import in.koyad.piston.dao.model.AbstractEntity;
import in.koyad.piston.dao.model.Sequence;

/**
*
* 
* @author Shailendra Singh
* @since 1.0
*/
public class GlobalDAO extends BaseDAO {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(GlobalDAO.class);
	
	public GlobalDAO() {
		super(DBConstants.PERSISTENT_UNIT_PISTON);
	}
	
	public GlobalDAO(String persistentUnit) {
		super(persistentUnit);
	}
	
	public <T extends AbstractEntity> void insert(T obj) throws FrameworkException {
		LOGGER.enterMethod("insert");

		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			ormCtx.persist(obj);
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("insert");
	}
	
	public <T extends AbstractEntity> void insertAll(List<T> objects) throws FrameworkException {
		LOGGER.enterMethod("insertAll");

		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			for(T obj : objects) {
				ormCtx.persist(obj);
			}
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("insertAll");
	}
	
	public <T extends AbstractEntity> T get(Class<T> objClass, String id, String entityGraphName) throws FrameworkException {
		LOGGER.enterMethod("get");

		T entity = null;
		
		ORMContext ormCtx = getORMContext();
		try {
			if(StringUtil.isEmpty(entityGraphName)) {
				entity = ormCtx.find(objClass, id);
			} else {
				entity = ormCtx.find(objClass, id, entityGraphName);
			}
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("get");
		return entity;
	}
	
	public <T extends AbstractEntity> T get(Class<T> objClass, String id) throws FrameworkException {
		return get(objClass, id, null);
	}
	
	private String getParamName(String paramName) {
		//this is to resolve embedded entity properties
		if(paramName.contains(".")) {
			return paramName.replaceAll(Pattern.quote("."), "");
		}
		
		return paramName;
	}
	
	private void setConditions(StringBuilder builder, Map<String, Object> conditions) {
		if(null != conditions) {
			builder.append(" where ");
			
			int i=0;
			for(String key : conditions.keySet()) {
				if(i > 0) {
					builder.append(" AND ");
				}
				
				String paramName = key;
				String paramNamePlaceHolder = getParamName(key);
				Object value = conditions.get(key);
				
				if(null == value) {
					builder.append(
								MessageFormat.format("entity.{0} is NULL", paramName)
							);
				} else {
					if(value instanceof List) {
						builder.append(
									MessageFormat.format("entity.{0} IN :{1}", paramName, paramNamePlaceHolder)
								);
					} else if(value instanceof String) {
						String val = (String)value;
						if(val.contains("*")) {
							builder.append(
										MessageFormat.format("entity.{0} like :{1}", paramName, paramNamePlaceHolder)
									);
						} else {
							builder.append(
										MessageFormat.format("entity.{0} = :{1}", paramName, paramNamePlaceHolder)
									);
						}
					} else {
						builder.append(
								MessageFormat.format("entity.{0} = :{1}", paramName, paramNamePlaceHolder)
							);
					}
				}
				i++;
			}
		}
	}
	
	private void setFields(StringBuilder builder, Map<String, Object> fields) {
		if(null != fields) {
			builder.append(" set ");
			
			int i=0;
			for(String key : fields.keySet()) {
				if(i > 0) {
					builder.append(", ");
				}
				
				String paramName = key;
				String paramNamePlaceHolder = getParamName(key);
				builder.append(
							MessageFormat.format("entity.{0}  = :{1}", paramName, paramNamePlaceHolder)
						);

				i++;
			}
		}
	}
	
	private void setParams(Query qry, Map<String, Object> conditions) {
		if(null != conditions) {
			for(String key : conditions.keySet()) {
				if(null != conditions.get(key)) {
					String paramName = getParamName(key);
					
					Object value = conditions.get(key);
					if(value instanceof String) {
						String val = (String)value;
						if(val.contains("*")) {
							val = val.replaceAll(Pattern.quote("*"), "%");
						}
						
						qry.setParameter(paramName, val);
					} else {
						qry.setParameter(paramName, value);
					}
				}
			}
		}
	}

	public <T extends AbstractEntity> List<T> getList(Class<T> objClass, Map<String, Object> conditions, String entityGraphName) throws FrameworkException {
		LOGGER.enterMethod("getList");

		List<T> list = null;
		
		ORMContext ormCtx = getORMContext();
		try {
			StringBuilder builder  = new StringBuilder();
			builder.append("from ").append(objClass.getSimpleName()).append(" entity");
			
			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			TypedQuery<T> qry = ormCtx.createQuery(query, objClass, entityGraphName);
			setParams(qry, conditions);
			
			list = qry.getResultList();
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getList");
		return list;
	}
	
	public <T extends AbstractEntity> List<T> getList(Class<T> objClass, Map<String, Object> conditions) throws FrameworkException {
		return getList(objClass, conditions, null);
	}
	
	public <T extends AbstractEntity> List<T> getList(Class<T> objClass) throws FrameworkException {
		return getList(objClass, null, null);
	}
	
	public <T extends AbstractEntity> List<T> getList(String nativeQuery, Class<T> objClass) throws FrameworkException {
		LOGGER.enterMethod("getList");

		List<T> list = null;
		
		ORMContext ormCtx = getORMContext();
		try {
			Query qry = ormCtx.createNativeQuery(nativeQuery, objClass);
			list = qry.getResultList();
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getList");
		return list;
	}
	
	public List<Object> getSingleFieldValues(Class entity, String field, Map<String, Object> conditions) throws FrameworkException {
		LOGGER.enterMethod("getSingleFieldValues");

		List<Object> values = null;
		
		ORMContext ormCtx = getORMContext();
		try {
			StringBuilder builder  = new StringBuilder();
			builder.append("select ").append("entity." + field)
					.append(" from ").append(entity.getSimpleName()).append(" entity");

			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createQuery(query);
			setParams(qry, conditions);
			
			values = qry.getResultList();
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getSingleFieldValues");
		return values;
	}
	
	public List getSingleColValues(Class entity, String col, Map<String, Object> conditions) throws FrameworkException {
		LOGGER.enterMethod("getSingleColValues");

		List values = null;
		ORMContext ormCtx = getORMContext();
		try {
			StringBuilder builder  = new StringBuilder();
			builder.append("select ").append("entity." + col)
					.append(" from ").append(EntityUtil.getTableName(entity)).append(" entity");

			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createNativeQuery(query);
			setParams(qry, conditions);
			
			values = qry.getResultList();
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getSingleColValues");
		return values;
	}
	
	public List getTableSingleColValues(String table, String col, Map<String, Object> conditions) throws FrameworkException {
		LOGGER.enterMethod("getSingleColValues");

		List values = null;
		ORMContext ormCtx = getORMContext();
		try {
			StringBuilder builder  = new StringBuilder();
			builder.append("select ").append("entity." + col)
					.append(" from ").append(table).append(" entity");

			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createNativeQuery(query);
			setParams(qry, conditions);
			
			values = qry.getResultList();
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getSingleColValues");
		return values;
	}
	
	public int updateFields(Class entity, Map<String, Object> fields, Map<String, Object> conditions) throws FrameworkException {
		LOGGER.enterMethod("updateSingleFieldValue");

		ORMContext ormCtx = getORMContext();
		int recordsUpdated = 0;
		try {
			ormCtx.beginTransaction();
			
			StringBuilder builder  = new StringBuilder();
			builder.append("update ").append(entity.getSimpleName()).append(" entity");
			
			setFields(builder, fields);

			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createQuery(query);
			setParams(qry, fields);
			setParams(qry, conditions);
			
			recordsUpdated = qry.executeUpdate();
			LOGGER.debug("No of records updated : " + recordsUpdated);
			
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("updateSingleFieldValue");
		return recordsUpdated;
	}

	public void updateSingleColumn(Class entity, String colName, String colValue, Map<String, Object> conditions) throws FrameworkException {
	
		LOGGER.enterMethod("updateSingleColumn");

		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			
			StringBuilder builder  = new StringBuilder();
			builder.append("update ").append(EntityUtil.getTableName(entity)).append(" entity")
				.append(" set entity.").append(colName).append(" = :colName");
			
			setConditions(builder, conditions);
			
			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createNativeQuery(query);
			qry.setParameter("colName", colValue);
			setParams(qry, conditions);
			
			int recordsUpdated = qry.executeUpdate();
			LOGGER.debug("No of records updated : " + recordsUpdated);
			
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("updateSingleColumn");
	}
	
	public <T extends AbstractEntity> T update(T newData) throws FrameworkException {
		LOGGER.enterMethod("update");

		ORMContext ormCtx = getORMContext();
		T managed = null;
		try {
			ormCtx.beginTransaction();
			managed = ormCtx.merge(newData);
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("update");
		return managed;
	}
	
	public <T extends AbstractEntity> void updateAll(List<T> objects) throws FrameworkException {
		LOGGER.enterMethod("updateAll");

		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			for(T obj : objects) {
				ormCtx.merge(obj);
			}
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("updateAll");
	}
	
	public <T extends AbstractEntity> void delete(Class<T> objClass, String id) throws FrameworkException {
		LOGGER.enterMethod("delete");

		ORMContext ormCtx = getORMContext();
		T entity = ormCtx.find(objClass, id); 
		
		try {
			ormCtx.beginTransaction();
			ormCtx.remove(entity);
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("delete");
	}
	
	public int getNextSeqValue(String seqName) throws FrameworkException {
		LOGGER.enterMethod("getNextSeqValue");
		
		int value = 0;
		
		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			
			int recordsUpdated = ormCtx.createNativeQuery("update Sequence set value = (value + 1) where name = :seqName")
									.setParameter("seqName", seqName)
									.executeUpdate();
			
			LOGGER.debug("Records updated : " + recordsUpdated);
			ormCtx.flush();
			
			//for testing
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException ex) {
//				ex.printStackTrace();
//			}
			
			value = ormCtx.createQuery("from Sequence where name = :seqName", Sequence.class, null)
						.setParameter("seqName", seqName)
						.getResultList().get(0).getValue();
			
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getNextSeqValue");
		return value;
	}
	
	public int getRecordsCount(String tableName) throws FrameworkException {
		LOGGER.enterMethod("getRecordsCount");
		
		int recordsCount = 0;
		
		ORMContext ormCtx = getORMContext();
		try {
			BigInteger count = (BigInteger)ormCtx.createNativeQuery(MessageFormat.format("select count(*) from {0}", tableName)).getSingleResult();
			
			recordsCount = count.intValue();
			
			LOGGER.debug("Number of records : " + recordsCount);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("getRecordsCount");
		return recordsCount;
	}
	
	public void deleteEntities(Class entity, Map<String, Object> conditions) throws FrameworkException {
		LOGGER.enterMethod("deleteEntities");

		ORMContext ormCtx = getORMContext();
		try {
			ormCtx.beginTransaction();
			
			StringBuilder builder  = new StringBuilder();
			builder.append("delete from ").append(entity.getSimpleName()).append(" entity");
			
			setConditions(builder, conditions);

			String query = builder.toString();
			LOGGER.debug("Query : " + query);
			
			Query qry = ormCtx.createQuery(query);
			setParams(qry, conditions);
			
			int recordsDeleted = qry.executeUpdate();
			LOGGER.debug("No of records deleted : " + recordsDeleted);
			
			ormCtx.commit(this);
		} finally {
			ormCtx.dispose(this);
		}
		
		LOGGER.exitMethod("deleteEntities");
	}
	
}
