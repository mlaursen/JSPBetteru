/**
 * 
 */
package com.betteru.accounts.database;

import java.sql.Date;

import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objecttypes.Updateable;
import com.betteru.accounts.database.UnitSystem;
import com.betteru.utils.Util;

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
		unitSystem = new UnitSystem(r);
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setGender(MyResultRow r) {
		gender = new Gender(r);
	}
	
	
	/**   	toString **/

	@Override
	public String toString() {
		return "Account [primaryKey=" + primaryKey + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ ", unitSystem=" + unitSystem + ", gender=" + gender + "]";
	}

}
