package com.betteru.ingredients;

public class Protein extends Macro {

	public Protein(double a) {
		super(a);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Protein [amount=" + getAmount() + ", toCals=" + getToCals() + "]";
	}

	
}
