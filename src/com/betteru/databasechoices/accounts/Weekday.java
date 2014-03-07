/**
 * 
 */
package com.betteru.databasechoices.accounts;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Weekday extends AccountChoice {

	public static final Weekday SUNDAY = new Weekday("SUNDAY", 0);
	public static final Weekday MONDAY = new Weekday("MONDAY", 1);
	public static final Weekday TUESDAY = new Weekday("TUESDAY", 2);
	public static final Weekday WEDNESDAY = new Weekday("WEDNESDAY", 3);
	public static final Weekday THURSDAY = new Weekday("THURSDAY", 4);
	public static final Weekday FRIDAY = new Weekday("FRIDAY", 5);
	public static final Weekday SATURDAY = new Weekday("SATURDAY", 6);
	public Weekday() {
	}

	/**
	 * @param primaryKey
	 */
	public Weekday(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Weekday(String primaryKey, int id) {
		super(primaryKey, id);
	}

	/**
	 * @param r
	 */
	public Weekday(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	
	public int getDayOfWeek() {
		return dropdownKey;
	}
	
	public void setDayOfWeek(MyResultRow r) {
		dropdownKey = r.getInt("day_of_week");
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(SUNDAY);
		choices.add(MONDAY);
		choices.add(TUESDAY);
		choices.add(WEDNESDAY);
		choices.add(THURSDAY);
		choices.add(FRIDAY);
		choices.add(SATURDAY);
		return choices;
	}

}
