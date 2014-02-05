package com.betteru.accounts;

import java.util.ArrayList;
import java.util.List;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.database.MyResultSet;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

public class MultiplierChoice extends AccountChoice {

	private static final String LOOKUP = "MULTIPLIER_GET_BYID(:ID, :CURSOR)";
	private static final String LOOKUP_ALL = "MULTIPLIER_GETALL(:PCURSOR)";
	private static final String GET = "MULTIPLIER_GET_BYNAME(:NAME, :CURSOR)";
	private double multiplier;
	public MultiplierChoice() { }

	public MultiplierChoice(String id) {
		super(id);
		MultiplierChoice m = lookup(id);
		setName(m.getName());
		setMultiplier(m.getMultiplier());
	}

	public MultiplierChoice(String id, String n) {
		super(id, n);
	}
	
	public MultiplierChoice(String id, String n, double m) {
		super(id, n);
		multiplier = m;
	}
	
	public MultiplierChoice(MyResultRow r) {
		super(r);
		multiplier = Double.parseDouble(r.get("amount"));
	}
	
	public static MultiplierChoice getMultiplierChoice(String n) {
		return new MultiplierChoice(DatabaseManager.getStoredProcedureFirstRow(GET, n));
	}
	
	public void setMultiplier(double m) {
		multiplier = m;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public MultiplierChoice lookup(String id) {
		return lookup(id, MultiplierChoice.class);
	}
	
	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		MyResultRow r = DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id);
		return type.cast(new MultiplierChoice(r));
	}

	public List<MultiplierChoice> lookupAll() {
		return lookupAll(MultiplierChoice.class);
	}
	
	@Override
	public <T extends DatabaseObject> List<T> lookupAll(Class<T> type) {
		MyResultSet s = DatabaseManager.getStoredProcedureCursor(LOOKUP_ALL);
		return s.toListOf(type);
	}

	@Override
	public List<DropdownChoice> getAll() {
		List<DropdownChoice> multipliers = new ArrayList<DropdownChoice>();
		List<MultiplierChoice> ms = lookupAll();
		for(MultiplierChoice m : ms) {
			multipliers.add((DropdownChoice) m);
		}
		return multipliers;
	}
}
