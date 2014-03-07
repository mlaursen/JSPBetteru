/**
 * 
 */
package com.betteru.databasechoices.accounts;

import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Weekday extends AccountChoice {

	public static final Weekday SUNDAY = new Weekday("SUNDAY", 0);
	public static final Weekday MONDAY = new Weekday("MONDAY", 1);
	public static final Weekday TUESDAY = new Weekday("TUESDAY", 2);
	public static final Weekday WEDNESDAY = new Weekday("WEDNESDAY", 3);
	public static final Weekday THURSDAY = new Weekday("THURSDAY", 4);
	public static final Weekday FRIDAY = new Weekday("FRIDAY", 5);
	public static final Weekday SATURDAY = new Weekday("SATURDAY", 6);
	public Weekday() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Weekday(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Weekday(String primaryKey, int id) {
		super(primaryKey, id);
	}

	/**
	 * @param r
	 */
	public Weekday(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	
	public int getDayOfWeek() {
		return dropdownKey;
	}
	
	public void setDayOfWeek(MyResultRow r) {
		dropdownKey = r.getInt("day_of_week");
	}

}
