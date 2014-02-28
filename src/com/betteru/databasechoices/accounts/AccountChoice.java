/**
 * 
 */
package com.betteru.databasechoices.accounts;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.utils.Util;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.database.DatabaseObjectClassUtil;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.ObjectManager;
import com.github.mlaursen.database.objecttypes.GetAllable;
import com.github.mlaursen.database.objecttypes.Getable;

/**
 * @author mikkel.laursen
 *
 */
public class AccountChoice extends DatabaseObject implements Getable, GetAllable, DropdownChoice {
	{
		setPrimaryKeyName("name");
	}
	
	protected int dropdownKey;
	public AccountChoice() { }
	public AccountChoice(String primaryKey) {
		super();
		this.primaryKey = primaryKey == null ? this.defaultChoice() : primaryKey;
	}
	
	public AccountChoice(String primaryKey, int id) {
		this(primaryKey);
		dropdownKey = id;
	}

	/**
	 * @param r
	 */
	public AccountChoice(MyResultRow r) {
		super();
		setAll(r);
	}
	
	@Override
	public String getDropdownValue() {
		return Util.capitalizeFirst(primaryKey, " ");
	}

	
	@Override
	public String getDropdownKey() {
		return dropdownKey + "";
	}

	@Override
	public void setDropdownKey(int k) {
		dropdownKey = k;
	}

	public String defaultChoice() {
		return "Select your " + (DatabaseObjectClassUtil.formatClassName(getClass(), null, " ").trim());
	}
	
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new AccountChoice(defaultChoice()));
		choices.addAll(new ObjectManager(this.getClass()).getAll(this.getClass()));
		for(int i = 0; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountChoice [" + (primaryKey != null ? "primaryKey=" + primaryKey + ", " : "") + "dropdownKey=" + dropdownKey + "]";
	}

	
}
