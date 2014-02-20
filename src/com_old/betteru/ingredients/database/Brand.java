/**
 * 
 */
package com_old.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemList;

import com_old.betteru.accounts.AccountChoice;
import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Util;

/**
 * @author mikkel.laursen
 *
 */
public class Brand extends BrandCategoryOutline implements DatabaseCreateable {

	public Brand() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Brand(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Brand(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Brand(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(call("new"), getPrimaryKey());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getPrimaryKey();
	}
	
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new Brand("New Brand", 0));
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		for(int i = 1; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}
	
}