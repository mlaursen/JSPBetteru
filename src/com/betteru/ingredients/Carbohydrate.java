package com.betteru.ingredients;

public class Carbohydrate extends Macro {

	public Carbohydrate(double a) {
		super(a);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Carbohydrate [amount=" + getAmount() + ", toCals=" + getToCals() + "]";
	}
	
	
}
