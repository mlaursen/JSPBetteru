/**
 * 
 */
package com.betteru.meals.database;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.DatabaseUpdateable;
import com.betteru.database.MyClob;
import com.betteru.database.MyResultRow;
import com.betteru.database.Procedure;

/**
 * @author mikkel.laursen
 *
 */
public class Meal extends DatabaseObjectListable implements DatabaseCreateable, DatabaseUpdateable {
	{
		Procedure pNew = new Procedure("new", "name", "description");
		Procedure pUpdate = new Procedure("update", "id", "name", "description");
		addProcedure(pNew);
		addProcedure(pUpdate);
	}
	private String name;
	private MyClob description;
	public Meal() {	}

	/**
	 * @param primaryKey
	 */
	public Meal(String primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param r
	 */
	public Meal(MyResultRow r) {
		super(r);
		setName(r);
		setDescription(r);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseUpdateable#update()
	 */
	@Override
	public boolean update() {
		return DatabaseManager.executeStoredProcedure(call("update"), getPrimaryKey(), getName(), getDescription());
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(call("new"), getName(), getDescription());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setName(MyResultRow r) {
		name = r.get("name");
	}

	/**
	 * @return the description
	 */
	public MyClob getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(MyClob description) {
		this.description = description;
	}
	
	public void setDescription(String desc) {
		description = new MyClob(desc);
	}
	
	public void setDescription(MyResultRow r) {
		description = new MyClob(r.get("description"));
	}
}
