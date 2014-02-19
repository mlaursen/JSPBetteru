/**
 * 
 */
package com.betteru.accounts.database;

import com.github.mlaursen.database.objects.MyResultRow;

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
	 * @param primaryKey
	 */
	public Gender(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Gender(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Gender(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
