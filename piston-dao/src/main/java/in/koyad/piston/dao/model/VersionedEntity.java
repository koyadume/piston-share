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
package in.koyad.piston.dao.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter 
@Setter
public abstract class VersionedEntity extends AbstractEntity {

	@Version
	private int version;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof VersionedEntity) {
			return getId().equals(((VersionedEntity)obj).getId()) && (version == ((VersionedEntity)obj).version);
		}
		return false;
	}
	
	@Override
	public void refresh(AbstractEntity newData) {
		super.refresh(newData);
		this.version = ((VersionedEntity)newData).getVersion();
	}
	
}
