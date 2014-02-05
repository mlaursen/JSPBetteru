package com.betteru.ingredients.database;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;

public abstract class IngredientTemplate extends DatabaseObject implements DatabaseObjectListable {

	private String name;
	private double servingSize, altServingSize;
	private double calories, fat, carbs, protein;

	public IngredientTemplate() {}

	public IngredientTemplate(String id) {
		super(id);
	}

	public IngredientTemplate(String id, String name) {
		super(id);
		setName(name);
	}

	public IngredientTemplate(String id, String name, double ss, double ass, double cals, double f, double carbs, double prot) {
		this(id, name);
		this.servingSize = ss;
		this.altServingSize = ass;
		this.calories = cals;
		this.fat = f;
		this.carbs = carbs;
		this.protein = prot;
	}

	public void setServingSize(String servingSize) {
		try {
			setServingSize(Double.parseDouble(servingSize));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setServingSize(double servingSize) {
		this.servingSize = servingSize;
	}

	public void setAltServingSize(String altServingSize) {
		try {
			setAltServingSize(Double.parseDouble(altServingSize));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setAltServingSize(double altServingSize) {
		this.altServingSize = altServingSize;
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

	public double getServingSize() {
		return this.servingSize;
	}

	public double getAltServingSize() {
		return this.altServingSize;
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

}
