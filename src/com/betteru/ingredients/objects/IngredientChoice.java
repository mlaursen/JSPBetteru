/**
 * 
 */
package com.betteru.ingredients.objects;

import java.util.ArrayList;
import java.util.List;

import com.betteru.ingredients.Serving;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

/**
 * @author mikkel.laursen
 *
 */
public class IngredientChoice implements DropdownChoice {

	private String id;
	private String name;
	public IngredientChoice() {}
	public IngredientChoice(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownValue()
	 */
	@Override
	public String getDropdownValue() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownKey()
	 */
	@Override
	public String getDropdownKey() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownKey(int)
	 */
	@Override
	public void setDropdownKey(int k) {
		this.id = k+"";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<Ingredient> is = new Ingredient().getAll(Ingredient.class);
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		for(Ingredient i : is) {
			choices.add(new IngredientChoice(i.getPrimaryKey(), i.getBrand() + " - " + i.getName()));
		}
		return choices;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IngredientChoice [id=" + id + ", name=" + name + "]";
	}

}
