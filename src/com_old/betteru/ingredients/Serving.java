package com_old.betteru.ingredients;

import com.betteru.utils.StringNumberFormat;

import com_old.betteru.database.MyResultRow;
import com_old.betteru.ingredients.database.FoodUnit;

public class Serving {

	private double size;
	private FoodUnit foodUnit;
	public Serving(double s, FoodUnit u) {
		size = s;
		foodUnit = u;
	}
	public Serving(String s, String u) {
		try {
			size = Double.parseDouble(s);
		}
		catch(NumberFormatException e) {
			size = 0;
		}
		foodUnit = new FoodUnit(u);
	}
	
	public void setSize(double s) {
		size = s;
	}
	
	public void setUnit(FoodUnit u) {
		foodUnit = u;
	}
	
	public double getSize() {
		return size;
	}
	
	public FoodUnit getUnit() {
		return foodUnit;
	}
	
	public String getUnitName() {
		return foodUnit.getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringNumberFormat.formatFractionString(size) + " (" + getUnitName() + ")";
	}

	
}
