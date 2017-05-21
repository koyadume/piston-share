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

import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

import in.koyad.piston.common.basic.exception.ErrorCodes;
import in.koyad.piston.common.basic.exception.FrameworkException;

public class CacheUtil {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(CacheUtil.class);
	
	public static <K, T> T getCacheEntry(LoadingCache<K, T> cache, K key) throws FrameworkException {
		T value = null;
		try {
			//Below line is only for testing in dev environment
//			cache.refresh(key);
			
			value  = cache.get(key);
		} catch (ExecutionException ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ErrorCodes.LOADING_CACHE_ERROR);
		}
		return value;
	}

}
