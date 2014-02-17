/**
 * 
 */
package com.betteru.accounts;

import java.sql.Date;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseUpdateable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Procedure;
import com.betteru.utils.Util;

/**
 * @author mikkel.laursen
 *
 */
public class Account extends AccountTemplate implements DatabaseUpdateable {
	{
		Procedure p = this.getCreateProcedure();
		p.setName(CREATE);
		p.setDisplayName(CREATE);
		p.setParams(new String[] { "tempid" });
		
		Procedure updateLogin = new Procedure(UPDATE_LAST_LOGIN, "id");
		updateLogin.setHasCursor(false);
		Procedure update = new Procedure(UPDATE, "id", "gender", "unitsystem", "birthday");
		update.setHasCursor(false);
		addProcedure(updateLogin);
		addProcedure(update);
	}
	
	public static final String UPDATE = "updateaccount", UPDATE_LAST_LOGIN = "updatelastlogin", CREATE = "createfromtemp";
	private Date birthday;
	private UnitSystem unitSystem;
	private Gender gender;
	public Account() {	}

	/**
	 * @param id
	 */
	public Account(String id) {
		super(id);
		Account a = get(id);
		setUsername(a.getUsername());
		setPassword(a.getPassword());
		setBirthday(a.getBirthday());
		setUnitSystem(a.getUnitSystem());
		setGender(a.getGender());
	}
	
	public Account(String user, String pass) {
		super(user, pass);
	}

	/**
	 * @param id
	 * @param u
	 * @param p
	 */
	public Account(String id, String u, String p) {
		super(id, u, p);
	}
	
	
	public Account(TempAccount ta) {
		super(ta.getUsername(), ta.getPassword());
	}

	/**
	 * @param r
	 */
	public Account(MyResultRow r) {
		super(r);
		setBirthday(r);
		setUnitSystem(r);
		setGender(r);
	}
	
	/**
	 * Checks if a user is valid.
	 * Also sets the id to the correct id if valid
	 * @return
	 */
	public boolean isValidUser() {
		MyResultRow row = DatabaseManager.getStoredProcedureFirstRow(call("get"), getUsername());
		boolean valid = false;
		if(row != null) {
			String pswd = row.get("password");
			String salt = pswd.substring(0, 64);
			String hash = Util.repeatedHashing(salt, getPassword());
			valid = hash.equals(pswd);
			if(valid)
				setPrimaryKey(row.get(getPrimaryKeyName()));
		}
		return valid;
	}
	
	/**
	 * Calls a stored procedure to update the last login of the current account
	 * @return boolean if the update encountered any errors. True = success, false = error
	 */
	public boolean updateLastLogin() {
		return DatabaseManager.executeStoredProcedure(call(UPDATE_LAST_LOGIN), getPrimaryKey());
	}
	
	@Override
	public boolean update() {
		return DatabaseManager.executeStoredProcedure(call(UPDATE), getPrimaryKey(), getGender(), getUnitSystem(), getBirthday());
	}

	/* (non-Javadoc)
	 * Do Not use this. It does not store a hashed password
	 * @see com.betteru.accounts.Account#createFromTemp(TempAccount ta)
	 */
	@Deprecated
	@Override
	public boolean create() {
		return false;
	}
	
	public static boolean createFromTemp(TempAccount ta) {
		return DatabaseManager.executeStoredProcedure(new Account().call(CREATE), ta.getPrimaryKey());
	}
	
	public Account get(String id) {
		return get(id, Account.class);
	}

	

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(MyResultRow r) {
		String bday = r.get("birthday");
		birthday = bday == null ? null : Util.stringToDate(bday);
	}

	/**
	 * @return the unit
	 */
	public UnitSystem getUnitSystem() {
		return unitSystem;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnitSystem(UnitSystem unit) {
		this.unitSystem = unit;
	}
	
	public void setUnitSystem(MyResultRow r) {
		unitSystem = new UnitSystem(r.get("unit"));
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(MyResultRow r) {
		gender = new Gender(r.get("gender"));
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [birthday=" + birthday + ", unitSystem=" + unitSystem + ", gender=" + gender + ", Username=" + getUsername()
				+ ", Password=" + getPassword() + ", Primary Key=" + getPrimaryKey() + "]";
	}

	
}
