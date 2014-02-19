/**
 * 
 */
package com_old.betteru.meals.database;

import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.DatabaseObject;
import com_old.betteru.database.DatabaseUpdateable;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;

/**
 * @author mikkel.laursen
 *
 */
public class MealPart extends DatabaseObject implements DatabaseCreateable, DatabaseUpdateable {
	{
		Procedure pNew = new Procedure("new");
		Procedure pUpdate = new Procedure("update");
		addProcedure(pNew);
		addProcedure(pUpdate);
	}
	
	private String mealId, ingredientId;
	private double amount;
	private boolean defaultUnit;
	public MealPart() {	}

	/**
	 * @param primaryKey
	 */
	public MealPart(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public MealPart(MyResultRow r) {
		super(r);
		setMealId(r);
		setIngredientId(r);
		setAmount(r);
		setDefaultUnit(r);
	}

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseUpdateable#update()
	 */
	@Override
	public boolean update() {
		return DatabaseManager.executeStoredProcedure(call("new"), getPrimaryKey());
	}

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(call("create"), getPrimaryKey());
	}

	/**
	 * @return the mealId
	 */
	public String getMealId() {
		return mealId;
	}

	/**
	 * @param mealId the mealId to set
	 */
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	
	public void setMealId(MyResultRow r) {
		mealId = r.get("mealid");
	}
	
	

	/**
	 * @return the ingrdientId
	 */
	public String getIngredientId() {
		return ingredientId;
	}

	/**
	 * @param ingrdientId the ingrdientId to set
	 */
	public void setIngrdientId(String ingrdientId) {
		this.ingredientId = ingrdientId;
	}
	
	public void setIngredientId(MyResultRow r) {
		ingredientId = r.get("ingredientid");
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(MyResultRow r) {
		amount = Double.parseDouble(r.get("amount"));
	}

	/**
	 * @return the defaultUnit
	 */
	public boolean isDefaultUnit() {
		return defaultUnit;
	}
	
	private String getDefaultUnitForDB() {
		return defaultUnit ? "0" : "1";
	}

	/**
	 * @param defaultUnit the defaultUnit to set
	 */
	public void setDefaultUnit(boolean defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public void setDefaultUnit(MyResultRow r) {
		defaultUnit = r.get("default_unit").equals("0");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealPart [" + (getPrimaryKey() != null ? "primaryKey=" + getPrimaryKey() + ", " : "")
				+ (mealId != null ? "mealId=" + mealId + ", " : "") + (ingredientId != null ? "ingredientId=" + ingredientId + ", " : "")
				+ "amount=" + amount + ", defaultUnit=" + defaultUnit + "]";
	}
	
	
}
