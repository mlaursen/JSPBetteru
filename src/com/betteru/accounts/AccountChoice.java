package com.betteru.accounts;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.utils.Util;

public abstract class AccountChoice extends DatabaseObject implements DropdownChoice, DatabaseObjectListable {

	private String name;
	public AccountChoice() { }

	public AccountChoice(String id) {
		super(id);
	}
	
	public AccountChoice(String id, String n) {
		super(id);
		name = n;
	}
	
	public AccountChoice(MyResultRow r) {
		super(r.get("id"));
		name = r.get("name");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}

	@Override
	public String getValue() {
		return Util.capitalizeFirstLetter(name);
	}

	@Override
	public String getKey() {
		return getId();
	}

	public static boolean isSelected(AccountChoice a) {
		return Integer.parseInt(a.getId()) > 0;
	}
	
	public static boolean isSelected(String id) {
		return Integer.parseInt(id) > 0;
	}
}
