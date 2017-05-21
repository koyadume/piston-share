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
package in.koyad.piston.common.enums;

public enum PortalPageType {

	INTERNAL(1), EXTERNAL(2);
	
	private int code;
	
	private PortalPageType(int code) {
		this.code = code;
	}
	
	public static PortalPageType getType(int code) {
		switch (code) {
			case 1:
				return INTERNAL;
			case 2:
				return EXTERNAL;
		}
		return null;
	}
	
	public int getCode() {
		return code;
	}
	
}
