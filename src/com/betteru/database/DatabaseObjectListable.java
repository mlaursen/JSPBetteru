package com.betteru.database;

import java.util.List;

public abstract class DatabaseObjectListable extends DatabaseObject {
	{ Procedure p = new Procedure("get");
	  p.setDisplayName("getall");
	  this.addProcedure(p);
	}
	public DatabaseObjectListable() {
		super();
	}
	
	public DatabaseObjectListable(String primaryKey) {
		super(primaryKey);
	}
	
	public DatabaseObjectListable(MyResultRow r) {
		super(r);
	}
	
	public String getAllProcedureString() {
		return call("getall");
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	protected <T extends DatabaseObject> List<T> getAll(Class<T> type) {
		return getAll(getAllProcedureString(), type);
	}
	
	/**
	 * 
	 * @param procName
	 * @param type
	 * @return
	 */
	protected <T extends DatabaseObject> List<T> getAll(String procName, Class<T> type) {
		return DatabaseManager.getStoredProcedureCursor(procName).toListOf(type);
	}
	
}
