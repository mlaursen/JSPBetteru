package com.betteru.accounts.objects;

import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Deleteable;

public class TempAccount extends AccountTemplate implements Deleteable {
	{
		Procedure newAccount = new Procedure("newaccount", "id");
		newAccount.setHasCursor(false);
		
		Procedure getId = new Procedure("getid", "username");
		
		manager.addCustomProcedure(newAccount);
		manager.addCustomProcedure(getId);
	}
	@DatabaseField(values = { DatabaseFieldType.NEW })
	private String code;
	public TempAccount() { }
	public TempAccount(String primaryKey) {
		super(primaryKey);
	}
	
	public TempAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		code = SecurityUtil.createCode();
	}
	
	public TempAccount(Integer primaryKey) {
		this(primaryKey.toString());
	}

	public TempAccount(MyResultRow r) {
		super(r);
	}
	
	public boolean createAccount() {
		return manager.executeStoredProcedure("newaccount", primaryKey);
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
	public boolean create() {
		boolean valid;
		if(username == null || password == null || code == null)
			valid = false;
		else {
			String hashed = SecurityUtil.createHash(username, password);
			password = hashed;
			valid = super.create();//manager.executeStoredProcedure("new", username, password, code);
		}
		if(valid) {
			this.primaryKey = manager.getFirstRowFromCursorProcedure("getid", username).construct(TempAccount.class).primaryKey;
		}
		return valid;
	}
	
	@Override
	public String toString() {
		return "TempAccount [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", code=" + code + "]";
	}

	
}
