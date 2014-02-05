package com.betteru.ingredients.database;

import java.util.List;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.ingredients.forms.CreateIngredientForm;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;

public class Ingredient extends IngredientTemplate implements DatabaseCreateable {

	private final String LOOKUP = "INGREDIENT_GET_BYID(:ID, :CURSOR)";
	private final String CREATE = "INGREDIENT_INSERT(:NAME, :BRAND, :CATEGORY, :SERV_SIZE, :SERV_UNIT, :ALT_SERV_SIZE, :ALT_SERV_UNIT, :CALS, :FAT, :CARBS, :PROT)";
	private static final String LOOKUP_ALL = "INGREDIENT_GETALL(:CURSOR)";
	private Brand b;
	private Category c;
	private Unit servingUnit, altServingUnit;

	public Ingredient() { }
	public Ingredient(String id) {
		super(id);
		Ingredient i = lookup(id);
		setBrand(i.getBrand());
		setCategory(i.getCategory());
		setName(i.getName());
		setServingUnit(i.getServingUnit());
		setServingSize(i.getServingSize());
		setAltServingUnit(i.getAltServingUnit());
		setAltServingSize(i.getAltServingSize());
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
		setServingSize(f.getFieldValue(CreateIngredientForm.SERVING_SIZE));
		setServingUnit(f.getFieldValue(CreateIngredientForm.SERVING_UNIT));
		setAltServingSize(f.getFieldValue(CreateIngredientForm.ALT_SERVING_SIZE));
		setAltServingUnit(f.getFieldValue(CreateIngredientForm.ALT_SERVING_UNIT));
		setCalories(f.getFieldValue(CreateIngredientForm.CALORIES));
		setFat(f.getFieldValue(CreateIngredientForm.FAT));
		setCarbs(f.getFieldValue(CreateIngredientForm.CARBS));
		setProtein(f.getFieldValue(CreateIngredientForm.PROTEIN));
	}

	public Ingredient(String id, String name, Brand b, Category c, Unit servingUnit, double servingSize, Unit altServingUnit,
			double altServingSize, double calories, double fat, double carbs, double prot) {
		super(id, name, servingSize, altServingSize, calories, fat, carbs, prot);
		this.b = b;
		this.c = c;
		this.servingUnit = servingUnit;
		this.altServingUnit = altServingUnit;
	}
	
	public Ingredient(MyResultRow r) {
		super(r.get("id"), r.get("name"));
		
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
	
	public Unit getServingUnit() {
		return this.servingUnit;
	}
	
	public void setServingUnit(String id) {
		setServingUnit(new Unit(id));
	}
	
	public void setServingUnit(Unit su) {
		this.servingUnit = su;
	}
	
	public Unit getAltServingUnit() {
		return this.altServingUnit;
	}
	
	public void setAltServingUnit(String id) {
		setAltServingUnit(new Unit(id));
	}
	
	
	public void setAltServingUnit(Unit asu) {
		this.altServingUnit = asu;
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

}
