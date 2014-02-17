/**
 * 
 */
package com.betteru.accounts;

import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Gender extends AccountChoice {

	/**
	 * 
	 */
	public Gender() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param n
	 */
	public Gender(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Gender(MyResultRow r) {
		super(r);
	}
	
	public static java.util.List<Gender> getAll() {
		return new Gender().getAll(Gender.class);
	}
}
