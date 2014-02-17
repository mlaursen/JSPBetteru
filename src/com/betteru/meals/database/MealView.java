/**
 * 
 */
package com.betteru.meals.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.MyResultRow;
import com.betteru.database.Package;

/**
 * @author mikkel.laursen
 *
 */
public class MealView extends Meal {
	{
		Package pkg = getPackage();
		pkg.setName("meal");
	}
	private double totalCalories, totalFat, totalCarbs, totalProtein;
	private List<MealPartView> mealPartViews = new ArrayList<MealPartView>();
	public MealView() {	}
	public MealView(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public MealView(MyResultRow r) {
		super(r);
		setTotalCalories(r.get("total_calories"));
		setTotalFat(r.get("total_fat"));
		setTotalCarbs(r.get("total_carbs"));
		setTotalProtein(r.get("total_protein"));
		setMealPartViews(new MealPartView().getAll(r.get("id")));
	}


	
	public String getIngredientList() {
		String s = "<ul>\n";
		for(MealPartView v : mealPartViews) {
			s += String.format("  <li>%s - %s %s</li>\n", v.getIngredientName(), v.getServing().getSize(), v.getServing().getUnitName());
		}
		return s += "</ul>\n";
		
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
	
	
	private double tryDoubleParse(String possibleDouble) {
		try {
			return Double.parseDouble(possibleDouble);
		}
		catch(NumberFormatException e) {
			return 0;
		}
	}
	/**
	 * @return the mealPartViews
	 */
	public List<MealPartView> getMealPartViews() {
		return mealPartViews;
	}
	/**
	 * @param mealPartViews the mealPartViews to set
	 */
	public void setMealPartViews(List<MealPartView> mealPartViews) {
		this.mealPartViews = mealPartViews;
	}
	
	
}
