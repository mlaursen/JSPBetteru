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
public class MealView_Old extends DatabaseObject implements DatabaseObjectListable {

	private static final String LOOKUP = "MEAL_VIEW_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "MEAL_VIEW_GETALL(:CURSOR)";
	
	private String name, desc;
	private double totalCalories, totalFat, totalCarbs, totalProtein;
	private List<MealPartView_old> mpvs;
	public MealView_Old() {	}

	/**
	 * @param id
	 */
	public MealView_Old(String id) {
		super(id);
		MealView_Old mv = lookup(id);
		setName(mv.getName());
		setDescription(mv.getDescription());
		setTotalCalories(mv.getTotalCalories());
		setTotalFat(mv.getTotalFat());
		setTotalCarbs(mv.getTotalCarbs());
		setTotalProtein(mv.getTotalProtein());
	}

	/**
	 * @param r
	 */
	public MealView_Old(MyResultRow r) {
		super(r);
		setName(r.get("name"));
		setDescription(r.get("description"));
		setTotalCalories(r.get("total_calories"));
		setTotalFat(r.get("total_fat"));
		setTotalCarbs(r.get("total_carbs"));
		setTotalProtein(r.get("total_protein"));
		setMealPartViewList(new MealPartView_old().lookupAll(r.get("id")));
	}
	
	/**
	 * @return the mpvs
	 */
	public List<MealPartView_old> getMealPartViewList() {
		return mpvs;
	}

	/**
	 * @param mpvs the mpvs to set
	 */
	public void setMealPartViewList(List<MealPartView_old> mpvs) {
		this.mpvs = mpvs;
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
	 * @return the desc
	 */
	public String getDescription() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDescription(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the totalCalories
	 */
	public double getTotalCalories() {
		return totalCalories;
	}
	
	public void setTotalCalories(String totalCalories) {
		try {
			this.totalCalories = Double.parseDouble(totalCalories);
		}
		catch(NumberFormatException e) {
			this.totalCalories = 0;
		}
	}

	/**
	 * @param totalCalories the totalCalories to set
	 */
	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}

	/**
	 * @return the totalFat
	 */
	public double getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(String totalFat) {
		try {
			this.totalFat = Double.parseDouble(totalFat);
		}
		catch(NumberFormatException e) {
			this.totalFat = 0;
		}
	}
	/**
	 * @param totalFat the totalFat to set
	 */
	public void setTotalFat(double totalFat) {
		this.totalFat = totalFat;
	}

	/**
	 * @return the totalCarbs
	 */
	public double getTotalCarbs() {
		return totalCarbs;
	}

	
	public void setTotalCarbs(String totalCarbs) {
		try {
			this.totalCarbs = Double.parseDouble(totalCarbs);
		}
		catch(NumberFormatException e) {
			this.totalCarbs = 0;
		}
	}
	
	/**
	 * @param totalCarbs the totalCarbs to set
	 */
	public void setTotalCarbs(double totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	/**
	 * @return the totalProtein
	 */
	public double getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(String totalProtein) {
		try {
			this.totalProtein = Double.parseDouble(totalProtein);
		}
		catch(NumberFormatException e) {
			this.totalProtein = 0;
		}
	}
	/**
	 * @param totalProtein the totalProtein to set
	 */
	public void setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
	}

	public List<MealView_Old> lookupAll() {
		return lookupAll(MealView_Old.class);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObjectListable#lookupAll(java.lang.Class)
	 */
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}
	
	public MealView_Old lookup(String id) {
		return lookup(id, MealView_Old.class);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseObject#lookup(java.lang.String, java.lang.Class)
	 */
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new MealView_Old(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MealView_Old [name=" + name + ", desc=" + desc + ", totalCalories=" + totalCalories + ", totalFat=" + totalFat
				+ ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein + ", getId()=" + getId() + ", MPV[" + mpvs + "]]";
	}

	public String getIngredientList() {
		String s = "<ul>\n";
		for(MealPartView_old v : mpvs) {
			s += String.format("  <li>%s - %s %s</li>\n", v.getIngredientName(), v.getIngredientAmount(), v.getIngredientUnit());
		}
		return s += "</ul>\n";
		
	}
}
