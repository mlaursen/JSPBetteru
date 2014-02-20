package com.betteru.accounts.database;

import com.betteru.utils.Util;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Deleteable;

public class TempAccount extends AccountTemplate implements Deleteable {

	private String code;
	public TempAccount() { }
	public TempAccount(String primaryKey) {
		super(primaryKey);
	}
	
	public TempAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		code = Util.createCode();
	}

	public TempAccount(MyResultRow r) {
		super(r);
	}
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 
	 * @param r
	 */
	public void setCode(MyResultRow r) {
		this.code = r.get("code");
	}
	
	@Override
	public String toString() {
		return "TempAccount [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", code=" + code + "]";
	}

	
}
