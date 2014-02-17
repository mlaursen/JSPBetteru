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
		// TODO Auto-generated constructor stub
	}

	public Gender get(String name) {
		return get(name, Gender.class);
	}
	
	public java.util.List<Gender> getAll() {
		return getAll(Gender.class);
	}
}
