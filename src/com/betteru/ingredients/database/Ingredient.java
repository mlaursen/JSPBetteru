package com.betteru.ingredients.database;

import java.util.List;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Macro;
import com.betteru.ingredients.Protein;
import com.betteru.ingredients.forms.CreateIngredientForm;
import com.github.mlaursen.bootstrap.forms.fields.errors.BasicValidation;

public class Ingredient extends DatabaseObject implements DatabaseObjectListable, DatabaseCreateable {

	private final String LOOKUP = "INGREDIENT_GET_BYID(:ID, :CURSOR)";
	private final String CREATE = "INGREDIENT_INSERT(:NAME, :BRAND, :CATEGORY, :SERV_SIZE, :SERV_UNIT, :ALT_SERV_SIZE, :ALT_SERV_UNIT, :CALS, :FAT, :CARBS, :PROT)";
	private static final String LOOKUP_ALL = "INGREDIENT_GETALL(:CURSOR)";
	
	private String name;
	private Brand_Old b;
	private Category_Old c;
	private Serving def, alt;
	private Macro fat, carbs, protein;
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
	
	public Ingredient(CreateIngredientForm f) {
		setName(f.getFieldValue(CreateIngredientForm.NAME));
		String brand = f.getFieldValue(CreateIngredientForm.BRANDS);
		if(BasicValidation.isNumber(brand))
			setBrand(brand);
		else
			setBrand(Brand_Old.lookupByName(brand));
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
	
	public Brand_Old getBrand() {
		return this.b;
	}
	
	public void setBrand(String brandId) {
		setBrand(new Brand_Old(brandId));
	}
	
	public void setBrand(Brand_Old b) {
		this.b = b;
	}
	
	public Category_Old getCategory() {
		return this.c;
	}
	
	public void setCategory(String categoryId) {
		setCategory(new Category_Old(categoryId));
	}
	
	public void setCategory(Category_Old c) {
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
									   , getDefaultServingSize()
									   , getDefaultServingUnitId()
									   , getAlternateServingSize()
									   , getAlternateServingUnitId()
									   , getCalories()
									   , getFat()
									   , getCarbs()
									   , getProtein() };
		return DatabaseManager.executeStoredProcedure(CREATE, params);
	}
	


	public void setCalories(String calories) {
		try {
			setCalories(Double.parseDouble(calories));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setCalories(double amt) {
		cals = new Calorie(amt);
	}

	public void setFat(String fat) {
		try {
			setFat(Double.parseDouble(fat));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setFat(double amt) {
		fat = new Fat(amt);
	}

	public void setCarbs(String carbs) {
		try {
			setCarbs(Double.parseDouble(carbs));
		}
		catch (NumberFormatException e) {

		}
	}

	public void setCarbs(double amt) {
		carbs = new Carbohydrate(amt);
	}

	public void setProtein(String protein) {
		try {
			setProtein(Double.parseDouble(protein));
		}
		catch(NumberFormatException e) {
			
		}
	}
	public void setProtein(double amt) {
		protein = new Protein(amt);
	}

	public double getCalories() {
		return cals.getAmount();
	}

	public double getCarbs() {
		return carbs.getAmount();
	}

	public double getProtein() {
		return protein.getAmount();
	}

	public double getFat() {
		return fat.getAmount();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
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
	
	/**
	 * 
	 * @return
	 */
	public Unit getDefaultServingUnit() {
		return def.getUnit();
	}
	
	public String getDefaultServingUnitId() {
		return def.getUnit().getId();
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
	
	public String getAlternateServingUnitId() {
		return alt.getUnit().getId();
	}

}
