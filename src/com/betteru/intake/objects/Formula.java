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
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.Protein;
import com.betteru.meals.objects.MealView;
import com.betteru.utils.StringNumberUtil;
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
	public static final String MIFFLIN_ST_JOER = "Mifflin-St Joer";
	public static final String HARRIS_BENEDICT = "Harris Benedict";
	public static final String GET_FROM_FORMULA = "getFromFormula";
	protected String accountId;
	protected List<String> mealIds = new ArrayList<String>();
	protected List<MealView> meals = new ArrayList<MealView>();
	protected Date intakeDate;
	protected Calorie totalCalories, expectedCalories, remainingCalories;
	protected Fat totalFat, expectedFat, remainingFat;
	protected Carbohydrate totalCarbs, expectedCarbs, remainingCarbs;
	protected Protein totalProtein, expectedProtein, remainingProtein;
	protected double weight;
	
	public Formula() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param accountId
	 * @param mealIds
	 * @param meals
	 * @param intakeDate
	 * @param totalCalories
	 * @param expectedCalories
	 * @param totalFat
	 * @param expectedFat
	 * @param totalCarbs
	 * @param expectedCarbs
	 * @param totalProtein
	 * @param expectedProtein
	 * @param weight
	 */
	public Formula(String accountId, List<String> mealIds, List<MealView> meals, Date intakeDate, Calorie totalCalories,
			Calorie expectedCalories, Fat totalFat, Fat expectedFat, Carbohydrate totalCarbs, Carbohydrate expectedCarbs,
			Protein totalProtein, Protein expectedProtein, double weight) {
		super();
		this.accountId = accountId;
		this.mealIds = mealIds;
		this.meals = meals;
		this.intakeDate = intakeDate;
		this.totalCalories = totalCalories;
		this.expectedCalories = expectedCalories;
		this.totalFat = totalFat;
		this.expectedFat = expectedFat;
		this.totalCarbs = totalCarbs;
		this.expectedCarbs = expectedCarbs;
		this.totalProtein = totalProtein;
		this.expectedProtein = expectedProtein;
		this.weight = weight;
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
		this.totalCalories = new Calorie(0);
		this.totalFat = new Fat(0);
		this.totalCarbs = new Carbohydrate(0);
		this.totalProtein = new Protein(0);
		if(this.mealIds != null && this.mealIds.size() > 0) {
			for(String id : mealIds) {
				MealView m = om.get(id, MealView.class);
				this.meals.add(om.get(id, MealView.class));
				this.totalCalories.add(m.getTotalCalories());
				this.totalFat.add(m.getTotalFat());
				this.totalCarbs.add(m.getTotalCarbs());
				this.totalProtein.add(m.getTotalProtein());
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
		this.remainingCalories = Calorie.subtractCalories(this.expectedCalories, this.totalCalories);
		this.remainingFat = Macro.subtractFats(this.expectedFat, this.totalFat);
		this.remainingCarbs = Macro.subtractCarbs(this.expectedCarbs, this.totalCarbs);
		this.remainingProtein = Macro.subtractProteins(this.expectedProtein, this.totalProtein);
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
	
	/**
	 * @param intakeDate
	 *            the intakeDate to set
	 */
	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}
	
	public String getIntakeDateString() {
		return DateUtil.dateToString(intakeDate);
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
	 * @return the expectedCalories
	 */
	public Calorie getExpectedCalories() {
		return expectedCalories;
	}
	
	/**
	 * @param expectedCalories
	 *            the expectedCalories to set
	 */
	public void setExpectedCalories(Calorie expectedCalories) {
		this.expectedCalories = expectedCalories;
	}
	
	/**
	 * @return the totalFat
	 */
	public Fat getTotalFat() {
		return totalFat;
	}
	
	/**
	 * @param totalFat
	 *            the totalFat to set
	 */
	public void setTotalFat(Fat totalFat) {
		this.totalFat = totalFat;
	}
	
	/**
	 * @return the expectedFat
	 */
	public Fat getExpectedFat() {
		return expectedFat;
	}
	
	/**
	 * @param expectedFat
	 *            the expectedFat to set
	 */
	public void setExpectedFat(Fat expectedFat) {
		this.expectedFat = expectedFat;
	}
	
	/**
	 * @return the totalCarbs
	 */
	public Carbohydrate getTotalCarbs() {
		return totalCarbs;
	}
	
	/**
	 * @param totalCarbs
	 *            the totalCarbs to set
	 */
	public void setTotalCarbs(Carbohydrate totalCarbs) {
		this.totalCarbs = totalCarbs;
	}
	
	/**
	 * @return the expectedCarbs
	 */
	public Carbohydrate getExpectedCarbs() {
		return expectedCarbs;
	}
	
	/**
	 * @param expectedCarbs
	 *            the expectedCarbs to set
	 */
	public void setExpectedCarbs(Carbohydrate expectedCarbs) {
		this.expectedCarbs = expectedCarbs;
	}
	
	/**
	 * @return the totalProtein
	 */
	public Protein getTotalProtein() {
		return totalProtein;
	}
	
	/**
	 * @param totalProtein
	 *            the totalProtein to set
	 */
	public void setTotalProtein(Protein totalProtein) {
		this.totalProtein = totalProtein;
	}
	
	/**
	 * @return the expectedProtein
	 */
	public Protein getExpectedProtein() {
		return expectedProtein;
	}
	
	/**
	 * @param expectedProtein
	 *            the expectedProtein to set
	 */
	public void setExpectedProtein(Protein expectedProtein) {
		this.expectedProtein = expectedProtein;
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setAccountId(MyResultRow r) {
		this.accountId = r.get("account_id");
	}
	
	public void setWeight(MyResultRow r) {
		this.weight = r.getDouble("weight");
	}
	
	public void setMealIds(MyResultRow r) {
		this.mealIds = Arrays.asList(r.get("meal_ids").split(","));
	}
	
	public void setIntakeDate(MyResultRow r) {
		this.intakeDate = r.getDate("intake_date");
	}
	
	public void setExpectedFields(MyResultRow r) {
		this.expectedCalories = new Calorie(r.getDouble("tdee") - r.getDouble("calorie_change"));
		double fatMultiplier = r.getDouble("fat_multiplier");
		double carbMultiplier = r.getDouble("carb_multiplier");
		double proteinMultiplier = r.getDouble("protein_multiplier");
		if(this.weight == 0) {
			setWeight(r);
		}
		if(proteinMultiplier == 1) {
			double cals = expectedCalories.getAmount();
			this.expectedProtein = new Protein(this.weight);
			cals = cals - (expectedProtein.getAmount() * expectedProtein.getToCals());
			this.expectedFat = new Fat(cals * fatMultiplier / 9);
			this.expectedCarbs = new Carbohydrate(cals * carbMultiplier / 4);
		}
		else {
			double cals = expectedCalories.getAmount();
			this.expectedProtein = new Protein(cals * proteinMultiplier / 4);
			this.expectedFat = new Fat(cals * fatMultiplier / 9);
			this.expectedCarbs = new Carbohydrate(cals * carbMultiplier / 4);
		}
	}
	
	/**
	 * @return the remainingCalories
	 */
	public Calorie getRemainingCalories() {
		return remainingCalories;
	}
	
	/**
	 * @param remainingCalories
	 *            the remainingCalories to set
	 */
	public void setRemainingCalories(Calorie remainingCalories) {
		this.remainingCalories = remainingCalories;
	}
	
	/**
	 * @return the remainingFat
	 */
	public Fat getRemainingFat() {
		return remainingFat;
	}
	
	/**
	 * @param remainingFat
	 *            the remainingFat to set
	 */
	public void setRemainingFat(Fat remainingFat) {
		this.remainingFat = remainingFat;
	}
	
	/**
	 * @return the remainingCarbs
	 */
	public Carbohydrate getRemainingCarbs() {
		return remainingCarbs;
	}
	
	/**
	 * @param remainingCarbs
	 *            the remainingCarbs to set
	 */
	public void setRemainingCarbs(Carbohydrate remainingCarbs) {
		this.remainingCarbs = remainingCarbs;
	}
	
	/**
	 * @return the remainingProtein
	 */
	public Protein getRemainingProtein() {
		return remainingProtein;
	}
	
	/**
	 * @param remainingProtein
	 *            the remainingProtein to set
	 */
	public void setRemainingProtein(Protein remainingProtein) {
		this.remainingProtein = remainingProtein;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Formula [accountId=" + accountId + ", mealIds=" + mealIds + ", meals=" + meals + ", intakeDate=" + intakeDate
				+ ", totalCalories=" + totalCalories + ", expectedCalories=" + expectedCalories + ", totalFat=" + totalFat
				+ ", expectedFat=" + expectedFat + ", totalCarbs=" + totalCarbs + ", expectedCarbs=" + expectedCarbs + ", totalProtein="
				+ totalProtein + ", expectedProtein=" + expectedProtein + ", weight=" + weight + "]";
	}
	
}
