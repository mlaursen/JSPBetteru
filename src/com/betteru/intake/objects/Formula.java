/**
 * 
 */
package com.betteru.intake.objects;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.betteru.meals.objects.MealView;
import com.github.mlaursen.annotations.DatabaseViewClass;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.utils.ClassUtil;


/**
 * @author mlaursen
 *
 */
public class Formula extends DatabaseObject {
	public static final String MIFFLIN_ST_JOER = "getFromMifflinStJoer";
	public static final String HARRIS_BENEDICT = "getFromHarrisBenedict";
	protected String accountId;
	
	protected double tdee;
	
	protected int calorieChange;
	protected double fatMultiplier;
	protected double carbMultiplier;
	protected double proteinMultiplier;
	protected List<String> mealIds = new ArrayList<String>();
	protected List<MealView> meals = new ArrayList<MealView>();
	protected Date intakeDate;
	public Formula() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param r
	 */
	public Formula(MyResultRow r) {
		super(r);
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
		Procedure get = new Procedure("getfrom" + this.getClass().getSimpleName().toLowerCase(), "id", "date");
		return Arrays.asList(get);
	}

	
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	
	/**
	 * @param accountId the accountId to set
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
	 * @param tdee the tdee to set
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
	 * @param calorieChange the calorieChange to set
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
	 * @param fatMultiplier the fatMultiplier to set
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
	 * @param carbMultiplier the carbMultiplier to set
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
	 * @param proteinMultiplier the proteinMultiplier to set
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
	 * @param mealIds the mealIds to set
	 */
	public void setMealIds(List<String> mealIds) {
		this.mealIds = mealIds;
	}


	public void setMealIds(MyResultRow r) {
		String mealids = r.get("meal_ids");
		this.mealIds = Arrays.asList(mealids.split(","));
	}
	/**
	 * @return the meals
	 */
	public List<MealView> getMeals() {
		return meals;
	}

	
	/**
	 * @param meals the meals to set
	 */
	public void setMeals(List<MealView> meals) {
		this.meals = meals;
	}
	
	public void generateMeals(ObjectManager om) {
		this.meals = new ArrayList<MealView>();
		if(this.mealIds != null && this.mealIds.size() > 0) {
			for(String id : mealIds) {
				this.meals.add(om.get(id, MealView.class));
			}
		}
	}
	
	/**
	 * @return the intakeDate
	 */
	public Date getIntakeDate() {
		return intakeDate;
	}

	
	/**
	 * @param intakeDate the intakeDate to set
	 */
	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}
	
	public void setIntakeDate(MyResultRow r) {
		this.intakeDate = r.getDate("intake_date");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [primaryKey=" + primaryKey + ", accountId=" + accountId + ", tdee=" + tdee + ", calorieChange=" + calorieChange
				+ ", fatMultiplier=" + fatMultiplier + ", carbMultiplier=" + carbMultiplier + ", proteinMultiplier=" + proteinMultiplier
				+ ", mealIds=" + mealIds + ", meals=" + meals + ", intakeDate=" + intakeDate + "]";
	}
	
	
}
