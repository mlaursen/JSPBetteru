/**
 * 
 */
package com.betteru.intake.objects;

import java.sql.Date;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Filterable;
import com.github.mlaursen.database.procedures.Updateable;


/**
 * @author mlaursen
 *
 */
public class DailyMealIntake extends DailyIntakeTemplate implements Filterable, Updateable, Createable {
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String mealId;
	public DailyMealIntake() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param accountId
	 * @param intakeDate
	 */
	public DailyMealIntake(String accountId, Date intakeDate) {
		super(accountId, intakeDate);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param r
	 */
	public DailyMealIntake(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
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
		this.mealId = r.get("meal_id");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DailyMealIntake [primaryKey=" + primaryKey + ", accountId=" + accountId + ", intakeDate=" + intakeDate + ", mealId="
				+ mealId + "]";
	}
	
}
