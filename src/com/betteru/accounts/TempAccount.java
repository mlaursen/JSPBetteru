package com.betteru.accounts;

import java.sql.Date;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.utils.Util;

public class TempAccount extends AccountTemplate implements DatabaseCreateable {

	private static final String LOOKUP = "TEMP_ACCOUNT_GET_BYID(:ID, :CUROSR)";
	private static final String INSERT = "TEMP_ACCOUNT_INSERT(:USERNAME, :PASSWORD, :CODE)";
	private String code;
	private Date created;
	public TempAccount() { }

	/**
	 * Looks up a TempAccount from the database with the id given.
	 * 
	 * @param id
	 */
	public TempAccount(String id) {
		super(id);
		TempAccount ta = lookup(id);
		setUsername(ta.getUsername());
		setPassword(ta.getPassword());
		setCode(ta.getCode());
	}
	
	/**
	 * Creates a temp account with a username, hashed version of the password, and a 
	 * new randomly generated code.
	 * @param u	Username
	 * @param p Password
	 */
	public TempAccount(String u, String p) {
		super(u, p);
		code = Util.createCode();
	}
	
	public TempAccount(MyResultRow r) {
		super(r);
		code = r.get("code");
		created = Util.stringToDate(r.get("created"));
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String c) {
		code = c;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date c) {
		created = c;
	}
	
	public TempAccount lookup(String id) {
		return lookup(id, TempAccount.class);
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new TempAccount(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	@Override
	public boolean create() {
		if(getUsername() == null || getPassword() == null || code == null)
			return false;
		else {
			String hashed = Util.createHash(getUsername(), getPassword());
			setPassword(hashed);
			return DatabaseManager.executeUpdateProcedure(INSERT, getUsername(), hashed, code);
		}
	}
	
	public String toString() {
		String s = super.toString();
		return s + ", code: " + code;
	}

}
