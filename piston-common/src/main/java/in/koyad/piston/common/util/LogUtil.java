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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	private Logger logger;
	
	private LogUtil(Logger logger){
		this.logger = logger;
	}
	
	public void enterMethod(String methodName){
		logger.info("Entering " + methodName + "() ==========>");
	}
	
	public void exitMethod(String methodName){
		logger.info("<========== Leaving " + methodName + "()");
	}

	public static LogUtil getLogger(Class<?> cls) {
		return new LogUtil(LoggerFactory.getLogger(cls));
	}

	public void debug(String msg) {
		logger.debug(msg);
	}
	
	public void info(String msg) {
		logger.info(msg);
	}
	
	public void error(String msg) {
		logger.error(msg);
	}
	
	public void logException(Exception ex) {
		logger.error(ex.getMessage(), ex);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
}
