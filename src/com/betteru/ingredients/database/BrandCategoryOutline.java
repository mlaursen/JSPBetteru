/**
 * 
 */
package com.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.accounts.AccountChoice;
import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Util;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemable;

/**
 * @author mikkel.laursen
 *
 */
public abstract class BrandCategoryOutline extends DatabaseObjectListable implements DropdownChoice, SidebarItemable {
	{ setPrimaryKeyName("name"); }
	
	private int id;
	public BrandCategoryOutline() { }

	/**
	 * @param primaryKey
	 */
	public BrandCategoryOutline(String primaryKey) {
		super(primaryKey);
	}
	
	public BrandCategoryOutline(String primaryKey, int id) {
		this(primaryKey);
		this.id = id;
	}

	/**
	 * @param r
	 */
	public BrandCategoryOutline(MyResultRow r) {
		super();
		setPrimaryKey(r.get(getPrimaryKeyName()));
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.sidebar.SidebarItemable#getName()
	 */
	@Override
	public String getName() {
		return getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownValue()
	 */
	@Override
	public String getDropdownValue() {
		return getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownKey()
	 */
	@Override
	public String getDropdownKey() {
		return id+"";
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownKey(int)
	 */
	@Override
	public void setDropdownKey(int k) {
		// TODO Auto-generated method stub
		this.id = k;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownValue(java.lang.String)
	 */
	@Override
	public void setDropdownValue(String v) {
		// TODO Auto-generated method stub
		// do nothing
	}

	public String defaultChoice() {
		return "Select your " + Util.formatClassName(getClass(), null, " ");
	}
	
	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new AccountChoice(defaultChoice(), 0));
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		for(int i = 0; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}
}