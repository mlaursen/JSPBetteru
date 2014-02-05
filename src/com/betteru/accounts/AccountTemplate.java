package com.betteru.accounts;

import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;

public abstract class AccountTemplate extends DatabaseObject {

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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public String toString() {
		return "ID: " + getId() + ", Username: " + username + ", Password: " + password;
	}
}
