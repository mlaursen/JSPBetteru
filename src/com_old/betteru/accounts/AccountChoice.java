package com_old.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com_old.betteru.database.DatabaseObjectListable;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Util;

public class AccountChoice extends DatabaseObjectListable implements DropdownChoice {
	{ 
		setPrimaryKeyName("name"); 
	}
	private int id;
	public AccountChoice() { }
	public AccountChoice(String n) {
		super(n);
		if(n == null)
			setPrimaryKey(defaultChoice());
	}
	
	public AccountChoice(String n, int id) {
		this(n);
		this.id = id;
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
		return id+"";
	}
	
	@Override
	public String getDropdownValue() {
		String pk = getPrimaryKey();
		return pk.contains("your") ? pk : com.github.mlaursen.bootstrap.utils.Util.capitalizeFirst(pk);
	}
	
	@Override
	public void setDropdownKey(int i) {
		id = i;
	}
	
	public String defaultChoice() {
		return "Select your " + Util.formatClassName(getClass(), null, " ");
	}
	
	/**
	 * 
	 */
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new AccountChoice(defaultChoice(), 0));
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
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
		return "AccountChoice [name=" + this.getPrimaryKey() + "]";
	}

}
