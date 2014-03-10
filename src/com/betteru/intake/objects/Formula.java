/**
 * 
 */
package com.betteru.intake.objects;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Protein;
import com.betteru.meals.objects.MealView;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyClob;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.utils.DateUtil;

/**
 * @author mlaursen
 * 
 */
public class Formula extends DatabaseObject implements Serializable {
	
	private static final long serialVersionUID = -8666941273158409959L;
	public static final String MIFFLIN_ST_JOER = "getFromMifflinStJoer";
	public static final String HARRIS_BENEDICT = "getFromHarrisBenedict";
	public static final String GET_FROM_FORMULA = "getFromFormula";
	protected String accountId;
	
	protected double tdee;
	
	protected int calorieChange;
	protected double fatMultiplier;
	protected double carbMultiplier;
	protected double proteinMultiplier;
	protected List<String> mealIds = new ArrayList<String>();
	protected List<MealView> meals = new ArrayList<MealView>();
	protected double totalCalories;
	protected double totalFat;
	protected double totalCarbs;
	protected double totalProtein;
	protected Date intakeDate;
	
	public Formula() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * For some reason doing super(r) alone will set the mealIds to an empty List.
	 * 
	 * @param r
	 *            A MyResultRow
	 */
	public Formula(MyResultRow r) {
		super(r);
		this.setMealIds(r);
	}
	
	/**
	 * @param accountId
	 * @param tdee
	 * @param calorieChange
	 * @param fatMultiplier
	 * @param carbMultiplier
	 * @param proteinMultiplier
	 * @param meals
	 * @param intakeDate
	 */
	public Formula(String accountId, double tdee, int calorieChange, double fatMultiplier, double carbMultiplier, double proteinMultiplier,
			List<String> mealIds, Date intakeDate) {
		super();
		this.accountId = accountId;
		this.tdee = tdee;
		this.calorieChange = calorieChange;
		this.fatMultiplier = fatMultiplier;
		this.carbMultiplier = carbMultiplier;
		this.proteinMultiplier = proteinMultiplier;
		this.mealIds = mealIds;
		this.intakeDate = intakeDate;
	}
	
	@Override
	public List<Procedure> getCustomProcedures() {
		Procedure get = new Procedure(GET_FROM_FORMULA, "id", "date");
		return Arrays.asList(get);
	}
	
	public static void generateMeals(List<Formula> fs, ObjectManager om) {
		for(Formula f : fs) {
			f.generateMeals(om);
		}
	}
	
	/**
	 * 
	 * @param om
	 */
	public void generateMeals(ObjectManager om) {
		this.meals = new ArrayList<MealView>();
		this.totalCalories = 0;
		this.totalFat = 0;
		this.totalCarbs = 0;
		this.totalProtein = 0;
		if(this.mealIds != null && this.mealIds.size() > 0) {
			for(String id : mealIds) {
				MealView m = om.get(id, MealView.class);
				this.meals.add(om.get(id, MealView.class));
				this.totalCalories += m.getTotalCalories().getAmount();
				this.totalFat += m.getTotalFat().getAmount();
				this.totalCarbs += m.getTotalCarbs().getAmount();
				this.totalProtein += m.getTotalProtein().getAmount();
			}
		}
		int l = meals.size();
		if(l < 3) {
			if(l == 0)
				l++;
			for(; l <= 3; l++) {
				meals.add(new MealView("Meal 0" + l, new MyClob(""), new Calorie(0), new Fat(0), new Carbohydrate(0), new Protein(0), null));
			}
		}
	}
	
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	
	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public void setAccountId(MyResultRow r) {
		this.accountId = r.get("account_id");
	}
	
	/**
	 * @return the tdee
	 */
	public double getTDEE() {
		return tdee;
	}
	
	/**
	 * @param tdee
	 *            the tdee to set
	 */
	public void setTDEE(double tdee) {
		this.tdee = tdee;
	}
	
	public void setTDEE(MyResultRow r) {
		this.tdee = r.getDouble("tdee");
	}
	
	/**
	 * @return the calorieChange
	 */
	public int getCalorieChange() {
		return calorieChange;
	}
	
	/**
	 * @param calorieChange
	 *            the calorieChange to set
	 */
	public void setCalorieChange(int calorieChange) {
		this.calorieChange = calorieChange;
	}
	
	public void setCalorieChange(MyResultRow r) {
		this.calorieChange = r.getInt("calorie_change");
	}
	
	/**
	 * @return the fatMultiplier
	 */
	public double getFatMultiplier() {
		return fatMultiplier;
	}
	
	/**
	 * @param fatMultiplier
	 *            the fatMultiplier to set
	 */
	public void setFatMultiplier(double fatMultiplier) {
		this.fatMultiplier = fatMultiplier;
	}
	
	public void setFatMultiplier(MyResultRow r) {
		this.fatMultiplier = r.getDouble("fat_multiplier");
	}
	
	/**
	 * @return the carbMultiplier
	 */
	public double getCarbMultiplier() {
		return carbMultiplier;
	}
	
	/**
	 * @param carbMultiplier
	 *            the carbMultiplier to set
	 */
	public void setCarbMultiplier(double carbMultiplier) {
		this.carbMultiplier = carbMultiplier;
	}
	
	public void setCarbMultiplier(MyResultRow r) {
		this.carbMultiplier = r.getDouble("carb_multiplier");
	}
	
	/**
	 * @return the proteinMultiplier
	 */
	public double getProteinMultiplier() {
		return proteinMultiplier;
	}
	
	/**
	 * @param proteinMultiplier
	 *            the proteinMultiplier to set
	 */
	public void setProteinMultiplier(double proteinMultiplier) {
		this.proteinMultiplier = proteinMultiplier;
	}
	
	public void setProteinMultiplier(MyResultRow r) {
		this.proteinMultiplier = r.getDouble("protein_multiplier");
	}
	
	/**
	 * @return the mealIds
	 */
	public List<String> getMealIds() {
		return mealIds;
	}
	
	/**
	 * @param mealIds
	 *            the mealIds to set
	 */
	public void setMealIds(List<String> mealIds) {
		this.mealIds = mealIds;
	}
	
	public void setMealIds(MyResultRow r) {
		this.mealIds = Arrays.asList(r.get("meal_ids").split(","));
	}
	
	/**
	 * @return the meals
	 */
	public List<MealView> getMeals() {
		return meals;
	}
	
	/**
	 * @param meals
	 *            the meals to set
	 */
	public void setMeals(List<MealView> meals) {
		this.meals = meals;
	}
	
	/**
	 * @return the intakeDate
	 */
	public Date getIntakeDate() {
		return intakeDate;
	}
	
	public String getIntakeDateString() {
		return DateUtil.dateToString(intakeDate);
	}
	
	/**
	 * @param intakeDate
	 *            the intakeDate to set
	 */
	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}
	
	public void setIntakeDate(MyResultRow r) {
		this.intakeDate = r.getDate("intake_date");
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

	
	public double getCalorieAmount() {
		return this.tdee - this.calorieChange;
	}
	
	public double getRemainingCalories() {
		return this.getCalorieAmount() - this.totalCalories;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [primaryKey=" + primaryKey + ", accountId=" + accountId + ", tdee=" + tdee + ", calorieChange=" + calorieChange
				+ ", fatMultiplier=" + fatMultiplier + ", carbMultiplier=" + carbMultiplier + ", proteinMultiplier=" + proteinMultiplier
				+ ", mealIds=" + mealIds + ", meals=" + meals + ", intakeDate=" + intakeDate + "]";
	}
	
}
