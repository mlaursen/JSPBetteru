package com.betteru.accounts;

import java.lang.reflect.Field;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.utils.Util;

public class AccountView extends DatabaseObject {
	
	private static final String LOOKUP = "ACCOUNT_VIEW_GET_BYID(:ID, :CURSOR)";
	//private static final String UPDATE = "ACCOUNT_VIEW_UPDATE(:ACTID, :USER, :GEND, :UNIT";
	private String username, gender, unit, recalc, multiplier;
	private Date birthday, activeSince;
	private double height, multiplierAmount;
	public AccountView() { }
	public AccountView(String id) {
		super(id);
		AccountView a = lookup(id);
		setUsername(a.getUsername());
		setGender(a.getGender());
		setUnit(a.getUnit());
		setRecalc(a.getRecalc());
		setMultiplier(a.getMultiplier());
		setMultiplierAmount(a.getMultiplierAmount());
		setBirthday(a.getBirthday());
		setActiveSince(a.getActiveSince());
		setHeight(a.getHeight());
		/*
		username = a.getUsername();
		gender = a.getGender();
		unit = a.getUnit();
		recalc = a.getRecalc();
		birthday = a.getBirthday();
		activeSince = a.getActiveSince();
		multiplier = a.getMultiplier();
		height = a.getHeight();
		*/
	}
	
	public AccountView(MyResultRow r) {
		super(r.get(Fields.ID.toString()));
		username = r.get(Fields.USERNAME.toString());
		gender   = r.get(Fields.GENDER.toString());
		unit     = r.get(Fields.UNIT_SYSTEM.toString());
		recalc   = r.get(Fields.RECALC.toString());
		birthday = Util.stringToDate(r.get(Fields.BIRTHDAY.toString()));
		activeSince = Util.stringToDate(r.get(Fields.ACTIVE_SINCE.toString()));
		multiplier = r.get(Fields.MULTIPLIER.toString());
		height     = Double.parseDouble(r.get(Fields.HEIGHT.toString()));
	}
	
	public AccountView(HttpServletRequest request) {
		this((String) request.getSession().getAttribute("userid"));
	}
	
	public AccountView lookup(String id) {
		return lookup(id, AccountView.class);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String u) {
		username = u;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String name) {
		gender = name;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String name) {
		unit = name;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date b) {
		birthday = b;
	}
	
	public Date getActiveSince() {
		return activeSince;
	}
	
	public void setActiveSince(Date a) {
		activeSince = a;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double h) {
		height = h;
	}
	
	public String getMultiplier() {
		return multiplier;
	}
	
	public double getMultiplierAmount() {
		return multiplierAmount;
	}
	
	public void setMultiplier(String m) {
		multiplier = m;
	}
	
	public void setMultiplierAmount(String m) {
		setMultiplierAmount(Double.parseDouble(m));
	}
	
	public void setMultiplierAmount(double m) {
		multiplierAmount = m;
	}
	
	public String getRecalc() {
		return recalc;
	}
	
	public void setRecalc(String name) {
		recalc = name;
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new AccountView(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}

	private enum Fields {
		ID, USERNAME, BIRTHDAY, UNIT_SYSTEM, GENDER, ACTIVE_SINCE, HEIGHT, MULTIPLIER, RECALC;
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	public String toString() {
		String s = "" + getId();
		return s;
	}
}
