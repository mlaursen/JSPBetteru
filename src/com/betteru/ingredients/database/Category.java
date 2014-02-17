/**
 * 
 */
package com.betteru.ingredients.database;

import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class Category extends BrandCategoryOutline {
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public Category(String primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public Category(String primaryKey, int id) {
		super(primaryKey, id);
	}

	/**
	 * @param r
	 */
	public Category(MyResultRow r) {
		super(r);
	}

}
