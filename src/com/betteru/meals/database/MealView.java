/**
 * 
 */
package com.betteru.meals.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.Protein;
import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.GetAllable;
import com.github.mlaursen.database.objecttypes.Getable;

/**
 * @author mikkel.laursen
 *
 */
public class MealView extends DatabaseView implements Getable, GetAllable {

	private Calorie totalCalories;
	private Macro totalFat, totalCarbs, totalProtein;
	private List<MealPartView> mealParts;// = new ArrayList<MealPartView>();
	public MealView() { }
	public MealView(String primaryKey) {
		super();
		this.primaryKey = primaryKey;
		this.setAll(manager.getFirstRowFromCursorProcedure("get", primaryKey));
	}

	public MealView(Integer primaryKey) {
		this(primaryKey.toString());
	}

	/**
	 * @param r
	 */
	public MealView(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	
	
	public String getIngredientList() {
		String s = "<ul>\n";
		for(MealPartView v : mealParts) {
			s += String.format("  <li>%s - %s %s</li>\n", v.getIngredientName(), v.getServing().getSize(), v.getServing().getUnitName());
		}
		return s += "</ul>\n";
		
	}
	
	
	
	/**
	 * @return the totalCalories
	 */
	public Calorie getTotalCalories() {
		return totalCalories;
	}
	/**
	 * @param totalCalories the totalCalories to set
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
	 * @param totalFat the totalFat to set
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
	 * @param totalCarbs the totalCarbs to set
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
	 * @param totalProtein the totalProtein to set
	 */
	public void setTotalProtein(Macro totalProtein) {
		this.totalProtein = totalProtein;
	}
	/**
	 * @return the mealParts
	 */
	public List<MealPartView> getMealParts() {
		return mealParts;
	}
	/**
	 * @param mealParts the mealParts to set
	 */
	public void setMealParts(List<MealPartView> mealParts) {
		this.mealParts = mealParts;
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

	public void setMealParts(MyResultRow r) {
		System.out.println("Getting all meal parts");
		System.out.println(new MealPartView().getAll(r.get("id")));
		this.mealParts = new MealPartView().getAll(r.get("id"));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealView [primaryKey=" + primaryKey + ", totalCalories=" + totalCalories + ", totalFat=" + totalFat + ", totalCarbs="
				+ totalCarbs + ", totalProtein=" + totalProtein + ", mealParts=" + mealParts + "]";
	}
	
	
}
