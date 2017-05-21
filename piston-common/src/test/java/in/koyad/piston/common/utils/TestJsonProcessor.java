package in.koyad.piston.common.utils;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import in.koyad.piston.common.util.rest.JsonProcessor;
import in.koyad.piston.common.util.rest.JsonUtil;

public class TestJsonProcessor {
	
	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void beforeTest() {
	}

	@Test
	public void parseStringToStringArray() {
			String json = "[\"piston\", \"portal\"]";
			String[] tokens = JsonProcessor.getAsObject(json, String[].class);
			
			Assert.assertEquals(tokens.length, 2);
			
			Assert.assertEquals(tokens[0], "piston");
	}
	
	@Test
	public void parseStringToObjectArray() {
			String json = "[{\"name\": \"piston\"}, {\"name\": \"portal\"}]";
			JSONObject[] array = JsonProcessor.getAsObject(json, JSONObject[].class);
			
			Assert.assertEquals(array.length, 2);
			
			Assert.assertEquals(JsonUtil.getProperty(array[1], "name"), "portal");
	}
	
	@After
	public void afterTest() {
	}

	@AfterClass
	public static void afterClass() {
	}
	
}
