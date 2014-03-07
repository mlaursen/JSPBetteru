/**
 * 
 */
package com.betteru.databasechoices.accounts;

import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class UnitSystem extends AccountChoice {

	public static final UnitSystem IMPERIAL = new UnitSystem("IMPERIAL");
	public static final UnitSystem METRIC   = new UnitSystem("METRIC");
	/**
	 * 
	 */
	public UnitSystem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public UnitSystem(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public UnitSystem(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public UnitSystem(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
