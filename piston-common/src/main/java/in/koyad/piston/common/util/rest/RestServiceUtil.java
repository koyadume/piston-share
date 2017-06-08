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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.sse.SseFeature;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.basic.StringUtil;
import in.koyad.piston.common.bo.Result;
import in.koyad.piston.common.util.LogUtil;

public abstract class RestServiceUtil {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(RestServiceUtil.class);
	
	private static Result invokeService(URI uri, String method, Object reqData, String contentType) throws FrameworkException {
		int responseCode = 0;
		InputStream is = null;
		String resContentType = null;
		HttpURLConnection httpConn = null;
		try {
			String endPoint = uri.toASCIIString();
			LOGGER.info("End point : " + endPoint);
			
			httpConn = (HttpURLConnection) new URL(endPoint).openConnection();
	
			httpConn.setRequestMethod(method);
			
//			if(method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT)) {
//				httpConn.setRequestProperty("Content-Type", (null != contentType) ? contentType : MediaType.APPLICATION_JSON);
//				httpConn.setDoInput(true);
//				httpConn.setDoOutput(true);
//			}
			
//			httpConn.setRequestProperty("Accept", RestContants.CONTENT_TYPE_JSON);
			contentType = (null != contentType) ? contentType : MediaType.APPLICATION_JSON;
			if(null != reqData && (method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT))) {
				httpConn.setRequestProperty("Content-Type", contentType);
				httpConn.setDoInput(true);
				httpConn.setDoOutput(true);
				
				try(final OutputStream reqStream = new DataOutputStream(httpConn.getOutputStream ())) {
					if(reqData instanceof InputStream) {
						IOUtils.copy((InputStream)reqData, reqStream);
					} else {
						if(contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
							reqStream.write(JsonProcessor.getAsString(reqData).getBytes());
						} else if(contentType.equalsIgnoreCase(MediaType.TEXT_PLAIN)) {
							reqStream.write(((String)reqData).getBytes());
						}
					}
				}
			}
			
			responseCode = httpConn.getResponseCode();
			try {
				is = httpConn.getInputStream();
			} catch(IOException ex) {
				is = httpConn.getErrorStream();
			}
			resContentType = httpConn.getContentType();
		} catch (Exception ex) {
			LOGGER.logException(ex);
			
			throw new FrameworkException("Error occured while invoking the service.");
		}
		
		return new Result(responseCode, is, resContentType);
	
	}
//	
//	public static URI getURI(String hostName, String resource, String query) throws URISyntaxException {
//		return new URI("http", hostName, resource, query, null);
//	}
//	
//	//GET
//	public static Result get(URI uri) throws FrameworkException {
//		return get(uri, null);
//	}
	
	//GET
	public static Result get(URI uri) throws FrameworkException {
		Result result =  invokeService(uri, HttpMethod.GET, null, null);
		return result;
	}
	
	private static void logReq(Object reqData, String contentType) {
		if(LOGGER.isDebugEnabled() && !StringUtil.isEmpty(contentType)) {
			if(contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
				LOGGER.debug("Request Body : \n" + JsonProcessor.prettyPrint(reqData));
			} else if(contentType.equalsIgnoreCase(MediaType.TEXT_PLAIN)) {
				LOGGER.debug("Request Body : \n" + reqData);
			}
		}
	}
//	
	//POST
	public static Result post(URI uri, Object reqData, String contentType) throws FrameworkException {
		logReq(reqData, contentType);
		
		Result result =  invokeService(uri, HttpMethod.POST, reqData, contentType);
		return result;
	}
//	
//	//PUT
//	public static Result put(URI uri, Object reqData, String contentType) throws FrameworkException {
//		logReq(reqData, contentType);
//		
//		Result result =  invokeService(uri, HttpMethod.PUT, reqData, contentType);
//		return result;
//	}
//	
	//DELETE
	public static Result delete(URI uri) throws FrameworkException {
		Result result =  invokeService(uri, HttpMethod.DELETE, null, null);
		return result;
	}
	
	public static Client getClient() {
		ClientConfig config = new ClientConfig();
//		config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
//		config.property(ClientProperties.READ_TIMEOUT, 5000);
		
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		jsonProvider.setMapper(JsonProcessor.getMapper());
		config.register(jsonProvider);
		config.register(SseFeature.class);
		
		Client client = ClientBuilder.newClient(config);
		return client;
	}

}
