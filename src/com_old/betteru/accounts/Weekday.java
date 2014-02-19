/**
 * 
 */
package com_old.betteru.accounts;

import com_old.betteru.database.MyResultRow;

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
	 * @param n
	 */
	public Weekday(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Weekday(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	public static Weekday get(String n) {
		return new Weekday().get(n, Weekday.class);
	}
	
	public java.util.List<Weekday> getAll() {
		return new Weekday().getAll(Weekday.class);
	}
}
