/**
 * 
 */
package com.betteru.databasechoices.ingredients;

import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Deleteable;

/**
 * @author mikkel.laursen
 *
 */
public class Brand extends SidebarTemplate implements Createable, Deleteable {
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
}
