/**
 * 
 */
package com.betteru.meals.database;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.Getable;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class MealPart extends DatabaseObject implements Createable, Getable, Updateable {

	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String mealId;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected String ingredientId;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	private double amount;
	
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	private String defaultUnit;
	
	public MealPart() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public MealPart(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public MealPart(Integer primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public MealPart(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

}
