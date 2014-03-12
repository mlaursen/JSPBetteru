/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import com.betteru.databasechoices.accounts.Formula;
import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.annotations.DatabaseViewClass;
import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.objects.Procedure;
import com.github.mlaursen.database.utils.DateUtil;

/**
 * @author mikkel.laursen
 * 
 */
@DatabaseViewClass(Account.class)
public class AccountView extends DatabaseView {
	
	private static final long serialVersionUID = 1049140216859207089L;
	public static String GET_FROM_VIEW = "getfromview";
	private Account account;
	private AccountSetting accountSetting;
	private int age;
	
	public AccountView() {}
	
	public AccountView(MyResultRow r) {
		super(r);
	}
	
	/**
	 * @param a
	 *            the a to set
	 */
	public void setAccount(Account a) {
		this.account = a;
	}
	
	public void setAccount(MyResultRow r) {
		this.account = new Account();
		this.account.setUsername(r);
		this.account.setBirthday(r);
		this.account.setGender(r);
		this.account.setUnitSystem(r);
		this.account.setActiveSince(r);
	}
	
	/**
	 * @param as
	 *            the as to set
	 */
	public void setAccountSetting(AccountSetting as) {
		this.accountSetting = as;
	}
	
	public void setAccountSetting(MyResultRow r) {
		this.accountSetting = new AccountSetting();
		this.accountSetting.setAccountId(r);
		this.accountSetting.setHeight(r);
		this.accountSetting.setMultiplier(r);
		this.accountSetting.setWeekday(r);
		this.accountSetting.setFormula(r);
	}
	
	public Date getBirthday() {
		return account.getBirthday();
	}
	
	public double getHeight() {
		return accountSetting.getHeight();
	}
	
	public Gender getGender() {
		return account.getGender();
	}
	
	public UnitSystem getUnitSystem() {
		return account.getUnitSystem();
	}
	
	public Multiplier getMultiplier() {
		return accountSetting.getMultiplier();
	}
	
	public Weekday getWeekday() {
		return accountSetting.getWeekday();
	}
	
	public void setBirthday(String b) {
		account.setBirthday(DateUtil.stringToDate(b));
	}
	
	public void setUnitSystem(String u) {
		account.setUnitSystem(new UnitSystem(u));
	}
	
	public void setHeight(String h) {
		accountSetting.setHeight(StringNumberUtil.attemptParseDouble(h));
	}
	
	public void setGender(String g) {
		account.setGender(new Gender(g));
	}
	
	public void setMultiplier(String m) {
		accountSetting.setMultiplier(new Multiplier(m));
	}
	
	public void setWeekday(String w) {
		accountSetting.setWeekday(new Weekday(w));
	}
	
	public void setFormula(Formula f) {
		accountSetting.setFormula(f);
	}
	
	public Formula getFormula() {
		return accountSetting.getFormula();
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	public void setAge(MyResultRow r) {
		this.age = r.getInt("age");
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public AccountSetting getAccountSetting() {
		return this.accountSetting;
	}
	
	@Override
	public List<Procedure> getCustomProcedures() {
		Procedure p = new Procedure(GET_FROM_VIEW, "id");
		return Arrays.asList(p);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountView [primaryKey=" + primaryKey + ", age=" + age + ", getBirthday()=" + getBirthday() + ", getHeight()="
				+ getHeight() + ", getGender()=" + getGender() + ", getUnitSystem()=" + getUnitSystem() + ", getMultiplier()="
				+ getMultiplier() + ", getWeekday()=" + getWeekday() + ", getFormula()=" + getFormula() + "]";
	}
	
}
