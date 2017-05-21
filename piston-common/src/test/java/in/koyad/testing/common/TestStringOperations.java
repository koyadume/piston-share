package in.koyad.testing.common;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import in.koyad.piston.common.basic.StringUtil;

public class TestStringOperations {
	
	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void beforeTest() {
	}

	@Test
	public void testSplit() {
		String response = "json$$abc";
		Assert.assertEquals(StringUtil.split(response, "$$").length, 2);
	}
	
	@Test
	public void testSplitAndFloat() {
		String response = "[==================================================\u003e] 1.125 MB/2.250 MB 1m10s";
		response = response.substring(0, response.lastIndexOf(' '));
		System.out.println("Progress : " + response.split("] ")[1]);
		
		String[] tokens = response.split("/");
		Assert.assertEquals(tokens.length, 2);
		
		BigDecimal currentData = new BigDecimal(tokens[0].split(" ")[1]);
		BigDecimal totalData = new BigDecimal(tokens[1].split(" ")[0]);
		BigDecimal percentage = currentData.multiply(new BigDecimal(100)).divideToIntegralValue(totalData);
		System.out.println("Percentage : " + percentage + "%");
		Assert.assertEquals(percentage.intValue(), 50);
		
	}
	
	@After
	public void afterTest() {
	}

	@AfterClass
	public static void afterClass() {
	}
	
}
