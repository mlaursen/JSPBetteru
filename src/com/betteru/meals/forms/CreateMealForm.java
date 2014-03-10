/**
 * 
 */
package com.betteru.meals.forms;

import javax.servlet.http.HttpServletRequest;

import com.betteru.ingredients.objects.IngredientChoice;
import com.github.mlaursen.bootstrap.forms.HtmlForm;
import com.github.mlaursen.bootstrap.forms.fields.ControlGroup;
import com.github.mlaursen.bootstrap.forms.fields.Dropdown;
import com.github.mlaursen.bootstrap.forms.fields.TextArea;
import com.github.mlaursen.bootstrap.forms.fields.input.NumberField;
import com.github.mlaursen.bootstrap.forms.fields.input.TextField;

/**
 * @author mikkel.laursen
 *
 */
public class CreateMealForm extends HtmlForm {
	
	private int numMealParts;
	//public static final List<Ingredient> ALL_INGREDIENTS = new Ingredient().getAll(Ingredient.class);
	private static final String ACTION = "create.jsp";
	//private ObjectManager manager;
	public CreateMealForm() {
		super(ACTION, "createmiddlewaretoken");
		//this.manager = manager;
		numMealParts = 1;
		TextField name = new TextField("name");
		name.setLabel("Meal Name");
		name.setPlaceholder("Meal Name");
		
		TextArea description = new TextArea("description", 3);
		description.setValue("");
		
		this.addFields(ControlGroup.wrap(name, description));
		this.addMealPart();
		this.addMealPart();
		this.addMealPart();
	}
	
	public CreateMealForm(HttpServletRequest request) {
		this();
		
	}
	
	public void addMealPart() {
		Dropdown ingredients = new Dropdown("ingredients", new IngredientChoice().getAllChoices());
		ingredients.setName(ingredients.getName()+numMealParts);
		ingredients.setCanBe0(true);
		
		NumberField amount = new NumberField("amount");
		amount.setName(amount.getName()+this.numMealParts);
		amount.setCss("span3");
		
		//Dropdown serving = new Dropdown("serving", null);
		
		this.addFields(ControlGroup.wrap(ingredients, amount));
		this.numMealParts++;
	}
}
