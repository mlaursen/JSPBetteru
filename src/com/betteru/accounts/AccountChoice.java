package com.betteru.accounts;

import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public abstract class AccountChoice extends DatabaseObject implements DropdownChoice, DatabaseObjectListable {
	{ setPrimaryKey("name"); }
	
	public AccountChoice() { }
	public AccountChoice(String n) {
		super(n);
	}
	
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor("").toListOf(type);
	}
}
