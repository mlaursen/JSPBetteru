/**
 * 
 */
package com.betteru.accounts.database;

import java.sql.Date;

import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Updateable;
import com.betteru.accounts.database.UnitSystem;

/**
 * @author mikkel.laursen
 *
 */
public class Account extends AccountTemplate implements Updateable {

	private Date birthday;
	private UnitSystem unitSystem;
	private Gender gender;
	public Account() { }

	/**
	 * @param primaryKey
	 */
	public Account(String primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param r
	 */
	public Account(MyResultRow r) {
		super(r);
	}

}
