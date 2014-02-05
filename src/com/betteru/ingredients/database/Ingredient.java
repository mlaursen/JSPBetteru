package com.betteru.ingredients.database;

import java.util.List;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.forms.CreateIngredientForm;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;

public class Ingredient extends DatabaseObject implements DatabaseObjectListable, DatabaseCreateable {

	private final String LOOKUP = "INGREDIENT_GET_BYID(:ID, :CURSOR)";
	private final String CREATE = "INGREDIENT_INSERT(:NAME, :BRAND, :CATEGORY, :SERV_SIZE, :SERV_UNIT, :ALT_SERV_SIZE, :ALT_SERV_UNIT, :CALS, :FAT, :CARBS, :PROT)";
	private static final String LOOKUP_ALL = "INGREDIENT_GETALL(:CURSOR)";
	private Brand b;
	private Category c;
	private Serving def, alt;
	private Macro fat, carb, protein;
	private Calorie cals;
	public Ingredient() { }
	public Ingredient(String id) {
		super(id);
		Ingredient i = lookup(id);
		setBrand(i.getBrand());
		setCategory(i.getCategory());
		setName(i.getName());
		//setServingUnit(i.getServingUnit());
		//setServingSize(i.getServingSize());
		//setAltServingUnit(i.getAltServingUnit());
		//setAltServingSize(i.getAltServingSize());
		setCalories(i.getCalories());
		setFat(i.getFat());
		setCarbs(i.getCarbs());
		setProtein(i.getProtein());
	}

	public Ingredient(String id, String name) {
		super(id, name);
	}
	
	public Ingredient(CreateIngredientForm f) {
		setName(f.getFieldValue(CreateIngredientForm.NAME));
		String brand = f.getFieldValue(CreateIngredientForm.BRANDS);
		if(BasicValidation.isNumber(brand))
			setBrand(brand);
		else
			setBrand(Brand.lookupByName(brand));
		setCategory(f.getFieldValue(CreateIngredientForm.CATEGORIES));
		//setServingSize(f.getFieldValue(CreateIngredientForm.SERVING_SIZE));
		//setServingUnit(f.getFieldValue(CreateIngredientForm.SERVING_UNIT));
		//setAltServingSize(f.getFieldValue(CreateIngredientForm.ALT_SERVING_SIZE));
		//setAltServingUnit(f.getFieldValue(CreateIngredientForm.ALT_SERVING_UNIT));
		setCalories(f.getFieldValue(CreateIngredientForm.CALORIES));
		setFat(f.getFieldValue(CreateIngredientForm.FAT));
		setCarbs(f.getFieldValue(CreateIngredientForm.CARBS));
		setProtein(f.getFieldValue(CreateIngredientForm.PROTEIN));
	}
	
	public Ingredient(MyResultRow r) {
		super(r.get("id"));
		
	}
	
	public Brand getBrand() {
		return this.b;
	}
	
	public void setBrand(String brandId) {
		setBrand(new Brand(brandId));
	}
	
	public void setBrand(Brand b) {
		this.b = b;
	}
	
	public Category getCategory() {
		return this.c;
	}
	
	public void setCategory(String categoryId) {
		setCategory(new Category(categoryId));
	}
	
	public void setCategory(Category c) {
		this.c = c;
	}
	
	public List<Ingredient> lookupAll() {
		return lookupAll(Ingredient.class);
	}
	
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}

	public Ingredient lookup(String id) {
		return lookup(id, Ingredient.class);
	}
	
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new Ingredient(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	
	@Override
	public boolean create() {
		Object[] params = new Object[] { getName()
									   , getBrand().getId()
									   , getCategory().getId()
									   , getServingSize()
									   , getServingUnit().getId()
									   , getAltServingSize()
									   , getAltServingUnit().getId()
									   , getCalories()
									   , getFat()
									   , getCarbs()
									   , getProtein() };
		return DatabaseManager.executeUpdateProcedure(CREATE, params);
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
