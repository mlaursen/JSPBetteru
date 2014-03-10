/**
 * 
 */
package com.betteru.intake.objects;

import java.sql.Date;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Filterable;
import com.github.mlaursen.database.procedures.Updateable;


/**
 * @author mlaursen
 *
 */
public abstract class DailyIntakeTemplate extends DatabaseObject implements Filterable, Createable, Updateable {
	
	@DatabaseField(values={DatabaseFieldType.FILTER, DatabaseFieldType.UPDATE, DatabaseFieldType.NEW})
	protected String accountId;
	@DatabaseField(values={DatabaseFieldType.FILTER, DatabaseFieldType.UPDATE, DatabaseFieldType.NEW})
	protected Date intakeDate;
	public DailyIntakeTemplate() { }
	public DailyIntakeTemplate(String accountId, Date intakeDate) {
		this.accountId = accountId;
		this.intakeDate = intakeDate;
	}
	
	public DailyIntakeTemplate(MyResultRow r) {
		super(r);
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
		this.accountId = r.get("account_id");
	}
	
	/**
	 * @return the intakeDate
	 */
	public Date getIntakeDate() {
		return intakeDate;
	}
	
	/**
	 * @param intakeDate the intakeDate to set
	 */
	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}
	
	public void setIntakeDate(MyResultRow r) {
		this.intakeDate = r.getDate("intake_date");
	}
	
}
