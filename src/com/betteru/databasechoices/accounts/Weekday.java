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
		dropdownKey = StringNumberUtil.attemptParseInteger(r, "day_of_week");
	}

}
