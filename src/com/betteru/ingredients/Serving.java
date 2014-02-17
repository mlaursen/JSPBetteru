package com.betteru.ingredients;

import com.betteru.database.MyResultRow;
import com.betteru.ingredients.database.FoodUnit;
import com.betteru.utils.StringNumberFormat;

public class Serving {

	private double size;
	private FoodUnit foodUnit;
	public Serving(double s, FoodUnit u) {
		size = s;
		foodUnit = u;
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
