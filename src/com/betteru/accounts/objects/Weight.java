/**
 * 
 */
package com.betteru.accounts.objects;

import java.sql.Date;

import com.github.mlaursen.annotations.DatabaseField;
import com.github.mlaursen.annotations.DatabaseFieldType;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.Createable;
import com.github.mlaursen.database.procedures.Filterable;
import com.github.mlaursen.database.procedures.Getable;
import com.github.mlaursen.database.utils.DateUtil;


/**
 * @author mikkel.laursen
 *
 */
public class Weight extends DatabaseObject implements Getable, Filterable, Createable {
	
	private static final long serialVersionUID = -9117246937927484713L;

	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.FILTER})
	private String accountId;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.FILTER})
	private Date weightDate;
	
	@DatabaseField(values={DatabaseFieldType.NEW})
	private double weight;
	public Weight() { }
	
	/**
	 * @param r
	 */
	public Weight(MyResultRow r) {
		super(r);
	}

	public Weight(String userid, double weight) {
		this.accountId = userid;
		this.weight = weight;
		this.weightDate = DateUtil.createSysdate();
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
	 * @return the weightDate
	 */
	public Date getWeightDate() {
		return weightDate;
	}

	
	/**
	 * @param weightDate the weightDate to set
	 */
	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	public void setWeightDate(MyResultRow r) {
		this.weightDate = r.getDate("weight_date");
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setWeight(MyResultRow r) {
		this.weight = r.getDouble("weight");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Weight [primaryKey=" + primaryKey + ", accountId=" + accountId + ", weightDate=" + weightDate + ", weight=" + weight + "]";
	}
	
	
}
