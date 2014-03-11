/**
 * 
 */
package com.betteru.meals.objects;

import java.util.ArrayList;
import java.util.List;

import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.Protein;
import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.annotations.DatabaseViewClass;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyClob;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.GetAllable;
import com.github.mlaursen.database.procedures.Getable;

/**
 * @author mikkel.laursen
 *
 */
@DatabaseViewClass(Meal.class)
public class MealView extends DatabaseView implements Getable, GetAllable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1350205600740619670L;
	private String name;
	private MyClob description;
	private Calorie totalCalories;
	private Macro totalFat, totalCarbs, totalProtein;
	private List<MealPartView> mealParts = new ArrayList<MealPartView>();
	public MealView() { }
	
	

	/**
	 * @param name
	 * @param description
	 * @param totalCalories
	 * @param totalFat
	 * @param totalCarbs
	 * @param totalProtein
	 * @param mealParts
	 */
	public MealView(String name, MyClob description, Calorie totalCalories, Macro totalFat, Macro totalCarbs, Macro totalProtein,
			List<MealPartView> mealParts) {
		super();
		this.name = name;
		this.description = description;
		this.totalCalories = totalCalories;
		this.totalFat = totalFat;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
		this.mealParts = mealParts;
	}



	/**
	 * @param r
	 */
	public MealView(MyResultRow r) {
		super(r);
	}
	
	
	public String getIngredientList() {
		String s = "<ul>\n";
		for(MealPartView v : mealParts) {
			s += String.format("  <li>%s - %.2f %s</li>\n", v.getIngredientName(), v.getAmount(), v.getServing().getUnitName());
		}
		return s += "</ul>\n";
	}
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public MyClob getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(MyClob description) {
		this.description = description;
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
	
	public void setName(MyResultRow r) {
		this.name = r.get("name");
	}
	
	public void setDescription(MyResultRow r) {
		this.description = new MyClob(r.get("description"));
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
	
	public static void generateMealParts(List<MealView> meals, ObjectManager manager) {
		for(MealView mv : meals) {
			mv.generateMealParts(manager);
		}
	}
	public void generateMealParts(ObjectManager manager) {
		this.mealParts = manager.filter(MealPartView.class, this.primaryKey);
	}
	
	@Override
	public String toString() {
		return "MealView [primaryKey=" + primaryKey + ", name=" + name + ", description=" + description + ", totalCalories="
				+ totalCalories + ", totalFat=" + totalFat + ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein
				+ ", mealParts=" + mealParts + "]";
	}
	
	
}
