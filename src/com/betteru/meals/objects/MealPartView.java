/**
 * 
 */
package com.betteru.meals.objects;

import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.Protein;
import com.betteru.ingredients.Serving;
import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.annotations.DatabaseViewClass;
import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Filterable;
import com.github.mlaursen.database.objecttypes.GetAllable;

/**
 * @author mikkel.laursen
 * 
 */
@DatabaseViewClass(MealPart.class)
public class MealPartView extends DatabaseView implements Filterable, GetAllable {
	
	@DatabaseField(values={DatabaseFieldType.FILTER})
	private String mealId;
	private double amount;
	private Calorie totalCalories;
	private Macro totalFat, totalCarbs, totalProtein;
	private String ingredientName, brandName, categoryName;
	private Serving serving;
	
	public MealPartView() {}
	public MealPartView(String mealId, double amount, Calorie totalCalories, Macro totalFat, Macro totalCarbs, Macro totalProtein,
			String ingredientName, String brandName, String categoryName, Serving serving) {
		this.mealId = mealId;
		this.amount = amount;
		this.totalCalories = totalCalories;
		this.totalFat = totalFat;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
		this.ingredientName = ingredientName;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.serving = serving;
	}
	
	/**
	 * @param r
	 */
	public MealPartView(MyResultRow r) {
		super(r);
	}
	
	/**
	 * @return the mealId
	 */
	public String getMealId() {
		return mealId;
	}
	
	/**
	 * @param mealId
	 *            the mealId to set
	 */
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the totalCalories
	 */
	public Calorie getTotalCalories() {
		return totalCalories;
	}
	
	/**
	 * @param totalCalories
	 *            the totalCalories to set
	 */
	public void setTotalCalories(Calorie totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	/**
	 * @return the totalFat
	 */
	public Macro getTotalFat() {
		return totalFat;
	}
	
	/**
	 * @param totalFat
	 *            the totalFat to set
	 */
	public void setTotalFat(Macro totalFat) {
		this.totalFat = totalFat;
	}
	
	/**
	 * @return the totalCarbs
	 */
	public Macro getTotalCarbs() {
		return totalCarbs;
	}
	
	/**
	 * @param totalCarbs
	 *            the totalCarbs to set
	 */
	public void setTotalCarbs(Macro totalCarbs) {
		this.totalCarbs = totalCarbs;
	}
	
	/**
	 * @return the totalProtein
	 */
	public Macro getTotalProtein() {
		return totalProtein;
	}
	
	/**
	 * @param totalProtein
	 *            the totalProtein to set
	 */
	public void setTotalProtein(Macro totalProtein) {
		this.totalProtein = totalProtein;
	}
	
	/**
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}
	
	/**
	 * @param ingredientName
	 *            the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * @return the serving
	 */
	public Serving getServing() {
		return serving;
	}
	
	/**
	 * @param serving
	 *            the serving to set
	 */
	public void setServing(Serving serving) {
		this.serving = serving;
	}
	
	public void setMealId(MyResultRow r) {
		this.mealId = r.get("mealid");
	}
	
	public void setAmount(MyResultRow r) {
		this.amount = StringNumberUtil.attemptParseDouble(r, "amount");
	}
	
	public void setIngredientName(MyResultRow r) {
		this.ingredientName = r.get("ingredient");
	}
	
	public void setBrandName(MyResultRow r) {
		this.brandName = r.get("brand");
	}
	
	public void setCategoryName(MyResultRow r) {
		this.categoryName = r.get("category");
	}
	
	public void setServing(MyResultRow r) {
		this.serving = new Serving(r.get("serving_size"), r.get("serving_unit"));
	}
	
	public void setTotalCalories(MyResultRow r) {
		this.totalCalories = new Calorie(r.get("total_calories"));
	}
	
	public void setTotalFat(MyResultRow r) {
		this.totalFat = new Fat(r.get("total_fat"));
	}
	
	public void setTotalCarbs(MyResultRow r) {
		this.totalCarbs = new Carbohydrate(r.get("total_carbs"));
	}
	
	public void setTotalProtein(MyResultRow r) {
		this.totalProtein = new Protein(r.get("total_protein"));
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealPartView [primaryKey=" + primaryKey + ", mealId=" + mealId + ", amount=" + amount + ", totalCalories=" + totalCalories
				+ ", totalFat=" + totalFat + ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein + ", ingredientName="
				+ ingredientName + ", brandName=" + brandName + ", categoryName=" + categoryName + ", serving=" + serving + "]";
	}
	
}
