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

import in.koyad.piston.common.bo.Node;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * A hybrid data structure.
 */
@Getter
public class TreeMap<T> {
	
	private List<Node<T>> rootNodes = null;
	
	private Map<String, Node<T>> map = null;
	
	private TreeMap(List<Node<T>> rootNodes, Map<String, Node<T>> map) {
		this.rootNodes = Collections.unmodifiableList(rootNodes);
		this.map = Collections.unmodifiableMap(map);
	}

	public static<T> TreeMap<T> getInstance(List<T> objects, String idProperty, String parentIdProperty) {
		Map<String, Node<T>> map = new HashMap<>();
		List<Node<T>> rootNodes = new LinkedList<>();
		
		for(T obj : objects) {
			//store reference to each node in a map
			Node<T> node = new Node<T>(obj);
			map.put((String)BeanPropertyUtils.getProperty(obj, idProperty), node);
		}
		
		for(T obj : objects) {
			String id = (String)BeanPropertyUtils.getProperty(obj, idProperty);
			String parentId = (String)BeanPropertyUtils.getProperty(obj, parentIdProperty); 
			if(null == parentId){
				rootNodes.add(map.get(id));
			} else {
				Node<T> parent = map.get(parentId);
				Node<T> child = map.get(id);
				parent.addChild(child);
				child.setParent(parent);
				
			}
		}
		
		return new TreeMap<T>(rootNodes, map);
	}
	
	public Node<T> get(String key) {
		return map.get(key);
	}
}
