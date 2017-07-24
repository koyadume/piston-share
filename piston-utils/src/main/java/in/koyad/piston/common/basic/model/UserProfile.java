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
package in.koyad.piston.common.basic.model;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author shailendra
 * @since 1.0
 */
public interface UserProfile {
	
	public String getId();

	// this returns external id of user
	public String getUid();
	
	public boolean isPortalAdmin();
	
	public Map<String, String> getExternalAttributes();
	
	/**
	 * key - namespace (e.g. AWS )
	 * value - attributes map
	 * @return
	 */
	public Map<String, Map<String, String>> getInternalAttributes();
	
	public Set<String> getGroups();
	
}
