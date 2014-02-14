package com.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Util;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class AccountChoice extends DatabaseObjectListable implements DropdownChoice {
	{ 
		setPrimaryKeyName("name"); 
	}
	public AccountChoice() { }
	public AccountChoice(String n) {
		super(n);
		if(n == null)
			setPrimaryKey(defaultChoice());
	}
	
	public AccountChoice(MyResultRow r) {
		super();
		setPrimaryKey(r.get(getPrimaryKeyName()));
	}
	
	@Override
	public void setPrimaryKey(MyResultRow r) {
		String k = r.get(getPrimaryKeyName());
		setPrimaryKey(k == null ? defaultChoice() : k);
	}
	
	@Override
	public String getDropdownKey() {
		return getPrimaryKey();
	}
	
	@Override
	public String getDropdownValue() {
		return this.getPrimaryKey();
	}
	
	public String defaultChoice() {
		return "Select your " + Util.formatClassName(getClass(), null, " ");
	}
	
	/**
	 * 
	 */
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new AccountChoice(defaultChoice()));
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		return choices;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountChoice [name=" + this.getPrimaryKey() + "]";
	}

}
