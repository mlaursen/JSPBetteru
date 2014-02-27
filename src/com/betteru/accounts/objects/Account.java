/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.utils.DateUtil;
import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class Account extends AccountTemplate implements Updateable {
	private static final String UPDATE_LAST_LOGIN = "updatelastlogin";
	{
		Procedure updateLogin = new Procedure( UPDATE_LAST_LOGIN, "id");
		updateLogin.setHasCursor(false);
		
		manager.addCustomProcedure(updateLogin);
	}
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Gender gender;
	
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private UnitSystem unitSystem;

	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Date birthday;

	private Date activeSince, lastLogin;
	public Account() { }

	/**
	 * @param primaryKey
	 */
	public Account(String primaryKey) {
		super(primaryKey);
	}
	
	public Account(Integer primaryKey) {
		super(primaryKey);
	}
	
	public Account(String username, String password) {
		super(username, password);
	}
	
	public Account(MyResultRow r) {
		super(r);
		setBirthday(r);
		setUnitSystem(r);
		setGender(r);
	}
	
	public boolean isValidUser() {
		MyResultRow r = manager.getFirstRowFromCursorProcedure("get", username);
		boolean valid = false;
		if(r != null) {
			String pswd = r.get("password");
			String salt = pswd.substring(0, 64);
			String hash = SecurityUtil.repeatedHashing(salt, getPassword());
			valid = hash.equals(pswd);
			if(valid)
				primaryKey = r.get(primaryKeyName);
		}
		return valid;
	}
	
	public boolean updateLastLogin() {
		return manager.executeStoredProcedure(UPDATE_LAST_LOGIN, primaryKey);
	}
	/**		Getters and setters  	 */


	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(MyResultRow r) {
		birthday = DateUtil.stringToDate(r.get("birthday"));
	}

	public UnitSystem getUnitSystem() {
		return unitSystem;
	}

	public void setUnitSystem(UnitSystem unitSystem) {
		this.unitSystem = unitSystem;
	}
	
	public void setUnitSystem(MyResultRow r) {
		unitSystem = new UnitSystem(r.get("unit"));
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setGender(MyResultRow r) {
		gender = new Gender(r.get("gender"));
	}
	
	
	/**
	 * @return the activeSince
	 */
	public Date getActiveSince() {
		return activeSince;
	}

	/**
	 * @param activeSince the activeSince to set
	 */
	public void setActiveSince(Date activeSince) {
		this.activeSince = activeSince;
	}
	public void setActiveSince(MyResultRow r) {
		this.activeSince = DateUtil.stringToDate(r.get("active_since"));
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public void setLastLogin(MyResultRow r) {
		this.lastLogin = DateUtil.stringToDate(r.get("last_login"));
	}

	/**   	toString **/

	@Override
	public String toString() {
		return "Account [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ ", unitSystem=" + unitSystem + ", gender=" + gender + "]";
	}

}
