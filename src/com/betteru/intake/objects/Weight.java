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
import com.github.mlaursen.database.procedures.Getable;
import com.github.mlaursen.database.procedures.Updateable;


/**
 * @author mikkel.laursen
 *
 */
public class Weight extends DatabaseObject implements Filterable, Getable, Updateable, Createable {

	private static final long serialVersionUID = 5085280940250171356L;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE, DatabaseFieldType.FILTER})
	protected String accountId;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected Date weightDate;
	@DatabaseField(values={DatabaseFieldType.NEW, DatabaseFieldType.UPDATE})
	protected double weight;
	public Weight() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param accountId
	 * @param weightDate
	 * @param weight
	 */
	public Weight(String accountId, Date weightDate, double weight) {
		this.accountId = accountId;
		this.weightDate = weightDate;
		this.weight = weight;		
	}
	
	/**
	 * @param r
	 */
	public Weight(MyResultRow r) {
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
