/**
 * 
 */
package com_old.betteru.ingredients.database;

import java.util.List;

import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.DatabaseObjectListable;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;
import com_old.betteru.ingredients.Calorie;
import com_old.betteru.ingredients.Carbohydrate;
import com_old.betteru.ingredients.Fat;
import com_old.betteru.ingredients.Protein;
import com_old.betteru.ingredients.Serving;
import com_old.betteru.ingredients.forms.CreateIngredientForm;

/**
 * @author mikkel.laursen
 *
 */
public class Ingredient extends DatabaseObjectListable implements DatabaseCreateable {
	{
		Procedure pFilter = new Procedure("filter", "category", "brand");
		Procedure pNew = new Procedure( "new"
									  , "brand"
									  , "category"
									  , "servingsize"
									  , "servingunit"
									  , "altservingsize"
									  , "altservingunit"
									  , "calories"
									  , "fat"
									  , "carbs"
									  , "protein");
		addProcedure(pFilter);
		addProcedure(pNew);
	}
	private String name;
	private Brand brand;
	private Category category;
	private Serving defaultServing, alternateServing;
	private Calorie calories;
	private Carbohydrate carbs;
	private Fat fat;
	private Protein protein;
	public Ingredient() { }
	public Ingredient(String primaryKey) {
		super(primaryKey);
		Ingredient i = get(primaryKey, Ingredient.class);
		setName(i.getName());
		setBrand(i.getBrand());
		setCategory(i.getCategory());
		setDefaultServing(i.getDefaultServing());
		setAlternateServing(i.getAlternateServing());
		setCalories(i.getCalories());
		setFat(i.getFat());
		setCarbs(i.getCarbs());
		setProtein(i.getProtein());
	}

	/**
	 * @param r
	 */
	public Ingredient(MyResultRow r) {
		super(r);
		setName(r);
		setBrand(r);
		setCategory(r);
		setDefaultServing(r);
		setAlternateServing(r);
		setCalories(r);
		setFat(r);
		setCarbs(r);
		setProtein(r);
	}
	
	public Ingredient(CreateIngredientForm f) {
		super();
		setName(f.getName());
		setBrand(f.getBrand());
		setCategory(f.getCategory());
		setDefaultServing(f.getDefaultServing());
		setAlternateServing(f.getAlternateServing());
		setCalories(f.getCalories());
		setFat(f.getFat());
		setCarbs(f.getCarbs());
		setProtein(f.getProtein());
	}
	
	public List<Ingredient> filter(String catg, String brand) {
		return DatabaseManager.getStoredProcedureCursor(call("filter"), catg, brand).toListOf(Ingredient.class);
	}
	
	public List<Ingredient> getAll() {
		return getAll(Ingredient.class);
	}

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure( call("new")
													 , getName()
													 , getBrand().getPrimaryKey()
													 , getCategory().getPrimaryKey()
													 , getDefaultServingSize()
													 , getDefaultServingUnit()
													 , getAlternateServingSize()
													 , getAlternateServingUnit()
													 , getCalories().getAmount()
													 , getFat().getAmount()
													 , getCarbs().getAmount()
													 , getProtein().getAmount());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setName(MyResultRow r) {
		this.name = r.get("name");
	}

	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public void setBrand(MyResultRow r) {
		this.brand = new Brand(r.get("brand"));
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setCategory(MyResultRow r) {
		this.category = new Category(r.get("category"));
	}

	/**
	 * @return the defaultServing
	 */
	public Serving getDefaultServing() {
		return defaultServing;
	}
	
	public double getDefaultServingSize() {
		return defaultServing.getSize();
	}
	public String getDefaultServingUnit() {
		return defaultServing.getUnitName();
	}

	/**
	 * @param defaultServing the defaultServing to set
	 */
	public void setDefaultServing(Serving def) {
		this.defaultServing = def;
	}
	
	public void setDefaultServing(MyResultRow r) {
		this.defaultServing = new Serving(Double.parseDouble(r.get("serving_size")), new FoodUnit(r.get("serving_unit")));
	}

	/**
	 * @return the alternateServing
	 */
	public Serving getAlternateServing() {
		return alternateServing;
	}
	
	public double getAlternateServingSize() {
		return alternateServing.getSize();
	}
	
	public String getAlternateServingUnit() {
		return alternateServing.getUnitName();
	}

	/**
	 * @param alternateServing the alternateServing to set
	 */
	public void setAlternateServing(Serving alt) {
		this.alternateServing = alt;
	}
	
	public void setAlternateServing(MyResultRow r) {
		this.alternateServing = new Serving(Double.parseDouble(r.get("alt_serving_size")), new FoodUnit(r.get("alt_serving_unit")));
	}

	/**
	 * @return the calories
	 */
	public Calorie getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(Calorie calories) {
		this.calories = calories;
	}
	
	public void setCalories(MyResultRow r) {
		calories = new Calorie(Double.parseDouble(r.get("calories")));
	}

	/**
	 * @return the carbs
	 */
	public Carbohydrate getCarbs() {
		return carbs;
	}

	/**
	 * @param carbs the carbs to set
	 */
	public void setCarbs(Carbohydrate carbs) {
		this.carbs = carbs;
	}
	
	public void setCarbs(MyResultRow r) {
		carbs = new Carbohydrate(Double.parseDouble(r.get("carbs")));
	}

	/**
	 * @return the fat
	 */
	public Fat getFat() {
		return fat;
	}

	/**
	 * @param fat the fat to set
	 */
	public void setFat(Fat fat) {
		this.fat = fat;
	}
	
	public void setFat(MyResultRow r) {
		fat = new Fat(Double.parseDouble(r.get("fat")));
	}

	/**
	 * @return the protein
	 */
	public Protein getProtein() {
		return protein;
	}

	/**
	 * @param protein the protein to set
	 */
	public void setProtein(Protein protein) {
		this.protein = protein;
	}
	
	public void setProtein(MyResultRow r) {
		protein = new Protein(Double.parseDouble(r.get("protein")));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingredient [primaryKey=" + getPrimaryKey() + ", name=" + name + ", brand=" + brand + ", category=" + category
				+ ", defaultServing=" + defaultServing + ", alternateServing=" + alternateServing + ", calories=" + calories + ", carbs=" + carbs + ", fat=" + fat + ", protein=" + protein
				+ "]";
	}
}
