package com.betteru.ingredients;

public class Fat extends Macro {

	public Fat(double a) {
		super(a);
		setToCals(9);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fat [amount=" + getAmount() + ", toCals=" + getToCals() + "]";
	}
	
	
}
