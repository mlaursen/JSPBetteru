/**
 * 
 */
package com.betteru.accounts.database;

import java.sql.Date;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.utils.Util;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Updateable;

/**
 * @author mikkel.laursen
 *
 */
public class Account extends AccountTemplate implements Updateable {

	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Date birthday;
	
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private UnitSystem unitSystem;
	
	@DatabaseField(values=DatabaseFieldType.UPDATE)
	private Gender gender;
	private Date activeSince;
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

	/**
	 * @param r
	 */
	public Account(MyResultRow r) {
		super(r);
		setBirthday(r);
		setUnitSystem(r);
		setGender(r);
	}
	
	
	/**		Getters and setters  	 */


	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(MyResultRow r) {
		birthday = Util.stringToDate(r.get("birthday"));
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
		this.activeSince = Util.stringToDate(r.get("active_since"));
	}

	/**   	toString **/

	@Override
	public String toString() {
		return "Account [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ ", unitSystem=" + unitSystem + ", gender=" + gender + "]";
	}

}
