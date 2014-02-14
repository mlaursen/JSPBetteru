package com.betteru.accounts;

import java.util.List;

import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public abstract class AccountChoice extends DatabaseObjectListable implements DropdownChoice {
	{ setPrimaryKeyName("name"); }
	
	
	public AccountChoice() { }
	public AccountChoice(String n) {
		super(n);
	}
	
	public AccountChoice(MyResultRow r) {
		super();
		setPrimaryKey(r.get(getPrimaryKeyName()));
	}
	
	@Override
	public String getDropdownKey() {
		return getPrimaryKey();
	}
	
	@Override
	public String getDropdownValue() {
		return this.getPrimaryKey();
	}
	
	/**
	 * 
	 */
	public List<DropdownChoice> getAllChoices() {
		return (List<DropdownChoice>) this.getAll(this.getClass());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountChoice [name=" + this.getPrimaryKey() + "]";
	}

}
