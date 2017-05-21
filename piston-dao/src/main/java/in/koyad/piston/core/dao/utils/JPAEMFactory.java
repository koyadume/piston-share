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
package in.koyad.piston.core.dao.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import in.koyad.piston.common.basic.StringUtil;
import in.koyad.piston.common.basic.exception.ErrorCodes;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;

/**
*
* 
* @author shailendra
* @since 1.0
*/
public class JPAEMFactory {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(JPAEMFactory.class);
	private static Map<String, EntityManagerFactory> emfs = new HashMap<>();
	
	/**
	 * 
	 * @param persistentUnits - comma seperated list of persistent units
	 */
	public static void initialize(String persistentUnits) {
		LOGGER.info("Initializing JPA EntityManagerFactory ..");
		List<String> units = Arrays.asList(StringUtil.split(persistentUnits, ","));

		for(String unit : units) {
			emfs.put(unit, Persistence.createEntityManagerFactory(unit));
		}
	}	

	public static EntityManager createEntityManager(String persistentUnit) throws FrameworkException {
		if(emfs.containsKey(persistentUnit)) {
			return emfs.get(persistentUnit).createEntityManager();
		}
		throw new FrameworkException(ErrorCodes.EMFACTORY_NOT_INITIALIZED);
	}
	
	public static void close() {
		LOGGER.info("Closing JPA EntityManagerFactory ..");
		for(EntityManagerFactory emf : emfs.values()) {
			emf.close();
		}
	}
	
}
