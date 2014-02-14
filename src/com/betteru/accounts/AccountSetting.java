/**
 * 
 */
package com.betteru.accounts;

import java.sql.Date;

import com.betteru.database.DatabaseCreateable;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.database.Procedure;
import com.betteru.utils.Util;

/**
 * @author mikkel.laursen
 *
 */
public class AccountSetting extends DatabaseObject implements DatabaseCreateable {
	{
		Procedure pNew = new Procedure("new", "accountid", "weekday", "multiplier", "height");
		pNew.setHasCursor(false);
		addProcedure(pNew);
	}
	private String accountId;
	private Multiplier multiplier;
	private Weekday weekday;
	private double height;
	private Date dateChanged;
	public AccountSetting() { }

	/**
	 * @param primaryKey
	 */
	public AccountSetting(String primaryKey) {
		super(primaryKey);
	}
	
	public AccountSetting(MyResultRow r) {
		super(r);
		setAccountId(r);
		setMultiplier(r);
		setWeekday(r);
		setHeight(r);
		setDateChanged(r);
	}
	
	public AccountSetting get(String id) {
		return get(id, AccountSetting.class);
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.betteru.database.DatabaseCreateable#createProcedureString()
	 */
	@Override
	public String createProcedureString() {
		// TODO Auto-generated method stub
		return null;
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
	
	public void setAccountId(MyResultRow r) {
		accountId = r.get("accountid");
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
		multiplier = new Multiplier(r.get("multiplier"));
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
		weekday = new Weekday(r.get("weekday"));
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
		try {
			height = Double.parseDouble(r.get("height"));
		}
		catch(NumberFormatException | NullPointerException e) {
			height = 0;
		}
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
		String d = r.get("date_changed");
		dateChanged = d == null ? null : Util.stringToDate(d);
	}
}
