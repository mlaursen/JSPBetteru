package com.betteru.ingredients;

import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.database.objects.MyResultRow;

public class Calorie {
	
	private double amt;
	
	public Calorie(double amt) {
		this.amt = amt;
	}
	
	public Calorie(MyResultRow r) {
		this.amt = StringNumberUtil.attemptParseDouble(r.get("calories"));
	}
	
	public Calorie(String amt) {
		this.amt = StringNumberUtil.attemptParseDouble(amt);
	}
	
	public void setAmount(double a) {
		amt = a;
	}
	
	public double getAmount() {
		return amt;
	}
	
	/**
	 * Adds two calories together
	 * 
	 * @param c1
	 *            The first calorie
	 * @param c2
	 *            The second calorie
	 * @return A new calorie with the values combined
	 */
	public static Calorie addCalories(Calorie c1, Calorie c2) {
		return new Calorie(c1.getAmount() + c2.getAmount());
	}
	
	/**
	 * Adds a calorie's value to the current calorie
	 * 
	 * @param c
	 *            The calorie to add
	 */
	public void add(Calorie c) {
		this.amt += c.getAmount();
	}
	
	/**
	 * Subtracts two calories and returns a new calorie with the new value
	 * 
	 * @param c1
	 *            The first calorie
	 * @param c2
	 *            The second calorie
	 * @return A new calorie with (c1.amount - c2.amount)
	 */
	public static Calorie subtractCalories(Calorie c1, Calorie c2) {
		return new Calorie(c1.getAmount() - c2.getAmount());
	}
	
	/**
	 * Subtracts the value of given calorie from the current calorie
	 * 
	 * @param c
	 *            A calorie to subtract
	 */
	public void subtract(Calorie c) {
		this.amt -= c.getAmount();
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Calorie && ((Calorie) o).amt == amt;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%.2f", amt);
	}
	
}
