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
package in.koyad.piston.common.model;

import java.util.Map;
import java.util.Set;

import in.koyad.piston.common.basic.model.UserProfile;
import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author Shailendra Singh <shailendra_01@outlook.com>
 * @since 1.0
 */
@Getter 
@Builder
public class UserProfileImpl implements UserProfile {
	
	private String id;
	private String uid;
	private boolean portalAdmin;
	private Map<String, String> externalAttributes;
	private Map<String, Map<String, String>> internalAttributes;
	private Set<String> groups;
	
}
