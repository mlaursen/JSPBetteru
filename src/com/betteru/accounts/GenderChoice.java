package com.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.database.MyResultSet;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class GenderChoice extends AccountChoice {

	private final String LOOKUP = "GENDER_GET_BYID(:ID, :CURSOR)";
	private final String LOOKUP_ALL = "GENDER_GETALL(:PCURSOR)";
	private static final String GET = "GENDER_GET_BYNAME(:NAME, :CURSOR)";
	public GenderChoice() {	}

	public GenderChoice(String id) {
		super(id);
		setName(this.lookup(id).getName());
	}
	
	public GenderChoice(String id, String n) {
		super(id, n);
	}
	
	public GenderChoice(MyResultRow r) {
		super(r);
	}

	public static GenderChoice getGenderChoice(String name) {
		return new GenderChoice(DatabaseManager.getStoredProcedureFirstRow(GET, name));
	}

	@Override
	public <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		MyResultRow r = DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id);
		GenderChoice g = new GenderChoice(r);
		return type.cast(g);
	}
	
	public GenderChoice lookup(String id) {
		return lookup(id, GenderChoice.class);
	}

	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		MyResultSet set = DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL);
		return set.toListOf(type);
	}
	
	public List<GenderChoice> lookupAll() {
		return lookupAll(GenderChoice.class);
	}

	
	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> genders = new ArrayList<DropdownChoice>();
		List<GenderChoice> gs = lookupAll();
		for(GenderChoice g : gs) {
			genders.add((DropdownChoice) g);
		}
		return genders;
	}
	
}
