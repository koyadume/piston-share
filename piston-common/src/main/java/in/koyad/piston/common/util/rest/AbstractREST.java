package in.koyad.piston.common.util.rest;

public abstract class AbstractREST {
	
//	private static final LogUtil LOGGER = LogUtil.getLogger(AbstractREST.class);
//	protected static final String SERVICE_HOST = "localhost";
//	protected static final int SERVICE_PORT = 8080;
	
//	protected Client RestServiceUtil.getClient() {
//		ClientConfig config = new ClientConfig();
//		config.property(ClientProperties.CONNECT_TIMEOUT, 10000);
//		config.property(ClientProperties.READ_TIMEOUT, 10000);
//		
//		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
//		jsonProvider.setMapper(JsonProcessor.getMapper());
//		config.register(jsonProvider);
//		
//		Client client = ClientBuilder.newClient(config);
//		return client;
//	}
	
//	protected <T> T get(String resource, Class<T> resClass) throws URISyntaxException, FrameworkException, MalformedURLException {
//		return get(resource, null, resClass);
//	}
//	
//	protected <T> T get(String resource, String query, Class<T> resClass) throws MalformedURLException, FrameworkException, URISyntaxException {
//		return get(resource, query, resClass, null);
//	}
//	
//	protected <T> T get(String resource, String query, Class<T> resClass, String contentType) throws MalformedURLException, FrameworkException, URISyntaxException {
//		String path = (null == query)? resource : resource.concat("?").concat(query);
//		URL url = new URL("http", SERVICE_HOST, SERVICE_PORT, path);
//		
//		Result result = RestServiceUtil.get(url.toURI(), contentType);
//		LOGGER.debug("Response code : " + result.getCode());
//		
//		if(result.getCode() != 200) {
//			throw new FrameworkException("Internal error occured.");
//		}
//
//		String response = result.getBody();
//		
//		if(result.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
//			return JsonProcessor.getAsObject(response, resClass);
//		}
//		
//		return (T)response;
//	}
	
//	protected void post(String resource, Object reqBody) throws URISyntaxException, FrameworkException, MalformedURLException {
//		URL url = new URL("http", SERVICE_HOST, SERVICE_PORT, resource);
//		
//		Result result = RestServiceUtil.post(url.toURI(), reqBody, MediaType.APPLICATION_JSON); 
//		LOGGER.debug("Response code : " + result.getCode());
//	}
//	
//	protected void put(String resource, Object reqBody) throws URISyntaxException, FrameworkException, MalformedURLException {
//		put(resource, reqBody, MediaType.APPLICATION_JSON); 
//	}
//	
//	protected void put(String resource, Object reqBody, String contentType) throws URISyntaxException, FrameworkException, MalformedURLException {
//		URL url = new URL("http", SERVICE_HOST, SERVICE_PORT, resource);
//		
//		Result result = RestServiceUtil.put(url.toURI(), reqBody, contentType); 
//		LOGGER.debug("Response code : " + result.getCode());
//	}
	
//	protected void delete(String resource, String query) throws URISyntaxException, FrameworkException, MalformedURLException {
//		String path = (null == query)? resource : resource.concat("?").concat(query);
//		URL url = new URL("http", SERVICE_HOST, SERVICE_PORT, path);
//		
//		Result result = RestServiceUtil.delete(url.toURI());
//		LOGGER.debug("Response code : " + result.getCode());
//	}
	
	public static void main(String[] args) {
		try {
//			// case 1 - Content-Type: application/json
//			URI uri1 = new URI("http", null, "localhost", 8080, "/piston-service/v2.0/portal/frames", null, null);
//			System.out.println("URI : " + uri1.toASCIIString());
//			
//			Result result1 = RestServiceUtil.get(uri1);
//			
//			System.out.println("Response code : " + result1.getCode());
//			if(result1.getCode() == 200) {
//				System.out.println("Response -");
//				System.out.println(JsonProcessor.prettyPrint(result1.getBody()));
//			}
//			
//			// case 2 - Content-Type: text/plain
//			URI uri2 = new URI("http", null, "localhost", 8080, "/piston-service/v2.0/appConfig/allUsersGroupDN", null, null);
//			System.out.println("URI : " + uri2.toASCIIString());
//			
//			Result result2 = RestServiceUtil.get(uri2);
//			
//			System.out.println("Response code : " + result2.getCode());
//			if(result2.getCode() == 200) {
//				System.out.println("Response : " + result2.getBody());
//			}
			
//			URL url1 = new URL("http", "localhost", 8080, "/piston-service/v2.0/appConfig/allUsersGroupDN?param=value");
//			System.out.println(url1.toURI());
//			
//			URL url2 = new URL("http", "piston.koyad.in", -1, "/piston-service/v2.0/appConfig/allUsersGroupDN?param=value");
//			System.out.println(url2.toURI());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
