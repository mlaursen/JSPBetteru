/**
 * 
 */
package com.betteru.databasechoices.accounts;

import com.betteru.utils.Util;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Multiplier extends AccountChoice {

	private double amount;
	public Multiplier() { }
	public Multiplier(String primaryKey) {
		super(primaryKey);
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
		this.amount = Util.attemptParseDouble(r, "amount");
	}

}
