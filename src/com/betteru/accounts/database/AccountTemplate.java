/**
 * 
 */
package com.betteru.accounts.database;

import static com.github.mlaursen.annotations.DatabaseFieldType.CREATE;
import static com.github.mlaursen.annotations.DatabaseFieldType.GET;
import static com.github.mlaursen.annotations.DatabaseFieldType.UPDATE;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Createable;
import com.github.mlaursen.database.objecttypes.Getable;

/**
 * @author mikkel.laursen
 *
 */
public abstract class AccountTemplate extends DatabaseObject implements Getable, Createable {
	@DatabaseField(values={CREATE})
	protected String username;
	
	@DatabaseField(values={CREATE})
	protected String password;
	protected AccountTemplate() { }

	/**
	 * @param primaryKey
	 */
	public AccountTemplate(String primaryKey) {
		super(primaryKey);
	}
	
	public AccountTemplate(Integer primaryKey) {
		super(primaryKey);
	}

	/**
	 * @param r
	 */
	public AccountTemplate(MyResultRow r) {
		super(r);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setUsername(MyResultRow r) {
		username = r.get("username");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPassword(MyResultRow r) {
		password = r.get("password");
	}
	
}
