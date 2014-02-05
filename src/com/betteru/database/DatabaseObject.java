package com.betteru.database;


public abstract class DatabaseObject {

	private String id;
	public DatabaseObject() {
		id = null;
	}
	
	public DatabaseObject(String id) {
		this.id = id;
	}
	
	public DatabaseObject(MyResultRow r) {
		this.id = r.get("id");
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	protected abstract <T extends DatabaseObject> T lookup(String id, Class<T> type);
}
