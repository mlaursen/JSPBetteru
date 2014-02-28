/**
 * 
 */
package com.betteru.databasechoices.ingredients;

import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.Deleteable;

/**
 * @author mikkel.laursen
 *
 */
public class Brand extends SidebarTemplate implements Createable, Deleteable {
	{
		Procedure pNew = manager.getPackage().getProcedure("new");
		pNew.addParams("name");
		
	}
	
	public Brand() { }
	public Brand(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param id
	 */
	public Brand(String name, int id) {
		super(name, id);
	}

	/**
	 * @param r
	 */
	public Brand(MyResultRow r) {
		super(r);
	}

	@Override
	public boolean create() {
		return manager.executeStoredProcedure("new", primaryKey);
	}
	
	@Override
	public boolean delete() {
		return manager.executeStoredProcedure("delete", primaryKey);
	}
}
