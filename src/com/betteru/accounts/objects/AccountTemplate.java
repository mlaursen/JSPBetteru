/**
 * 
 */
package com.betteru.accounts.objects;

import static com.github.mlaursen.annotations.DatabaseFieldType.NEW;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Getable;

/**
 * @author mikkel.laursen
 *
 */
public abstract class AccountTemplate extends DatabaseObject implements Getable, Createable {
	@DatabaseField(values={NEW})
	protected String username;
	
	@DatabaseField(values={NEW})
	protected String password;
	protected AccountTemplate() { }
	
	public AccountTemplate(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
