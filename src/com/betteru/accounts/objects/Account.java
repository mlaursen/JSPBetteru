/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.utils.DateUtil;
import com.betteru.utils.SecurityUtil;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.objecttypes.Deleteable;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class Account extends AccountTemplate implements Updateable, Deleteable {
	private static final String UPDATE_LAST_LOGIN = "updatelastlogin";
	
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Gender gender;
	
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private UnitSystem unitSystem;

	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Date birthday;

	private Date activeSince, lastLogin;
	public Account() { }
	public Account(String username, String password) {
		super(username, password);
	}
	
	public Account(MyResultRow r) {
		super(r);
	}
	
	public boolean isValidUser() {
		Account a = new ObjectManager(Account.class).get(this.username, Account.class);
		boolean valid = false;
		if(a != null && a.getPassword() != null) {
			String pswd = a.getPassword();
			String salt = pswd.substring(0, 64);
			String hash = SecurityUtil.repeatedHashing(salt, this.password);
			valid = hash.equals(pswd);
			if(valid)
				primaryKey = a.getPrimaryKey();
		}
		return valid;
	}
	
	@Override
	public List<Procedure> getCustomProcedures() {
		Procedure lastLogin = new Procedure(UPDATE_LAST_LOGIN, "id");
		lastLogin.setHasCursor(false);
		return Arrays.asList(lastLogin);
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
