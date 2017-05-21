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

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.core.dao.context.ORMContext;
import in.koyad.piston.core.dao.context.ServiceContext;

/**
 *
 * 
 * @author Shailendra Singh
 * @since 1.0
 */
public abstract class BaseDAO {

	private String persistentUnit;
	
	protected BaseDAO(String persistentUnit) {
		this.persistentUnit = persistentUnit;
	}
	
	protected ORMContext getORMContext() throws FrameworkException {
		ORMContext ormCtx = ServiceContext.getInstance().getORMContext();
		if(null == ormCtx) {
			ormCtx = ORMContext.getInstance(this, persistentUnit);
		}
		return ormCtx;
	}

}
