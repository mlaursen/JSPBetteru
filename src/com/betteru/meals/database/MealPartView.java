/**
 * 
 */
package com.betteru.meals.database;

import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class MealPartView extends DatabaseView {

	
	public MealPartView() { }
	public MealPartView(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	public MealPartView(Integer primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public MealPartView(MyResultRow r) {
		super(r);
	}

}
