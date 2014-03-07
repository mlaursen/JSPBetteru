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
public class Multiplier extends AccountChoice {

	public static final Multiplier DEFAULT           = new Multiplier();
	public static final Multiplier SEDENTARY         = new Multiplier("SEDENTARY", 1.2, 1);
	public static final Multiplier LIGHTLY_ACTIVE    = new Multiplier("LIGHTLY ACTIVE", 1.375, 2);
	public static final Multiplier MODERATELY_ACTIVE = new Multiplier("MODERATELY ACTIVE", 1.55, 3);
	public static final Multiplier VERY_ACTIVE       = new Multiplier("VERY ACTIVE", 1.725, 4);
	public static final Multiplier EXTREMELY_ACTIVE  = new Multiplier("EXTREMELY ACTIVE", 1.9, 5);
	private double amount;
	public Multiplier() { 
		super();
		this.amount = 0;
		this.dropdownKey = 0;
	}
	
	public Multiplier(String primaryKey) {
		super(primaryKey);
	}
	public Multiplier(String primaryKey, double amount, int id) {
		super(primaryKey);
		this.amount = amount;
		this.dropdownKey = id;
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Multiplier(String primaryKey, int id) {
		super(primaryKey, id);
	}

	/**
	 * @param r
	 */
	public Multiplier(MyResultRow r) {
		super(r);
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(MyResultRow r) {
		this.amount = r.getDouble("amount");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Multiplier [" + (primaryKey != null ? "primaryKey=" + primaryKey + ", " : "") + "amount=" + amount + ", dropdownKey=" + dropdownKey + "]";
	}
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(DEFAULT);
		choices.add(SEDENTARY);
		choices.add(LIGHTLY_ACTIVE);
		choices.add(MODERATELY_ACTIVE);
		choices.add(VERY_ACTIVE);
		choices.add(EXTREMELY_ACTIVE);
		return choices;
	}

}
