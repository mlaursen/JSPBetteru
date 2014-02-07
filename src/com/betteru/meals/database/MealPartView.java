package com.betteru.meals.database;

import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;

public class MealPartView extends DatabaseObject implements DatabaseObjectListable {

	private static final String LOOKUP = "MEALPART_VIEW_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "MEALPART_VIEW_GETALL(:CURSOR)";
	
	private String mealId, ingredientName, brandName, categoryName;
	public MealPartView() {	}
	public MealPartView(String id) {
		super(id);
		MealPartView mpv = lookup(id);
	}

	public MealPartView(MyResultRow r) {
		super(r);
	}
	
	
	public List<MealPartView> lookupAll() {
		return lookupAll(MealPartView.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}

	public MealPartView lookup(String id) {
		return lookup(id, MealPartView.class);
	}
	
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new MealPartView(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

}
