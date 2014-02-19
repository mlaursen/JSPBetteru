package com_old.betteru.accounts;

import com.betteru.utils.Util;
import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;

public class TempAccount extends AccountTemplate implements DatabaseCreateable {
	{
		Procedure pNew = getCreateProcedure();
		pNew.addParams("code");
	}
	private String code;
	public TempAccount() { }

	/**
	 * Looks up a TempAccount from the database with the id given.
	 * 
	 * @param id
	 */
	public TempAccount(String id) {
		super(id);
		TempAccount ta = get(id);
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
		setCode(r);//code = r.get("code");
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(MyResultRow r) {
		code = r.get("code");
	}
	
	public void setCode(String c) {
		code = c;
	}
	
	public TempAccount get(String id) {
		return get(id, TempAccount.class);
	}
	
	@Override
	public boolean create() {
		if(getUsername() == null || getPassword() == null || code == null)
			return false;
		else {
			String hashed = Util.createHash(getUsername(), getPassword());
			setPassword(hashed);
			return DatabaseManager.executeStoredProcedure(call("new"), getUsername(), hashed, code);
		}
	}
	
	public String toString() {
		String s = super.toString();
		return s + ", code: " + code;
	}

}
