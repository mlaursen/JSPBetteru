package com.betteru.ingredients.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.betteru.ingredients.database.Brand;
import com.betteru.ingredients.database.Category;
import com.betteru.ingredients.database.Unit;
import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlFieldGroup;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.forms.fields.HtmlFieldable;
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
											  CATEGORY_LIST = new Category().getAllChoices();
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
		List<DropdownChoice> units = new Unit().getAll();
		Dropdown defUnit = new Dropdown(SERVING_UNIT, units);
		defUnit.setCanBe0(true);
		
		NumberField altSize = new NumberField(ALT_SERVING_SIZE);
		altSize.setCss("span3");
		Dropdown altUnit = new Dropdown(ALT_SERVING_UNIT, units);
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

}
