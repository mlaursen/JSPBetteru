package com.betteru.ingredients.database;

import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.utils.StringNumberFormat;

public class IngredientView extends DatabaseObject implements DatabaseObjectListable {

	private static final String LOOKUP = "INGREDIENT_VIEW_GET_BYID(:ID, :CURSOR)";
	private final static String LOOKUP_ALL = "INGREDIENT_VIEW_GETALL(:CURSOR)";
	private final static String FILTER = "INGREDIENT_VIEW_FILTERBY(:CATEGORY, :BRAND, :CURSOR)";
	private String name, brand, category, servingUnit, servingUnitShort, servingSize, altServingUnit, altServingUnitShort, altServingSize, calories, fat, carbs, protein;
	
	public IngredientView() { }
	public IngredientView(MyResultRow r) {
		super(r.get("id"));
		this.name = r.get("ingredient_name");
		this.brand = r.get("brand_name");
		this.category = r.get("category_name");
		this.servingUnit = r.get("serving_unit");
		this.servingUnitShort = r.get("serving_unit_short");
		this.servingSize = StringNumberFormat.formatFractionString(r.get("serving_size"));
		this.altServingUnit = r.get("alt_serving_unit");
		this.altServingUnitShort = r.get("alt_serving_unit_short");
		this.altServingSize = StringNumberFormat.formatFractionString(r.get("alt_serving_size"));
		this.calories = StringNumberFormat.formatDecimalString(r.get("calories"));
		this.fat = StringNumberFormat.formatDecimalString(r.get("fat"));
		this.carbs = StringNumberFormat.formatDecimalString(r.get("carbs"));
		this.protein = StringNumberFormat.formatDecimalString(r.get("protein"));
	}
	
	public String getName() {
		return this.name;
	}

	public String getBrand() {
		return this.brand;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getServingUnit() {
		return this.servingUnit;
	}
	
	public String getAltServingUnit() {
		return this.altServingUnit;
	}
	
	public String getServingUnitShort() {
		return this.servingUnitShort;
	}
	
	public String getAltServingUnitShort() {
		return this.altServingUnitShort;
	}
	
	public String getServingSize() {
		return this.servingSize;
	}
	
	public String getAltServingSize() {
		return this.altServingSize;
	}
	
	public String getCalories() { 
		return this.calories;
	}
	
	public String getFat() {
		return this.fat;
	}
	
	public String getCarbs() {
		return this.carbs;
	}
	
	public String getProtein() {
		return this.protein;
	}
	
	public static List<IngredientView> filter(String categoryId, String brandId) {
		return DatabaseManager.getStoredProcedureCursor(FILTER, categoryId, brandId).toListOf(IngredientView.class);
	}
	
	public String toString() {
		String s = "Ingredient View(";
		s += "Name: " + this.name + ", Brand: " + brand;
		s += ", Category: " + category + ", ServingUnit:" + servingUnit;
		s += "{" + servingUnitShort + "}, servingSIze:" + servingSize;
		s += ", Alt: " + altServingUnit + "{" + altServingUnitShort + "}, Size: " + altServingSize;
		s += ", Cal:" + calories + ", fat: " + fat + ", carbs: " + carbs + ", prot: " + protein;
		return s;
	}
	
	public static String toHtmlTable(List<IngredientView> ingredients) {
		String h = "      <h4>Ingredients</h4>\n";
		h += "      <table class=\"table table-striped table-bordered table-hover table-condensed\">\n";
		h += "        <tr>\n";
		h += "          <th>Name</th>\n";
		h += "          <th>Brand</th>\n";
		h += "          <th>Category</th>\n";
		h += "          <th>Serving Size (default)</th>\n";
		h += "          <th>Serving Size (alternate)</th>\n";
		h += "          <th>Calories (kCal)</th>\n";
		h += "          <th>Fat (g)</th>\n";
		h += "          <th>Carbohydrates (g)</th>\n";
		h += "          <th>Protein (g)</th>\n";
		h += "        </tr>\n";
		for(IngredientView i : ingredients) {
			h += "          <tr>\n";
			h += String.format("          <td>%s</td>\n", i.getName());
			h += String.format("          <td>%s</td>\n", i.getBrand());
			h += String.format("          <td>%s</td>\n", i.getCategory());
			h += String.format("          <td>%s (%s)</td>\n", i.getServingSize(), i.getServingUnitShort());
			h += String.format("          <td>%s (%s)</td>\n", i.getAltServingSize(), i.getAltServingUnitShort());
			h += String.format("          <td>%s</td>\n", i.getCalories());
			h += String.format("          <td>%s</td>\n", i.getFat());
			h += String.format("          <td>%s</td>\n", i.getCarbs());
			h += String.format("          <td>%s</td>\n", i.getProtein());
			h += "        </tr>\n";
		}
		h += "      </table>\n";
		return h;
	}
	
	public IngredientView lookup(String id) {
		return lookup(id, IngredientView.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new IngredientView(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

	
	public List<IngredientView> lookupAll() {
		return lookupAll(IngredientView.class);
	}
	
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}
}
