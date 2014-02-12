/**
 * 
 */
package com.betteru.accounts;

import java.util.List;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Multiplier extends DatabaseObject implements DatabaseObjectListable {
	
	private double amount;
	public Multiplier() { }

	/**
	 * @param primaryKey
	 */
	public Multiplier(String primaryKey, double a) {
		super(primaryKey);
		amount = a;
	}

	/**
	 * @param r
	 */
	public Multiplier(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObjectListable#lookupAll(java.lang.Class)
	 */
	

}
