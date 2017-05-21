package in.koyad.piston.common.utils;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import in.koyad.piston.common.util.DateUtil;

public class TestDateUtil {
	
	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void beforeTest() {
	}

	@Test
	public void getISODateWithMillis() {
			String date = "2016-07-11T13:31:01.123Z";
		
			try {
				DateUtil.getISODate(date);
			} catch (Exception ex) {
				Assert.fail(ex.getMessage());
			}
	}
	
	@Test
	public void getISODateWithoutMillis() {
			String date = "2016-07-11T13:31:01Z";
		
			try {
				DateUtil.getISODate(date, false);
			} catch (Exception ex) {
				Assert.fail(ex.getMessage());
			}
	}
	
	@Test
	public void getDaysInAMonth() {
		DateTime dateTime = new DateTime();
		int days = dateTime.minusMonths(1).dayOfMonth().getMaximumValue();
		Assert.assertEquals(days, 29);
	}
	
	@After
	public void afterTest() {
	}

	@AfterClass
	public static void afterClass() {
	}
	
}
