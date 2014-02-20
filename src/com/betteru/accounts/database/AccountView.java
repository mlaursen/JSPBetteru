/**
 * 
 */
package com.betteru.accounts.database;

import com.github.mlaursen.database.objects.DatabaseView;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class AccountView extends DatabaseView {
	{
		this.setGetProcedureName("getfromview");
	}
	private Account account;
	private AccountSetting accountSetting;
	public AccountView() { }
	public AccountView(String primaryKey) {
		super();
		MyResultRow r = manager.getFirstRowFromCursorProcedure("get", primaryKey);
		setAll(r);
	}
	public AccountView(Integer primaryKey) {
		this(primaryKey.toString());
	}
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
		this.accountSetting.setHeight(r);
		this.accountSetting.setMultiplier(r);
		this.accountSetting.setWeekday(r);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountView [primaryKey=" + primaryKey + ", account=" + account + ", accountSetting=" + accountSetting + "]";
	}
	
	
}
