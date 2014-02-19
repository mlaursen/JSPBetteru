package com_old.betteru.accounts;

import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseObject;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;

public abstract class AccountTemplate extends DatabaseObject implements DatabaseCreateable {

	{ 
		Procedure pNew = new Procedure("new", "username", "password");
		pNew.setHasCursor(false);
		addProcedure(pNew);
	}
	private String username, password;
	public AccountTemplate() { }
	public AccountTemplate(String id) {
		super(id);
	}
	
	public AccountTemplate(String id, String u, String p) {
		super(id);
		username = u;
		password =p;
	}
	
	public AccountTemplate(String u, String p) {
		username = u;
		password = p;
	}
		
	public AccountTemplate(MyResultRow r) {
		this(r.get("id"), r.get("username"), r.get("password"));
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String u) {
		username = u;
	}
	
	public void setUsername(MyResultRow r) {
		username = r.get("username");
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public void setPassword(MyResultRow r) {
		password = r.get("password");
	}
	
	public String toString() {
		return "ID: " + getPrimaryKey() + ", Username: " + username + ", Password: " + password;
	}
	
	protected Procedure getCreateProcedure() {
		return super.getPackage().getProcedure("new");
	}
}
