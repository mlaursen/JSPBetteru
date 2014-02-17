package com.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemList;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemable;

public class Brand_Old extends DatabaseObject implements SidebarItemable, DropdownChoice, DatabaseObjectListable, DatabaseCreateable {

	private final String LOOKUP = "BRAND_GETNAME_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_BY_NAME = "BRAND_GET_BYNAME(:NAME, :CURSOR)";
	private final String CREATE = "BRAND_INSERT(:NAME)";
	private static final String GET_ALL = "BRAND_GETALL(:CURSOR)";
	//private static final String LIMIT_GET_ALL = "BRAND_GETALL_WITHIN(:MIN, :MAX, :CURSOR)";
	private String name;
	public Brand_Old() { }
	public Brand_Old(String id) {
		super(id);
		Brand_Old b = lookup(id);
		setName(b.getName());
	}
	
	public Brand_Old(String id, String name) {
		super(id);
		setName(name);
	}
	
	public Brand_Old(MyResultRow r) {
		this(r.get("id"), r.get("name"));
	}
	
	public static SidebarItemList<Brand_Old> getSidebarList() {
		return new SidebarItemList<Brand_Old>("brand",
										  "Brands",
										  "brands",
										  DatabaseManager.getStoredProcedureCursor(GET_ALL).toListOf(Brand_Old.class));
	}

	@Override
	public String getValue() {
		return name;
	}

	@Override
	public String getKey() {
		return getId();
	}
	
	public List<DropdownChoice> getForForm() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new Brand_Old("-1", "New Brand_Old"));
		List<Brand_Old> brand_Olds = lookupAll();
		for(Brand_Old b : brand_Olds)
			choices.add((DropdownChoice) b);
		return choices;
	}

	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		List<Brand_Old> brand_Olds = lookupAll();
		for(Brand_Old b : brand_Olds)
			choices.add((DropdownChoice) b);
		return choices;
	}
	
	public List<Brand_Old> lookupAll() {
		return lookupAll(Brand_Old.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(GET_ALL).toListOf(type);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new Brand_Old(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	public Brand_Old lookup(String id) {
		return lookup(id, Brand_Old.class);
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public static Brand_Old lookupByName(String n) {
		return new Brand_Old(DatabaseManager.getStoredProcedureFirstRow(LOOKUP_BY_NAME, n));
	}
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(CREATE, getName());
	}
}
