/**
 * 
 */
package com_old.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.utils.Util;

import com_old.betteru.accounts.AccountChoice;
import com_old.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Category extends BrandCategoryOutline {
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Category(String primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Category(String primaryKey, int id) {
		super(primaryKey, id);
	}

	/**
	 * @param r
	 */
	public Category(MyResultRow r) {
		super(r);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Util.capitalizeFirst(getPrimaryKey());
	}
	
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		for(int i = 0; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}
}
