package com.betteru.ingredients;

import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.database.objects.MyResultRow;

public abstract class Macro {

	protected int toCals;
	protected double amt;
	public Macro(double a) {
		amt = a;
		toCals = 4;
	}
	
	public Macro(MyResultRow r) {
		amt = StringNumberUtil.attemptParseDouble(r, this.getClass().getSimpleName());
	}
	
	public Macro(String amt) {
		this.amt = StringNumberUtil.attemptParseDouble(amt);
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
	
	/**
	 * 
	 * @param m
	 */
	public void subtract(Macro m) {
		if(this.getClass().equals(m.getClass())) {
			this.amt -= m.getAmount();
		}
		else {
			System.err.println("Not the same macro type. Did not add.");
		}
	}
	
	public static Fat subtractFats(Fat f1, Fat f2) {
		return new Fat(f1.amt - f2.amt);
	}
	
	public static Carbohydrate subtractCarbs(Carbohydrate c1, Carbohydrate c2) {
		return new Carbohydrate(c1.amt - c2.amt);
	}
	
	public static Protein subtractProteins(Protein p1, Protein p2) {
		return new Protein(p1.amt - p2.amt);
	}
	
	/**
	 * 
	 * @param m
	 */
	public void add(Macro m) {
		if(this.getClass().equals(m.getClass()))
			this.amt += m.getAmount();
		else
			System.err.println("Not the same macro type. Did not add");
	}
	
	public static Macro addFats(Fat f1, Fat f2) {
		return new Fat(f1.amt - f2.amt);
	}
	
	public static Macro addCarbs(Carbohydrate c1, Carbohydrate c2) {
		return new Carbohydrate(c1.amt - c2.amt);
	}
	
	public static Macro addProteins(Protein p1, Protein p2) {
		return new Protein(p1.amt - p2.amt);
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Macro && this.getClass().equals(o.getClass()) && amt == ((Macro) o).amt;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", amt);
	}
}
