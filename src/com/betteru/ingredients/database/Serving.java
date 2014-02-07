package com.betteru.ingredients.database;

public class Serving {

	private double size;
	private Unit unit;
	public Serving(double s, Unit u) {
		size = s;
		unit = u;
	}
	
	public void setSize(double s) {
		size = s;
	}
	
	public void setUnit(Unit u) {
		unit = u;
	}
	
	public double getSize() {
		return size;
	}
	
	public Unit getUnit() {
		return unit;
	}

}
