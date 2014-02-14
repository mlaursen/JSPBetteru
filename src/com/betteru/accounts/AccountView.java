/**
 * 
 */
package com.betteru.accounts;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.betteru.database.DatabaseObject;
import com.betteru.database.DatabaseUpdateable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Package;
import com.betteru.database.Procedure;
import com.betteru.utils.Util;

/**
 * @author mikkel.laursen
 *
 */
public class AccountView extends DatabaseObject implements DatabaseUpdateable {
	{
		Package pkg = getPackage();
		pkg.setName("account");
		Procedure get = pkg.getProcedure("get");
		get.setName("getfromview");
	}
	private Account a;
	private AccountSetting as;
	public AccountView() { }

	/**
	 * @param primaryKey
	 */
	public AccountView(String accountId) {
		super(accountId);
		AccountView av = get(accountId);
		setAccount(av.getAccount());
		setAccountSetting(av.getAccountSetting());
	}
	
	public AccountView(HttpServletRequest request) {
		this((String) request.getSession().getAttribute("userid"));
	}


	/**
	 * @param r
	 */
	public AccountView(MyResultRow r) {
		super(r);
		setAccount(r);
		setAccountSetting(r);
	}

	/**
	 * @return the a
	 */
	public Account getAccount() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setAccount(Account a) {
		this.a = a;
	}

	/**
	 * @return the as
	 */
	public AccountSetting getAccountSetting() {
		return as;
	}

	/**
	 * @param as the as to set
	 */
	public void setAccountSetting(AccountSetting as) {
		this.as = as;
	}
	
	/**
	 * Sets an account with only:
	 * 	ID, username, birthday, gender, and unit fields
	 * @param r
	 */
	public void setAccount(MyResultRow r) {
		a = new Account();
		a.setPrimaryKey(r);
		a.setUsername(r);
		a.setGender(r);
		a.setUnitSystem(r);
	}
	
	/**
	 * Sets an account setting with only:
	 * 	accountid, height, multiplier, weekday fields
	 * @param r
	 */
	public void setAccountSetting(MyResultRow r) {
		as = new AccountSetting();
		as.setAccountId(r.get("id"));
		as.setHeight(r);
		as.setMultiplier(r);
		as.setWeekday(r);
	}

	@Override
	public boolean update() {
		return a.update() && as.update();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountView [a=" + a + ", as=" + as + "]";
	}
	
	public AccountView get(String accountId) {
		return get(accountId, AccountView.class);
	}
	
	public void setBirthday(String b) {
		a.setBirthday(Util.stringToDate(b));
	}
	
	public Date getBirthday() {
		return a.getBirthday();
	}
	
	public void setUnitSystem(String u) {
		a.setUnitSystem(new UnitSystem(u));
	}
	
	public UnitSystem getUnitSystem() {
		return a.getUnitSystem();
	}
	
	public void setHeight(String h) {
		as.setHeight(Double.parseDouble(h));
	}
	
	public double getHeight() {
		return as.getHeight();
	}
	
	public void setGender(String g) {
		a.setGender(new Gender(g));
	}
	
	public Gender getGender() {
		return a.getGender();
	}
	
	public void setMultiplier(String m) {
		as.setMultiplier(new Multiplier(m));
	}
	
	public Multiplier getMultiplier() {
		return as.getMultiplier();
	}
	
	public void setWeekday(String w) {
		as.setWeekday(new Weekday(w));
	}
	
	public Weekday getWeekday() {
		return as.getWeekday();
	}
}
