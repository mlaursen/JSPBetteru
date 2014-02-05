package com.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemList;
import com.github.mlaursen.bootstrap.sidebar.SidebarItemable;

public class Category extends DatabaseObject implements SidebarItemable, DropdownChoice, DatabaseObjectListable {

	private final String LOOKUP = "CATEGORY_GETNAME_BYID(:ID, :CURSOR)";
	private final String CREATE = "CATEGORY_INSERT(:NAME)";
	private final static String GET_ALL = "CATEGORY_GETALL(:CURSOR)";
	private String name;
	public Category() { }
	public Category(String id) {
		super(id);
		Category c = lookup(id);
		setName(c.getName());
	}
	
	public Category(String id, String name) {
		super(id);
		setName(name);
	}

	public Category(MyResultRow r) {
		this(r.get("id"), r.get("name"));
	}
	
	@Override
	public String getValue() {
		return this.name;
	}

	@Override
	public String getKey() {
		return getId();
	}

	public List<Category> lookupAll() {
		return lookupAll(Category.class);
	}
	
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(GET_ALL).toListOf(type);
	}

	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		List<Category> cs = lookupAll();
		for(Category c : cs)
			choices.add((DropdownChoice) c);
		return choices;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public Category lookup(String id) {
		return lookup(id, Category.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new Category(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	public static SidebarItemList<Category> getSidebarList() {
		return new SidebarItemList<Category>("categories",
				  "Categories",
				  "categories",
				  DatabaseManager.getStoredProcedureCursor(GET_ALL).toListOf(Category.class));
	}
}
