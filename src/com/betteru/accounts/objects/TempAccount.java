package com.betteru.accounts.objects;

import java.util.Arrays;
import java.util.List;

import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.Deleteable;
import com.github.mlaursen.database.objecttypes.Getable;

public class TempAccount extends AccountTemplate implements Getable, Createable, Deleteable {
	public static final String NEW_ACCOUNT = "newaccount";
	@DatabaseField(values = { DatabaseFieldType.NEW })
	private String code;
	
	public TempAccount() {}
	
	public TempAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		code = SecurityUtil.createCode();
	}
	
	public TempAccount(MyResultRow r) {
		super(r);
	}
	
	public void hashPassword() {
		this.password = SecurityUtil.createHash(this.username, this.password);
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code
	 *            the code to set
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
	public List<Procedure> getCustomProcedures() {
		Procedure newAccount = new Procedure(NEW_ACCOUNT, "username");
		newAccount.setHasCursor(false);
		return Arrays.asList(newAccount);
	}
	
	/*
	 * public boolean create() { ObjectManager m = new ObjectManager(TempAccount.class); boolean valid; if(username == null || password ==
	 * null || code == null) valid = false; else { String hashed = SecurityUtil.createHash(username, password); password = hashed; valid =
	 * m.create(this);//manager.executeStoredProcedure("new", username, password, code); } if(valid) { this.primaryKey =
	 * m.executeCustomGetProcedure("getid", TempAccount.class, username).primaryKey; //manager.getFirstRowFromCursorProcedure("getid",
	 * username).construct(TempAccount.class).primaryKey; } return valid; }
	 */
	
	@Override
	public String toString() {
		return "TempAccount [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", code=" + code + "]";
	}
	
}
