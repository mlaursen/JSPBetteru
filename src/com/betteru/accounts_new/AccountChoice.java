/**
 * 
 */
package com.betteru.accounts_new;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.Util;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.database.DatabaseObjectManager;
import com.github.mlaursen.database.MyResultRow;
import com.github.mlaursen.database.objects.DatabaseGetable;
import com.github.mlaursen.database.objects.DatabaseListable;
import com.github.mlaursen.database.objects.DatabaseObject;

/**
 * @author mikkel.laursen
 *
 */
public abstract class AccountChoice extends DatabaseObject implements DatabaseGetable, DatabaseListable, DropdownChoice {
	{ setPrimaryKeyName("name"); }
	private int id;
	public AccountChoice() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public AccountChoice(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}
	
	public AccountChoice(String primaryKey, int id) {
		super(primaryKey);
		this.id = id;
	}

	/**
	 * @param r
	 */
	public AccountChoice(MyResultRow r) {
		super(r);
		
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
		int id = k;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownValue(java.lang.String)
	 */
	@Override
	public void setDropdownValue(String v) {
		// TODO Auto-generated method stub

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

	/* (non-Javadoc)
	 * @see com.github.mlaursen.database.objects.DatabaseListable#getListableParameters()
	 */
	@Override
	public String[] getListableParameters() {
		return new String[]{};
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.database.objects.DatabaseGetable#getGetableParameters()
	 */
	@Override
	public String[] getGetableParameters() {
		return new String[] { "name" };
	}
	
}
