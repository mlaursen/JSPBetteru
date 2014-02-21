package com.betteru.accounts.database;

import com.betteru.utils.Util;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Deleteable;

public class TempAccount extends AccountTemplate implements Deleteable {
	{
		Procedure newAccount = new Procedure("newaccount", "id");
		newAccount.setHasCursor(false);
		
		manager.addCustomProcedure(newAccount);
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
		code = Util.createCode();
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
		if(username == null || password == null || code == null)
			return false;
		else {
			String hashed = Util.createHash(username, password);
			password = hashed;
			return super.create();//manager.executeStoredProcedure("new", username, password, code);
		}
	}
	
	@Override
	public String toString() {
		return "TempAccount [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", code=" + code + "]";
	}

	
}
