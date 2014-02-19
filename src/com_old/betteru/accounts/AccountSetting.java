/**
 * 
 */
package com_old.betteru.accounts;

import java.sql.Date;

import com.betteru.utils.Util;
import com_old.betteru.database.DatabaseCreateable;
import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.DatabaseObject;
import com_old.betteru.database.DatabaseUpdateable;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;

/**
 * @author mikkel.laursen
 *
 */
public class AccountSetting extends DatabaseObject implements DatabaseCreateable, DatabaseUpdateable {
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
	public AccountSetting(String accountId) {
		super();
		AccountSetting as = get(accountId);
		setAccountId(as.getAccountId());
		setMultiplier(as.getMultiplier());
		setWeekday(as.getWeekday());
		setHeight(as.getHeight());
		setDateChanged(as.getDateChanged());
	}
	
	public AccountSetting(MyResultRow r) {
		super(r);
		setAccountId(r);
		setMultiplier(r);
		setWeekday(r);
		setHeight(r);
		setDateChanged(r);
	}

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseCreateable#create()
	 */
	@Override
	public boolean create() {
		return DatabaseManager.executeStoredProcedure(call("new"), getAccountId(), getWeekday(), getMultiplier(), getHeight());
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

	/* (non-Javadoc)
	 * @see com_old.betteru.database.DatabaseUpdateable#update()
	 */
	@Override
	public boolean update() {
		return create();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountSetting [accountId=" + accountId + ", multiplier=" + multiplier + ", weekday=" + weekday + ", height=" + height
				+ ", dateChanged=" + dateChanged + "]";
	}
	
	public AccountSetting get(String accountId) {
		return get(accountId, AccountSetting.class);
	}
	
	@Override
	protected <T extends DatabaseObject> T get(String accountId, Class<T> type) {
		return type.cast(new AccountSetting(DatabaseManager.getStoredProcedureFirstRow(call("get"), accountId)));
	}
}
