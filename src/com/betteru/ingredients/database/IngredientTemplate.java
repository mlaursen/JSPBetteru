package com.betteru.ingredients.database;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;

public abstract class IngredientTemplate extends DatabaseObject implements DatabaseObjectListable {

	private String name;
	//private double servingSize, altServingSize;
	private double calories, fat, carbs, protein;
	private Serving def, alt;

	public IngredientTemplate() {}

	public IngredientTemplate(String id) {
		super(id);
	}

	public IngredientTemplate(String id, String name) {
		super(id);
		setName(name);
	}

	public void setCalories(String calories) {
		try {
			setCalories(Double.parseDouble(calories));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public void setFat(String fat) {
		try {
			setFat(Double.parseDouble(fat));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public void setCarbs(String carbs) {
		try {
			setCarbs(Double.parseDouble(carbs));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public void setProtein(String protein) {
		try {
			setProtein(Double.parseDouble(protein));
		}
		catch(NumberFormatException e) {
			
		}
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCalories() {
		return this.calories;
	}

	public double getCarbs() {
		return this.carbs;
	}

	public double getProtein() {
		return this.protein;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public double getFat() {
		return fat;
	}
	
	public void setDefaultServing(double size, Unit u) {
		def = new Serving(size, u);
	}

	public void setAlternateServing(double size, Unit u) {
		alt = new Serving(size, u);
	}
	
	public Serving getDefaultServing() {
		return def;
	}
	
	public double getDefaultServingSize() {
		return def.getSize();
	}
	
	public Unit getDefaultServingUnit() {
		return def.getUnit();
	}
	
	public Serving getAlternateServing() {
		return alt;
	}
	
	public double getAlternateServingSize() {
		return alt.getSize();
	}
	
	public Unit getAlternateServingUnit() {
		return alt.getUnit();
	}
}
