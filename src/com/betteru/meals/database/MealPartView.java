/**
 * 
 */
package com.betteru.meals.database;

import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Package;
import com.betteru.ingredients.Serving;

/**
 * @author mikkel.laursen
 *
 */
public class MealPartView extends DatabaseObjectListable {
	{
		Package pkg = getPackage();
		pkg.setName("meal_part");
		//Procedure pGet = pkg.getProcedure("get");
	}
	private String mealId;
	private double amount, totalCalories, totalFat, totalCarbs, totalProtein;
	private String ingredientName, brandName, categoryName;
	private Serving serving;
	
	public MealPartView() {	}

	/**
	 * @param primaryKey
	 */
	public MealPartView(String primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param r
	 */
	public MealPartView(MyResultRow r) {
		super(r);
		setMealId(r);
		setAmount(r);
		setIngredientName(r);
		setBrandName(r);
		setCategoryName(r);
		setServing(r);
		setTotalCalories(r);
		setTotalFat(r);
		setTotalCarbs(r);
		setTotalProtein(r);
	}
	
	
	public List<MealPartView> getAll(String primaryKey) {
		return DatabaseManager.getStoredProcedureCursor(call("get"), primaryKey).toListOf(MealPartView.class);
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
	
	public void setAmount(String a) {
		amount = tryDoubleParse(a);
	}
	
	public void setAmount(MyResultRow r) {
		setAmount(r.get("amount"));
	}

	/**
	 * @return the totalCalories
	 */
	public double getTotalCalories() {
		return totalCalories;
	}

	/**
	 * @param totalCalories the totalCalories to set
	 */
	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	public void setTotalCalories(String c) {
		totalCalories = tryDoubleParse(c);
	}
	
	public void setTotalCalories(MyResultRow r) {
		setTotalCalories(r.get("total_calories"));
	}

	/**
	 * @return the totalFat
	 */
	public double getTotalFat() {
		return totalFat;
	}

	/**
	 * @param totalFat the totalFat to set
	 */
	public void setTotalFat(double totalFat) {
		this.totalFat = totalFat;
	}
	
	public void setTotalFat(String f) {
		totalFat = tryDoubleParse(f);
	}
	
	public void setTotalFat(MyResultRow r) {
		setTotalFat(r.get("total_fat"));
	}

	/**
	 * @return the totalCarbs
	 */
	public double getTotalCarbs() {
		return totalCarbs;
	}

	/**
	 * @param totalCarbs the totalCarbs to set
	 */
	public void setTotalCarbs(double totalCarbs) {
		this.totalCarbs = totalCarbs;
	}
	
	public void setTotalCarbs(String c) {
		totalCarbs = tryDoubleParse(c);
	}
	
	public void setTotalCarbs(MyResultRow r) {
		setTotalCarbs(r.get("total_carbs"));
	}

	/**
	 * @return the totalProtein
	 */
	public double getTotalProtein() {
		return totalProtein;
	}

	/**
	 * @param totalProtein the totalProtein to set
	 */
	public void setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
	}
	
	public void setTotalProtein(String p) {
		totalProtein = tryDoubleParse(p);
	}
	
	public void setTotalProtein(MyResultRow r) {
		setTotalProtein(r.get("total_protein"));
	}

	/**
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}

	/**
	 * @param ingredientName the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	public void setIngredientName(MyResultRow r) {
		ingredientName = r.get("ingredient");
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setBrandName(MyResultRow r) {
		brandName = r.get("brand");
	}
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setCategoryName(MyResultRow r) {
		categoryName = r.get("category");
	}

	/**
	 * @return the serving
	 */
	public Serving getServing() {
		return serving;
	}

	/**
	 * @param serving the serving to set
	 */
	public void setServing(Serving serving) {
		this.serving = serving;
	}
	
	public void setServing(MyResultRow r) {
		serving = new Serving(r.get("serving_size"), r.get("serving_unit"));
	}

	private double tryDoubleParse(String possibleDouble) {
		try {
			return Double.parseDouble(possibleDouble);
		}
		catch(NumberFormatException e) {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealPartView [" + (getPrimaryKey() != null ? "getPrimaryKey()=" + getPrimaryKey() + ", " : "")
				+ (mealId != null ? "mealId=" + mealId + ", " : "") + "amount=" + amount + ", totalCalories=" + totalCalories
				+ ", totalFat=" + totalFat + ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein + ", "
				+ (ingredientName != null ? "ingredientName=" + ingredientName + ", " : "")
				+ (brandName != null ? "brandName=" + brandName + ", " : "")
				+ (categoryName != null ? "categoryName=" + categoryName + ", " : "") + (serving != null ? "serving=" + serving : "") + "]";
	}
	
	
}
