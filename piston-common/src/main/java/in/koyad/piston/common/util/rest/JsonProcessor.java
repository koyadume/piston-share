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
package in.koyad.piston.common.util.rest;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import in.koyad.piston.common.util.LogUtil;

public class JsonProcessor {
	
	private static final ObjectMapper resourceMapper;
	
	static {
//		SimpleModule module = new SimpleModule()
//									.setDeserializerModifier(new BeanDeserializerModifier() {
//										@Override
//										public JsonDeserializer<?> modifyDeserializer(
//												DeserializationConfig config, BeanDescription beanDesc,
//												JsonDeserializer<?> deserializer) {
//											Class beanClass = beanDesc.getBeanClass();
//											if (AbstractEntity.class.isAssignableFrom(beanClass)) {
//												return new EntityDeserializer(deserializer);
//											}
//											return deserializer;
//										}
//									});

		resourceMapper = new ObjectMapper();
//		resourceMapper.registerModule(module);
	}

	private static final LogUtil LOGGER = LogUtil.getLogger(JsonProcessor.class);

	public static String getAsString(Object obj) {
		String json = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(obj);
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		
		return json;
	}
	
	public static String prettyPrint(Object obj) {
		String formattedJson = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

			formattedJson = writer.writeValueAsString(obj);
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new RuntimeException("Json serialization error.");
		}
		
		return formattedJson;
	}
	
	public static <T> T getAsObject(String json, Class<T> objClass) {
		T jsonObj = null;
		try {
			jsonObj = resourceMapper.readValue(json, objClass);
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		return jsonObj;
	}
	
	public static <T> List<T> getAsList(String json, Class<T> objClass) {
		List<T> list = null;
		try {
			list = resourceMapper.readValue(json, 
						resourceMapper.getTypeFactory().constructCollectionType(List.class, objClass));
		} catch (Exception ex) {
			LOGGER.logException(ex);
		}
		return list;
	}
	
	public static ObjectMapper getMapper() {
		return resourceMapper;
	}
}
