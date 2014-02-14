/**
 * 
 */
package com.betteru.accounts;

import java.util.List;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Multiplier extends AccountChoice {
	private double amount;
	public Multiplier() { }
	public Multiplier(String primaryKey) {
		super(primaryKey);
		Multiplier m = get(primaryKey);
		setAmount(m.getAmount());
	}

	/**
	 * @param primaryKey
	 */
	public Multiplier(String primaryKey, double a) {
		super(primaryKey);
		amount = a;
	}

	/**
	 * @param r
	 */
	public Multiplier(MyResultRow r) {
		super(r);
		setAmount(r);
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(MyResultRow r) {
		amount = Double.parseDouble(r.get("amount"));
	}

	
	
	public List<Multiplier> getAll() {
		return getAll(Multiplier.class);
	}

	
	public Multiplier get(String primaryKey) {
		return get(primaryKey, Multiplier.class);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Multiplier [" + super.toString() + ", amount=" + amount + "]";
	}


}
