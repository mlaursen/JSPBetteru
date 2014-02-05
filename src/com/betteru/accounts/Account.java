package com.betteru.accounts;

import java.sql.Date;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.utils.Util;

public class Account extends AccountTemplate {

	private static final String LOOKUP = "ACCOUNT_GET_BYID(:ID, :CURSOR)";
	//private static final String LOOKUP_BYUSERNAME = "ACCOUNT_GET_BYUSER(:USER, :CURSOR)";
	private static final String CREATE = "ACCOUNT_INSERT(:USER, :PASS)";
	private static final String GET_IDPASSWORD = "ACCOUNT_GETIDPASS_BYUSER(:USER, :CURSOR)";
	private static final String UPDATE_LAST_LOGIN = "ACCOUNT_UPDATE_LASTLOGIN(:ID)";
	private static final String UPDATE = "ACCOUNT_UPDATE_BYID(:ID, :GENDER, :UNIT, :BIRTHDAY)";
	private GenderChoice gender;
	private UnitChoice unit;
	private Date birthday, lastLogin, activeSince;
	public Account() { }
	public Account(String id) {
		super(id);
		Account a = lookup(id);
		setUsername(a.getUsername());
		setPassword(a.getPassword());
		gender = a.getGender();
		unit = a.getUnit();
		birthday = a.getBirthday();
		lastLogin = a.getLastLogin();
		activeSince = a.getActiveSince();
	}

	public Account(String id, String u, String p) {
		super(id, u, p);
	}
	
	public Account(TempAccount ta) {
		super(ta.getUsername(), ta.getPassword());
	}
	
	public Account(String u, String p) {
		super(u, p);
	}

	public Account(MyResultRow r) {
		super(r);
		gender = new GenderChoice().lookup(r.get(Fields.GENDERID.toString()));
		unit = new UnitChoice().lookup(r.get(Fields.UNITID.toString()));
		birthday = Util.stringToDate(r.get(Fields.BIRTHDAY.toString()));
		lastLogin = Util.stringToDate(r.get(Fields.LAST_LOGIN.toString()));
		activeSince = Util.stringToDate(r.get(Fields.ACTIVE_SINCE.toString()));
	}
	
	public Account(AccountView av) {
		super(av.getId());
		setUsername(av.getUsername());
		setGenderByName(av.getGender());
		setUnitByName(av.getUnit());
		setBirthday(av.getBirthday());
	}
	
	/**
	 * Lookup an account from the database by id
	 * @param id
	 * @return
	 */
	public Account lookup(String id) {
		return lookup(id, Account.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new Account(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	/**
	 * Checks if a user is valid.
	 * Also sets the id to the correct id if valid
	 * @return
	 */
	public boolean isValidUser() {
		MyResultRow row = DatabaseManager.getStoredProcedureFirstRow(GET_IDPASSWORD, getUsername());
		boolean valid = false;
		if(row != null) {
			String pswd = row.get(Fields.PASSWORD.toString());
			String salt = pswd.substring(0, 64);
			String hash = Util.repeatedHashing(salt, getPassword());
			valid = hash.equals(pswd);
			if(valid)
				setId(row.get(Fields.ID.toString()));
		}
		return valid;
	}
	
	public boolean updateLastLogin() {
		return DatabaseManager.executeUpdateProcedure(UPDATE_LAST_LOGIN, getId());
	}
	
	public GenderChoice getGender() {
		return gender;
	}
	
	public String getGenderId() {
		return gender.getId();
	}
	
	public void setGender(String id) {
		setGender(new GenderChoice(id));
	}
	
	public void setGenderByName(String g) {
		setGender(GenderChoice.getGenderChoice(g));
	}
	
	public void setGender(GenderChoice c) {
		gender = c;
	}
	
	public UnitChoice getUnit() {
		return unit;
	}
	
	public String getUnitId() {
		return unit.getId();
	}
	
	public void setUnit(String id) {
		setUnit(new UnitChoice(id));
	}
	
	public void setUnitByName(String u) {
		setUnit(UnitChoice.getUnitChoice(u));
	}
	
	public void setUnit(UnitChoice u) {
		unit = u;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public String getBirthdayAsString() {
		return Util.sqlDateToString(birthday);
	}
	public void setBirthday(String b) {
		setBirthday(Util.stringToDate(b));
	}
	
	public void setBirthday(Date b) {
		birthday = b;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date ll) {
		lastLogin = ll;
	}
	
	public Date getActiveSince() {
		return activeSince;
	}
	
	public void setActiveSince(Date as) {
		activeSince = as;
	}
	
	public boolean update() {
		return DatabaseManager.executeUpdateProcedure(UPDATE, getId(), getGenderId(), getUnitId(), getBirthday());
	}
	
	public static boolean createFromTemp(TempAccount ta) {
		return DatabaseManager.executeUpdateProcedure(CREATE, ta.getUsername(), ta.getPassword());
	}
	
	public String toString() {
		String s = super.toString();
		s += ", Gender: " + (gender == null ? "" : gender.getValue());
		s += ", Unit: " + (unit == null ? "" : unit.getValue());
		s += ", Birthday: " + (birthday == null ? "" : Util.sqlDateToString(birthday));
		s += ", Active Since: " + (activeSince == null ? "" : Util.sqlDateToString(activeSince));
		s += ", last login: " + (lastLogin == null ? "" : Util.sqlDateToString(lastLogin));
		return s;
	}
	
	public enum Fields {
		ID, USERNAME, PASSWORD, GENDERID, UNITID, BIRTHDAY, LAST_LOGIN, ACTIVE_SINCE;
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
