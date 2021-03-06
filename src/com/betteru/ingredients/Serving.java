package com.betteru.ingredients;

import com.betteru.databasechoices.ingredients.FoodUnit;
import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.utils.ClassUtil;

public class Serving {

	private double size;
	private FoodUnit foodUnit;
	public Serving(double s, FoodUnit u) {
		size = s;
		foodUnit = u;
	}
	
	public Serving(MyResultRow r) {
		String n = ClassUtil.formatClassName(this.getClass());
		String s = (n + "_size").toLowerCase();
		String u = (n + "_unit").toLowerCase();
		size = StringNumberUtil.attemptParseDouble(r.get(s));
		foodUnit = new FoodUnit(r.get(u));
		
	}
	
	public Serving(String s, String u) {
		size = StringNumberUtil.attemptParseDouble(s);
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
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Serving && ((Serving) o).size == size && ((Serving) o).foodUnit.equals(foodUnit);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringNumberUtil.formatFractionString(size) + " (" + getUnitName() + ")";
	}

	
}
