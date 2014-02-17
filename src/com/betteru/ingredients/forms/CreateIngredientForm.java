package com.betteru.ingredients.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.betteru.ingredients.Calorie;
import com.betteru.ingredients.Carbohydrate;
import com.betteru.ingredients.Fat;
import com.betteru.ingredients.Protein;
import com.betteru.ingredients.Serving;
import com.betteru.ingredients.database.Brand;
import com.betteru.ingredients.database.Category;
import com.betteru.ingredients.database.FoodUnit;
import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.forms.fields.TextAction;
import com.github.mlaursen.bootstrap.forms.fields.button.Button;
import com.github.mlaursen.bootstrap.forms.fields.button.SubmitButton;
import com.github.mlaursen.bootstrap.forms.fields.input.NumberField;
import com.github.mlaursen.bootstrap.forms.fields.input.TextField;

public class CreateIngredientForm extends HtmlForm {

	public static final String BRANDS="brands", CATEGORIES="categories", NAME="name", SERVING_SIZE="serving_size", SERVING_UNIT="serving_unit",
			ALT_SERVING_SIZE="alt_serving_size", ALT_SERVING_UNIT="alt_serving_unit", CALORIES="calories", FAT="fat", CARBS="carbs", PROTEIN="protein";
	private static final String ACTION = "create.jsp";
	private static final List<DropdownChoice> BRAND_LIST = new Brand().getAllChoices(),
											  CATEGORY_LIST = new Category().getAllChoices(),
											  FOOD_UNIT_LIST = new FoodUnit().getAllChoices();
	public CreateIngredientForm() {
		super(ACTION, "createmiddlewaretoken");
		TextField name = new TextField(NAME);
		name.setLabel("Ingredient Name:");
		name.setPlaceholder("Ingredient Name");
		
		TextAction brands = new TextAction(BRANDS, BRAND_LIST);
		brands.setCss("span7");
		
		Dropdown categories = new Dropdown(CATEGORIES, CATEGORY_LIST);
		categories.setCanBe0(true);
		
		NumberField defSize = new NumberField(SERVING_SIZE);
		defSize.setCss("span3");
		
		//Dropdown defUnit = new Dropdown(SERVING_UNIT, FOOD_UNIT_LIST);
		//System.out.println(FOOD_UNIT_LIST);
		//System.out.println(defUnit);
		//System.out.println(defUnit.toHtml());
		/*
		NumberField altSize = new NumberField(ALT_SERVING_SIZE);
		altSize.setCss("span3");
		Dropdown altUnit = new Dropdown(ALT_SERVING_UNIT, FOOD_UNIT_LIST);
		altUnit.setChosen(3);
		altUnit.setCanBe0(true);
		NumberField cals = new NumberField(CALORIES);
		cals.setCss("span2");
		NumberField fat = new NumberField(FAT);
		fat.setCss("span2");
		NumberField carbs = new NumberField(CARBS);
		carbs.setCss("span2");
		NumberField protein = new NumberField(PROTEIN);
		protein.setCss("span2");
		
		Button submit = new SubmitButton();
		submit.setValue("Create Ingredient");
		addFields(ControlGroup.wrap(name, brands, categories, defSize, defUnit, altSize, altUnit, cals, fat, carbs, protein, submit));
		*/
		addFields(ControlGroup.wrap(name, brands, categories, defSize));
	}

	public CreateIngredientForm(HttpServletRequest request) {
		this();
		updateValue(NAME, request);
		updateValue(BRANDS, request);
		updateValue(CATEGORIES, request);
		updateValue(SERVING_SIZE, request);
		updateValue(SERVING_UNIT, request);
		updateValue(ALT_SERVING_SIZE, request);
		updateValue(ALT_SERVING_UNIT, request);
		updateValue(CALORIES, request);
		updateValue(FAT, request);
		updateValue(CARBS, request);
		updateValue(PROTEIN, request);
	}
	
	public String getName() {
		return this.getFieldValue(NAME);
	}
	
	public Brand getBrand() {
		return new Brand(this.getFieldValue(BRANDS));
	}
	
	public Category getCategory() {
		return new Category(this.getFieldValue(CATEGORIES));
	}
	
	public Serving getDefaultServing() {
		double amt = Double.parseDouble(this.getFieldValue(SERVING_SIZE));
		FoodUnit fu = new FoodUnit(this.getFieldValue(SERVING_UNIT));
		return new Serving(amt, fu);
	}
	
	public Serving getAlternateServing() {
		double amt = Double.parseDouble(this.getFieldValue(ALT_SERVING_SIZE));
		FoodUnit fu = new FoodUnit(this.getFieldValue(ALT_SERVING_UNIT));
		return new Serving(amt, fu);
	}
	
	public Calorie getCalories() {
		return new Calorie(Double.parseDouble(this.getFieldValue(CALORIES)));
	}
	
	public Fat getFat() {
		return new Fat(Double.parseDouble(this.getFieldValue(FAT)));
	}
	
	public Carbohydrate getCarbs() {
		return new Carbohydrate(Double.parseDouble(this.getFieldValue(CARBS)));
	}
	
	public Protein getProtein() {
		return new Protein(Double.parseDouble(this.getFieldValue(PROTEIN)));
	}
}
