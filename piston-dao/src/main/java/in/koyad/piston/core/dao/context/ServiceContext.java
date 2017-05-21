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
package in.koyad.piston.core.dao.context;

import java.text.MessageFormat;

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;

/**
 * 
 * @author Shailendra Singh <shailendra_01@outlook.com>
 * @since 1.0
 */
public class ServiceContext {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ServiceContext.class);
	
	private static final String CONTEXT_ALREADY_INITIALIZED = "{0} is alreay initialized.";
	
	private ORMContext ormContext = null;

	private static ThreadLocal<ServiceContext> context = new ThreadLocal<ServiceContext>() {
															@Override
															protected ServiceContext initialValue() {
																return new ServiceContext();
															};
														};
														
	public static ServiceContext getInstance() {
		return context.get();
	}
	
	public static void removeContext() {
		context.remove();
	}
	
	public void initORMContext(ORMContext ormCtx) throws FrameworkException {
		if (null != ormCtx) {
			throw new FrameworkException(MessageFormat.format(CONTEXT_ALREADY_INITIALIZED, "ORM Context"));
		} else {
			ormContext = ormCtx;
		}
	}
	
	public ORMContext getORMContext() {
		return ormContext;
	}
	
	public void clearORMContext(Object owner) throws FrameworkException {
//		Object ormCtxOwner = BeanPropertyUtils.getProperty(ormContext, "owner");
		if(ormContext.getOwner() == owner) {
			LOGGER.info("Removing orm context ..");
			try{
				ormContext.getClass().getMethod("dispose", Object.class).invoke(ormContext, owner);
			} catch(Exception ex) {
				LOGGER.logException(ex);
			}
		}
	}

}
