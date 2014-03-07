/**
 * 
 */
package com.betteru.meals.objects;

import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.GetAllable;
import com.github.mlaursen.database.objecttypes.Getable;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class MealPart extends DatabaseObject implements Createable, Getable, Updateable {

	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String mealId;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String ingredientId;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	private double amount;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	private String defaultUnit;
	
	public MealPart() {	}
	public MealPart(String mealId, String ingredientId, double amount, String defaultUnit) {
		this.mealId = mealId;
		this.ingredientId = ingredientId;
		this.amount = amount;
		this.defaultUnit = defaultUnit;
	}
	/**
	 * @param r
	 */
	public MealPart(MyResultRow r) {
		super(r);
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
		this.mealId = r.get("mealid");
	}

	/**
	 * @return the ingredientId
	 */
	public String getIngredientId() {
		return ingredientId;
	}

	/**
	 * @param ingredientId the ingredientId to set
	 */
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public void setIngredientId(MyResultRow r) {
		this.ingredientId = r.get("ingredientid");
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
		this.amount = StringNumberUtil.attemptParseDouble(r, "amount");
	}

	/**
	 * @return the defaultUnit
	 */
	public String getDefaultUnit() {
		return defaultUnit;
	}

	/**
	 * @param defaultUnit the defaultUnit to set
	 */
	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public void setDefaultUnit(MyResultRow r) {
		this.defaultUnit = r.get("default_unit");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealPart [" + (primaryKey != null ? "primaryKey=" + primaryKey + ", " : "")
				+ (mealId != null ? "mealId=" + mealId + ", " : "") + (ingredientId != null ? "ingredientId=" + ingredientId + ", " : "")
				+ "amount=" + amount + ", " + (defaultUnit != null ? "defaultUnit=" + defaultUnit : "") + "]";
	}
	
	
}
