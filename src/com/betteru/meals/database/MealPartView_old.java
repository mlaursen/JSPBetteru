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
public class MealPartView_old extends DatabaseObject implements DatabaseObjectListable {

	private static final String LOOKUP = "MEALPART_VIEW_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "MEALPART_VIEW_GETALL(:CURSOR)";
	private static final String LOOKUP_ALL_ID = "MEALPART_VIEW_GETALL_ID(:ID, :CURSOR)";
	private String ingredientName, ingredientAmount, ingredientUnit;
	public MealPartView_old() { }

	/**
	 * @param id
	 */
	public MealPartView_old(String id) {
		super(id);
		MealPartView_old mpv = lookup(id);
		setIngredientName(mpv.getIngredientName());
		setIngredientAmount(mpv.getIngredientAmount());
		setIngredientUnit(mpv.getIngredientUnit());
	}

	/**
	 * @param r
	 */
	public MealPartView_old(MyResultRow r) {
		super(r);
		setIngredientName(r.get("ingredient_name"));
		setIngredientAmount(r.get("amount"));
		setIngredientUnit(r.get("serving_unit"));
	}

	/**
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealPartView_old [getId()=" + getId() + ", ingredientName=" + ingredientName + ", ingredientAmount=" + ingredientAmount
				+ ", ingredientUnit=" + ingredientUnit + "]";
	}

	/**
	 * @param ingredientName the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	/**
	 * @return the ingredientAmount
	 */
	public String getIngredientAmount() {
		return ingredientAmount;
	}

	/**
	 * @param ingredientAmount the ingredientAmount to set
	 */
	public void setIngredientAmount(String ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	/**
	 * @return the ingredientUnit
	 */
	public String getIngredientUnit() {
		return ingredientUnit;
	}

	/**
	 * @param ingredientUnit the ingredientUnit to set
	 */
	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}
	
	public List<MealPartView_old> lookupAll() {
		return lookupAll(MealPartView_old.class);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObjectListable#lookupAll(java.lang.Class)
	 */
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}
	
	public List<MealPartView_old> lookupAll(String mealid) {
		return lookupAll(mealid, MealPartView_old.class);
	}
	
	private <T extends DatabaseObject> List<T> lookupAll(String mealid, Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL_ID, mealid).toListOf(type);
	}
	
	public MealPartView_old lookup(String id) {
		return lookup(id, MealPartView_old.class);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObject#lookup(java.lang.String, java.lang.Class)
	 */
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new MealPartView_old(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

}
