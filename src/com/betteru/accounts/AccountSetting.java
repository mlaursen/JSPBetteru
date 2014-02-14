package com.betteru.accounts;

import java.sql.Date;

import com.betteru.database.DatabaseManager;
import com.betteru.database.DatabaseObject;
import com.betteru.database.MyResultRow;
import com.betteru.utils.Util;

public class AccountSetting extends DatabaseObject {

	private static final String LOOKUP = "ACCOUNT_SETTING_VIEW_GET_BYID(:ID, :PCURSOR)";
	private static final String UPDATE = "ACCOUNT_SETTING_INSERT(:ACTID, :RECALCID, :MULTID, :HEIGHT)";
	private String accountId;//, recalcId, multiplierId;
	private RecalcChoice recalc;
	private MultiplierChoice multiplier;
	private double height;
	private Date dateChanged;
	public AccountSetting() { }

	public AccountSetting(String id) {
		super(id);
	}

	public AccountSetting(MyResultRow r) {
		super(r.get(Fields.ID.toString()));
		setAccountId(r.get(Fields.ACCOUNTID.toString()));
		setRecalcById(r.get(Fields.RECALCID.toString()));
		setMultiplierById(r.get(Fields.MULTIPLIERID.toString()));
		setHeight(r.get(Fields.HEIGHT.toString()));
		setDateChanged(r.get(Fields.DATE_CHANGED.toString()));
	}
	
	public AccountSetting(AccountView av) {
		super();
		setAccountId(av.getId());
		setRecalcByName(av.getRecalc());
		setMultiplierByName(av.getMultiplier());
		setHeight(av.getHeight());
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public RecalcChoice getRecalc() {
		return recalc;
	}
	
	public String getRecalcId() {
		return recalc.getId();
	}
	
	public MultiplierChoice getMultiplier() {
		return multiplier;
	}
	
	public String getMultiplierId() {
		return multiplier.getId();
	}
	
	public double getHeight() {
		return height;
	}
	
	public Date getDateChanged() {
		return dateChanged;
	}
	
	public void setAccountId(String id) {
		accountId = id;
	}
	
	public void setRecalcByName(String name) {
		setRecalc(RecalcChoice.getRecalcChoice(name));
	}
	
	public void setRecalcById(String id) {
		setRecalc(new RecalcChoice(id));
	}
	
	public void setRecalc(RecalcChoice r) {
		recalc = r;
	}
	
	public void setMultiplierByName(String n) {
		setMultiplier(MultiplierChoice.getMultiplierChoice(n));
	}
	
	public void setMultiplierById(String id) {
		setMultiplier(new MultiplierChoice(id));
	}
	
	public void setMultiplier(MultiplierChoice m) {
		multiplier = m;
	}
	
	public void setHeight(String h) {
		setHeight(Double.parseDouble(h));
	}
	
	public void setHeight(double h) {
		height = h;
	}
	
	public void setDateChanged(String d) {
		setDateChanged(Util.stringToDate(d));
	}
	public void setDateChanged(Date d) {
		dateChanged = d;
	}

	@Override
	protected <T extends DatabaseObject> T lookup(String id, Class<T> type) {
		return type.cast(new AccountSetting(DatabaseManager.getStoredProcedureFirstRow(LOOKUP, id)));
	}
	
	public enum Fields {
		ID, ACCOUNTID, RECALCID, MULTIPLIERID, HEIGHT, DATE_CHANGED;
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	public boolean update() {
		return DatabaseManager.executeStoredProcedure(UPDATE, getAccountId(), getRecalcId(), getMultiplierId(), ""+getHeight());
	}

}
