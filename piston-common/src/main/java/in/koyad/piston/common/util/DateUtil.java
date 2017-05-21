package in.koyad.piston.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtil {
	
	public static String convertToHoursMinutes(long seconds) {
		StringBuilder builder = new StringBuilder();
		
		long hours = seconds/3600;
		if(hours > 0) {
			builder.append(hours + "h ");
		}
		
		long minutes =  (seconds % 3600)/60;
		if(minutes > 0) {
			builder.append(minutes + "m");
		}
		
		return builder.toString();
	}
	
	public static Date getDateMidnight(int decrement) {
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		cal.add(Calendar.DATE, -decrement);
		
		Date dt = cal.getTime();
		return dt;
	}
	
	public static Date cloneDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.getTime();
	}
	
	public static Date addDays(Date dt, int count) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DAY_OF_MONTH, count);
		return cal.getTime();
	}
	
	public static String format(Date dt, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(dt);
	}
	
	/*
	 * This can be used to parse a date in two formats -
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZZ (In this case millis should be true. Use getISODate(String) method for this scenario.)
	 * yyyy-MM-dd'T'HH:mm:ssZZ (In this case millis should be false)
	 */
	public static DateTime getISODate(String date, boolean millis) {
		DateTimeFormatter fmt = null;
		if(millis) {
			fmt = ISODateTimeFormat.dateTime();
		} else {
			fmt = ISODateTimeFormat.dateTimeNoMillis();
		}
		DateTime dt = fmt.parseDateTime(date); 
		return dt;
	}

	/*
	 * This should be used to parse a date in yyyy-MM-dd'T'HH:mm:ss.SSSZZ format.
	 */
	public static DateTime getISODate(String date) {
		return getISODate(date, true);
	}
	
	/*
	 * This should be used to parse a date in yyyy-MM-dd'T'HH:mm:ss.SSSZZ format.
	 */
	public static DateTime getISODate(String date, DateTimeZone tz) {
		DateTime dt = ISODateTimeFormat.dateTime().withZone(tz).parseDateTime(date); 
		return dt;
	}
	
	/*
	 * This should be used to parse a date in yyyy-MM-dd'T'HH:mm:ss.SSSZZ format.
	 */
	public static DateTime getISODateInUTC(String date) {
		return getISODate(date, DateTimeZone.UTC);
	}
	
	/*
	 * Returns smallest integer greater than the difference between two time stamps
	 * in hours.
	 */
	public static long getDiffInHours(Timestamp start, Timestamp end) {
		long diff = end.getTime() - start.getTime();
		long hours = diff/(60 * 60 * 1000);
		
		if(diff/(60 * 60 * 1000) > 0) {
			hours += 1;
		}
		
		return hours;
	}
	
	/*
	 * Returns smallest integer greater than the difference between two timestamps
	 * in hours.
	 */
	public static long getDiffInHours(long start, long end) {
		long diff = end - start;
		long hours = diff/(60 * 60 * 1000);
		
		if(diff/(60 * 60 * 1000) > 0) {
			hours += 1;
		}
		
		return hours;
	}
	
	public static DateTime getTime(String time, String format, DateTimeZone timeZone) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withChronology(ISOChronology.getInstance(timeZone));
		return formatter.parseDateTime(time);
	}
	
	public static void main(String[] args) {
//		DateTime date = getISODateInUTC("2016-02-26T12:36:00.000Z");
		System.out.println(getTime("2016-01-01 15:24:00", "yyyy-MM-dd HH:mm:ss", DateTimeZone.forID("GMT")));
	}
}
