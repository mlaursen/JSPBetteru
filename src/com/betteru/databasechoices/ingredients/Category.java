/**
 * 
 */
package com.betteru.databasechoices.ingredients;

import com.github.mlaursen.bootstrap.utils.Util;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Category extends SidebarTemplate {

	/**
	 * 
	 */
	public Category() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Category(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param id
	 */
	public Category(String name, int id) {
		super(name, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public Category(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return Util.capitalizeFirst(primaryKey);
	}
}
