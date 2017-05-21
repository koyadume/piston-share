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

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class AbstractEntity {

	@Id
	@Getter @Setter
	private String id = "";
	
	@PrePersist
	protected void generateId() {
		if(id == "") {
			id = UUID.randomUUID().toString();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractEntity) {
			return id.equals(((AbstractEntity)obj).id);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(!id.equals("")) {
			return id.hashCode();
		}
		
		return super.hashCode();
	}
	 
	public void postJsonConstruct() {

	}
	
	public void refresh(AbstractEntity newData) {
		this.id = newData.id;
	}
}
