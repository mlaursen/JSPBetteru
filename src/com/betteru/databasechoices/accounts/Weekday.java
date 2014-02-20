/**
 * 
 */
package com.betteru.databasechoices.accounts;

import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Weekday extends AccountChoice {

	/**
	 * 
	 */
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Weekday(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
