package com.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class UnitChoice extends AccountChoice {

	private static final String LOOKUP = "UNIT_SYSTEM_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "UNIT_SYSTEM_GETALL(:CURSOR)";
	private static final String GET = "UNIT_SYSTEM_GET_BYNAME(:NAME, :CURSOR)";
	public UnitChoice() { }

	public UnitChoice(String id) {
		super(id);
		UnitChoice u = lookup(id);
		setName(u.getName());
	}

	public UnitChoice(String id, String n) {
		super(id, n);
	}

	public UnitChoice(MyResultRow r) {
		super(r);
	}
	
	public static UnitChoice getUnitChoice(String name) {
		return new UnitChoice(DatabaseManager.getStoredProcedureFirstRow(GET, name));
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new UnitChoice(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	public UnitChoice lookup(String id) {
		return lookup(id, UnitChoice.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}

	public List<UnitChoice> lookupAll() {
		return lookupAll(UnitChoice.class);
	}
	

	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> units = new ArrayList<DropdownChoice>();
		List<UnitChoice> us = lookupAll();
		for(UnitChoice u : us) {
			units.add((DropdownChoice) u);
		}
		return units;
	}
}
