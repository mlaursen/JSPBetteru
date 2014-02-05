package com.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class Unit extends DatabaseObject implements DatabaseObjectListable, DropdownChoice {

	private final String LOOKUP = "UNIT_GET_BYID(:ID, :CURSOR)";
	private final String LOOKUP_ALL = "UNIT_GETALL(:CURSOR)";
	private final String CREATE = "UNIT_INSERT(:NAME, :SHORTNAME)";
	private String name, shortName;
	public Unit() { }
	public Unit(String id) {
		super(id);
		Unit u = lookup(id);
		setName(u.getName());
		setShortName(u.getShortName());
	}
	
	public Unit(String id, String name, String shortName) {
		super(id);
		setName(name);
		setShortName(shortName);
	}
	
	public Unit(MyResultRow r) {
		this(r.get("id"), r.get("name"), r.get("short_name"));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String getValue() {
		return getShortName();
	}

	@Override
	public String getKey() {
		return getId();
	}

	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		List<Unit> us = lookupAll();
		for(Unit u : us)
			choices.add((DropdownChoice) u);
		return choices;
	}
	
	public List<Unit> lookupAll() {
		return lookupAll(Unit.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}
	
	public Unit lookup(String id) {
		return lookup(id, Unit.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new Unit(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
}
