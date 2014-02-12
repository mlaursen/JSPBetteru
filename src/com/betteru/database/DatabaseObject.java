package com.betteru.database;


public abstract class DatabaseObject {

	private String primaryKey;
	private String primaryKeyName = "id";
	public DatabaseObject() {
		primaryKey = null;
	}
	
	public DatabaseObject(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public DatabaseObject(MyResultRow r) {
		this.primaryKey = r.get(getPrimaryKeyName());
	}
	
	public void setPrimaryKey(String id) {
		this.primaryKey = id;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKeyName(String name) {
		primaryKeyName = name;
	}
	
	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
	
	protected abstract <T extends DatabaseObject> T lookup(String primaryKey, Class<T> type);
	
}
