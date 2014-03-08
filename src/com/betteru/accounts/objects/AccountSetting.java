/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;

import com.betteru.databasechoices.accounts.Multiplier;
import com.betteru.databasechoices.accounts.Weekday;
import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Getable;

/**
 * @author mikkel.laursen
 *
 */
public class AccountSetting extends DatabaseObject implements Getable, Createable {

	@DatabaseField(values={DatabaseFieldType.NEW})
	private String accountId;

	@DatabaseField(values={DatabaseFieldType.NEW})
	private Weekday weekday;
	@DatabaseField(values={DatabaseFieldType.NEW})
	private Multiplier multiplier;
	@DatabaseField(values={DatabaseFieldType.NEW})
	private double height;
	private Date dateChanged;
	public AccountSetting() {}
	public AccountSetting(String primaryKey) {
		super(primaryKey);
	}
	
	public AccountSetting(MyResultRow r) {
		super(r);
	}
	
	public AccountSetting(AccountView av) {
		super();
		this.accountId = av.getPrimaryKey();
		this.weekday = av.getWeekday();
		this.multiplier = av.getMultiplier();
		this.height = av.getHeight();
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * Set the account id from a myresultrow.
	 * It first attempts to set it to the column ACCOUNTID
	 * If account id doesn't exist (The MyResultRow is a AccountView result row),
	 * it sets the account id to the ID column
	 * @param r
	 */
	public void setAccountId(MyResultRow r) {
		String id = r.get("accountid");
		this.accountId = id == null ? r.get("id") : id;
	}
	/**
	 * @return the multiplier
	 */
	public Multiplier getMultiplier() {
		return multiplier;
	}
	/**
	 * @param multiplier the multiplier to set
	 */
	public void setMultiplier(Multiplier multiplier) {
		this.multiplier = multiplier;
	}
	
	public void setMultiplier(MyResultRow r) {
		this.multiplier = new Multiplier(r.get("multiplier"));
	}
	/**
	 * @return the weekday
	 */
	public Weekday getWeekday() {
		return weekday;
	}
	/**
	 * @param weekday the weekday to set
	 */
	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}
	
	public void setWeekday(MyResultRow r) {
		this.weekday = new Weekday(r.get("weekday"));
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setHeight(MyResultRow r) {
		this.height = r.getDouble("height");//StringNumberUtil.attemptParseDouble(r, "height");
	}
	/**
	 * @return the dateChanged
	 */
	public Date getDateChanged() {
		return dateChanged;
	}
	/**
	 * @param dateChanged the dateChanged to set
	 */
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	public void setDateChanged(MyResultRow r) {
		this.dateChanged = r.getDate("date_changed");//DateUtil.stringToDate(r.get("date_changed"));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountSetting [primaryKey=" + primaryKey + ", accountId=" + accountId + ", multiplier=" + multiplier + ", weekday="
				+ weekday + ", height=" + height + ", dateChanged=" + dateChanged + "]";
	}
	
	
	
}
