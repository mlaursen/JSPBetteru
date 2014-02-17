package com.betteru.ingredients;

import com.betteru.database.MyResultRow;

public class Calorie {

	private double amt;
	public Calorie(double amt) {
		this.amt = amt;
	}
	
	public void setAmount(double a) {
		amt = a;
	}
	
	public double getAmount() {
		return amt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calorie [amt=" + amt + "]";
	}
	
	
}
