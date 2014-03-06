/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;

import com.betteru.databasechoices.accounts.Gender;
import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.UnitSystem;
import com.betteru.databasechoices.accounts.Weekday;
import com.betteru.utils.StringNumberUtil;
import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.utils.DateUtil;

/**
 * @author mikkel.laursen
 *
 */
public class AccountView extends DatabaseView {
	/*{
		this.setGetProcedureName("getfromview");
	}*/
	private Account account;
	private AccountSetting accountSetting;
	private int age;
	public AccountView() { }
	
	public AccountView(MyResultRow r) {
		super(r);
	}
	/**
	 * @param a the a to set
	 */
	public void setAccount(Account a) {
		this.account = a;
	}
	
	public void setAccount(MyResultRow r) {
		this.account = new Account();
		this.account.setPrimaryKey(r);
		this.account.setUsername(r);
		this.account.setBirthday(r);
		this.account.setGender(r);
		this.account.setUnitSystem(r);
		this.account.setActiveSince(r);
	}
	
	/**
	 * @param as the as to set
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
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	public void setAge(MyResultRow r) {
		this.age = r.getInt("age");//StringNumberUtil.attemptParseInteger(r, "age");
	}
	
	public Account getAccount() { return this.account; }
	public AccountSetting getAccountSetting() { return this.accountSetting; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountView [primaryKey=" + primaryKey + ", username=" + account.getUsername() + ", birthday=" 
				+ account.getBirthday() + ", gender=" + account.getGender() + ", unitsystem=" + account.getUnitSystem()
				+ ", height=" + accountSetting.getHeight() + ", multiplier=" + accountSetting.getMultiplier()
				+ ", weekday=" + accountSetting.getWeekday() + ", activeSince=" + account.getActiveSince() + "]";
	}
	
	
}
