package com.betteru.database;

import java.lang.reflect.InvocationTargetException;


public abstract class DatabaseObject {

	private String primaryKey;
	private String primaryKeyName = "id";
	private Package pkg = new Package(this.getClass(), new Procedure("get", "id"));
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
	
	public void addProcedure(Procedure p) {
		pkg.addProcedure(p);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProcedureString() {
		return call("get");
	}
	
	public Package getPackage() {
		return pkg;
	}
	
	public String call(String procName) {
		return pkg.call(procName);
	}
	
	/**
	 * Looks up a Database object (table) and retrieves the first row. It then
	 * casts that row into a generic Database Object class.
	 * @param primaryKey
	 * @param procName
	 * @param type
	 * @return
	 */
	protected <T extends DatabaseObject> T get(String primaryKey, String procName, Class<T> type) {
		try {
			return DatabaseManager.getStoredProcedureFirstRow(procName, primaryKey).construct(type);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected <T extends DatabaseObject> T get(String primaryKey, Class<T> type) {
		return get(primaryKey, getProcedureString(), type);
	}
	
}
