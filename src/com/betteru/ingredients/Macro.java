package com.betteru.ingredients;

import com.betteru.utils.StringNumberFormat;
import com.github.mlaursen.database.objects.MyResultRow;

public abstract class Macro {

	protected int toCals;
	protected double amt;
	public Macro(double a) {
		amt = a;
		toCals = 4;
	}
	
	public Macro(MyResultRow r) {
		amt = StringNumberFormat.attemptParseDouble(r, this.getClass().getSimpleName());
	}
	
	public double getAmount() {
		return amt;
	}
	
	public void setAmount(double d) {
		amt = d;
	}
	
	public int getToCals() {
		return toCals;
	}
	
	public void setToCals(int c) {
		toCals = c;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Macro && amt == ((Macro) o).amt;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", amt);
	}
}
