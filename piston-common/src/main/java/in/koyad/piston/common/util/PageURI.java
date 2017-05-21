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

import java.util.Arrays;

import in.koyad.piston.common.basic.StringUtil;
import in.koyad.piston.common.basic.exception.ErrorCodes;
import in.koyad.piston.common.basic.exception.FrameworkException;
import lombok.Data;
import lombok.Getter;

@Data
public class PageURI {
	
	@Getter
	private String siteMapping = null;
	
	@Getter
	private String[] pageMappings = null;
	
	public PageURI(String reqURI) throws FrameworkException {
		String path = StringUtil.removeTrailingCharacter(reqURI.substring(1), '/');
		String[] pathTokens = path.split("/");
		pathTokens = Arrays.copyOfRange(pathTokens, 2, pathTokens.length);
		
		boolean invalidURI = false;
		if(pathTokens.length == 0) {
			invalidURI = true;
		} else {
			if(pathTokens[0].startsWith(MappingConstants.PREFIX_SITE)) {
				siteMapping = pathTokens[0].split("-")[1];
				if(pathTokens.length > 1) {
					pageMappings = Arrays.copyOfRange(pathTokens, 1, pathTokens.length);
				}
			} else {
				invalidURI = true;
			}
			
		}
		
		if(invalidURI) {
			throw new FrameworkException(ErrorCodes.INVALID_PORTAL_PAGE_URI);
		}
	}
	
//	public static class PageMapping {
//		private int level;
//		private String mapping;
//		
//		public PageMapping(int level, String mapping) {
//			this.level = level;
//			this.mapping = mapping;
//		}
//		
//		public int getLevel() {
//			return level;
//		}
//		
//		public String getMapping() {
//			return mapping;
//		}
//	}
	
//	public PageMapping getLastPageMapping() {
//		String[] mappings = pagePath.split("/");
//		return new PageMapping(mappings.length, mappings[mappings.length-1]);
//	}
//
//	public String getSiteMapping() {
//		return siteMapping;
//	}
//
//	public String getPagePath() {
//		return pagePath;
//	}
	
}
