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
package in.koyad.piston.common.basic;

import java.util.regex.Pattern;

public class StringUtil {
	
	public static String removeTrailingCharacter(String src, Character ch) {
		int lastIndex = src.length() - 1;
		while(src.charAt(lastIndex) == ch) {
			lastIndex--;
		}
		
		return src.substring(0, lastIndex + 1);
	}
	
	public static boolean isEmpty(String var) {
		return (null == var || var.isEmpty()) ? true : false;
	}
	

	public static String removeLeadingCharacter(String src, Character ch) {
		int index = 0;
		while(src.charAt(index) == ch) {
			index++;
		}
		
		return src.substring(index);
	}
	
	public static String trim(String src, Character ch) {
		String dest = removeLeadingCharacter(src, ch);
		dest = removeTrailingCharacter(dest, ch);
		return dest;
	}
	
	public static String decorate(String src, String decorate) {
		StringBuilder builder = new StringBuilder();
		builder.append(decorate);
		builder.append(src);
		builder.append(decorate);
		return builder.toString();
	}
	
	public static String[] split(String str, String expr) {
		return str.split(Pattern.quote(expr));
	}
	
	public static String getQueryParamValue(String queryString, String param) {
		if(null != queryString) {
			String[] queryParameters = queryString.split("&");
			
			for(String queryParameter : queryParameters) {
				String[] paramValue = queryParameter.split("=");
				if(paramValue[0].equalsIgnoreCase(param)) {
					if(paramValue.length == 2) {
						return paramValue[1];
					} else {
						return "";
					}
				}
			}
		}
		return null;
	}
	
}
