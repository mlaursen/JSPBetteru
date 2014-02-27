/**
 * 
 */
package com.betteru.meals.objects;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyClob;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.Getable;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class Meal extends DatabaseObject implements Getable, Updateable, Createable {

	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String name;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected MyClob description;
	public Meal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Meal(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Meal(Integer primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Meal(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
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
		this.name = r.get("name");
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

	public void setDescription(MyResultRow r) {
		this.description = new MyClob(r.get("description"));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Meal [" + (primaryKey != null ? "primaryKey=" + primaryKey + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}
	
	
}