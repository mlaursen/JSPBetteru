/**
 * 
 */
package com.betteru.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This has util methods for dealing with SQL Dates.
 * @author mikkel.laursen
 *
 */
public class DateUtil {
	
	/**
	 * Converts a string date into a java.sql.Date
	 * @param s
	 * @return
	 */
	public static Date stringToDate(String s) {
		boolean hyphen = s.contains("-");
		s = s.replace("-", "").replace("/", "");
		try {
			String format = hyphen ? "yyyyMMdd" : "MMddyyyy";
			
			return new Date(new SimpleDateFormat(format).parse(s).getTime());
		}
		catch (ParseException e) {
			return new Date(Calendar.getInstance().getTime().getTime());
		}
	}
	
	/**
	 * Converts a sysdate string(yyyy-MM-dd h:m:s) for Oracle to a java.sql.Date
	 * @param sys
	 * @return
	 */
	public static Date sysdateToDate(String sys) {
		if (sys != null) {
			try {
				java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd h:m:s").parse(sys);
				return new Date(utilDate.getTime());
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTime().getTime());
	}
}
