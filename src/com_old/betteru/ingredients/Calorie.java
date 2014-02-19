package com_old.betteru.ingredients;


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
		return String.format("%.2f", amt);
	}
	
	
}
