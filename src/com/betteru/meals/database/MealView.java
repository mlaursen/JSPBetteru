/**
 * 
 */
package com.betteru.meals.database;

import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class MealView extends DatabaseObject implements DatabaseObjectListable {

	private static final String LOOKUP = "MEAL_VIEW_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "MEAL_VIEW_GETALL(:CURSOR)";
	public MealView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public MealView(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public MealView(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObjectListable#lookupAll(java.lang.Class)
	 */
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObject#lookup(java.lang.String, java.lang.Class)
	 */
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
