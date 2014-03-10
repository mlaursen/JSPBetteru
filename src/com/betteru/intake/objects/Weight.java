/**
 * 
 */
package com.betteru.intake.objects;

import java.sql.Date;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Filterable;
import com.github.mlaursen.database.procedures.Getable;
import com.github.mlaursen.database.procedures.Updateable;


/**
 * @author mikkel.laursen
 *
 */
public class Weight extends DatabaseObject implements Filterable, Getable, Updateable, Createable {

	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE, DatabaseFieldType.FILTER})
	protected String accountId;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected Date weightDate;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected double weight;
	public Weight() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param primaryKey
	 */
	public Weight(String accountId) {
		
		
	}
	
	/**
	 * @param r
	 */
	public Weight(MyResultRow r) {
		super(r);
	}
	
}
