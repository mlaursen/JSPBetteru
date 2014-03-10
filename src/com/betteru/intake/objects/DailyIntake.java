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
public class DailyIntake extends DailyIntakeTemplate implements Updateable, Createable, Filterable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7842332693437947749L;
	public static final String MIFFLIN_ST_JOER = "getFromMifflinStJoer";
	public static final String HARRIS_BENEDICT = "getFromHarrisBenedict";
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected int calorieChange;
	public DailyIntake() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param accountId
	 * @param intakeDate
	 * @param calorieChanges
	 */
	public DailyIntake(String accountId, Date intakeDate, int calorieChange) {
		super(accountId, intakeDate);
		this.calorieChange = calorieChange;
	}
	
	/**
	 * @param r
	 */
	public DailyIntake(MyResultRow r) {
		super(r);
	}

	/*
	@Override
	public List<Procedure> getCustomProcedures() {
		Procedure mifflin = new Procedure(MIFFLIN_ST_JOER, "id", "date");
		Procedure harris  = new Procedure(HARRIS_BENEDICT, "id", "date");
		return Arrays.asList(mifflin, harris);
	}
	*/
	
	
	
	
	
	
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DailyIntake [primaryKey=" + primaryKey + ", accountId=" + accountId + ", intakeDate=" + intakeDate + ", calorieChange="
				+ calorieChange + "]";
	}
	
	
}
