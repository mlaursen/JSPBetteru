package com.betteru.meals.database;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyClob;
import com.betteru.database.MyResultRow;

public class Meal_old extends DatabaseObject implements DatabaseCreateable {

	private static final String CREATE = "MEAL_INSERT(:NAME, :DESC)";
	private String name;
	private MyClob description;
	public Meal_old() {	}
	public Meal_old(String id, String name) {
		super(id);
		this.name = name;
	}
	
	public Meal_old(String id, String name, String desc) {
		this(id, name);
		description = new MyClob(desc);
	}
	
	public Meal_old(MyResultRow r) {
		super(r);
		name = r.get("name");
		description = new MyClob(r.get("description"));
	}
	
	public void setDescription(String d) {
		setDescription(new MyClob(d));
	}
	
	public void setDescription(MyClob c) {
		description = c;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public MyClob getDescription() {
		return description;
	}
	
	public String getDescriptionValue() {
		return description.getValue();
	}

	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(CREATE, getName(), getDescription());
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
