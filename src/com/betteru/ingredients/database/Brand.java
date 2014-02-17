/**
 * 
 */
package com.betteru.ingredients.database;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Brand extends BrandCategoryOutline implements DatabaseCreateable {

	public Brand() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Brand(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Brand(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Brand(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(call("new"), getPrimaryKey());
	}

}
