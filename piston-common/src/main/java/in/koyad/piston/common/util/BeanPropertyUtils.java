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
package in.koyad.piston.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanPropertyUtils {

	private static final LogUtil LOGGER = LogUtil.getLogger(BeanPropertyUtils.class);
	
	public static void copyProperties(Object dest, Object src) {
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception ex) {
//			LOGGER.logException(ex);
			// throw new FrameworkException(ErrorCodes.FWKMISC003);
		}
	}
	
	public static Object getProperty(Object bean, String nestedProperty) {
		Object value = null;
		try {
			value = PropertyUtils.getNestedProperty(bean, nestedProperty);
		} catch (Exception ex) {
			//Don't print exception
//			LOGGER.logException(ex);
		}
		return value;
	}
}
