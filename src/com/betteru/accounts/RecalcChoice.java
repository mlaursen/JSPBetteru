package com.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class RecalcChoice extends AccountChoice {

	private static final String LOOKUP = "RECALC_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "RECALC_GETALL(:CURSOR)";
	private static final String GET = "RECALC_GET_BYNAME(:NAME, :CURSOR)";
	public RecalcChoice() { }

	public RecalcChoice(String id) {
		super(id);
		RecalcChoice r = lookup(id);
		setName(r.getName());
	}
	
	public RecalcChoice(String id, String name) {
		super(id, name);
	}
	
	public RecalcChoice(MyResultRow r) {
		super(r);
	}
	
	public static RecalcChoice getRecalcChoice(String name) {
		return new RecalcChoice(DatabaseManager.getStoredProcedureFirstRow(GET, name));
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new RecalcChoice(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	public RecalcChoice lookup(String id) {
		return lookup(id, RecalcChoice.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL).toListOf(type);
	}

	public List<RecalcChoice> lookupAll() {
		return lookupAll(RecalcChoice.class);
	}

	
	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> recalcs = new ArrayList<DropdownChoice>();
		List<RecalcChoice> rs = lookupAll();
		for(RecalcChoice r : rs) {
			recalcs.add((DropdownChoice) r);
		}
		return recalcs;
	}
}
