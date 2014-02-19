package com_old.betteru.ingredients;

public abstract class Macro {

	private int toCals;
	private double amt;
	public Macro(double a) {
		amt = a;
		toCals = 4;
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
	
	public String toString() {
		return String.format("%.2f", amt);
	}
}
