/**
 * 
 */
package com.betteru.intake.objects;

import java.sql.Date;
import java.util.List;

import com.github.mlaursen.database.objects.MyResultRow;


/**
 * @author mikkel.laursen
 *
 */
public class MifflinStJoer extends Formula {
	
	/**
	 * 
	 */
	public MifflinStJoer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param r
	 */
	public MifflinStJoer(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param accountId
	 * @param tdee
	 * @param calorieChange
	 * @param fatMultiplier
	 * @param carbMultiplier
	 * @param proteinMultiplier
	 * @param mealIds
	 * @param intakeDate
	 */
	public MifflinStJoer(String accountId, double tdee, int calorieChange, double fatMultiplier, double carbMultiplier,
			double proteinMultiplier, List<String> mealIds, Date intakeDate) {
		super(accountId, tdee, calorieChange, fatMultiplier, carbMultiplier, proteinMultiplier, mealIds, intakeDate);
		// TODO Auto-generated constructor stub
	}
}
