/**
 * 
 */
package com_old.betteru.accounts;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com_old.betteru.database.DatabaseManager;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Procedure;

/**
 * @author mikkel.laursen
 *
 */
public class Multiplier extends AccountChoice {
	{
		Procedure p = new Procedure("getamount", "name");
		this.addProcedure(p);
	}
	private double amount;
	public Multiplier() { }
	public Multiplier(String primaryKey) {
		super(primaryKey);
		if(primaryKey == null) {
			setPrimaryKey(defaultChoice());
		}
		else {
			Multiplier m = get(primaryKey);
			setAmount(m.getAmount());
			setPrimaryKey(m.getPrimaryKey());
		}
	}

	/**
	 * @param primaryKey
	 */
	public Multiplier(String primaryKey, double a) {
		super(primaryKey);
		amount = a;
	}

	/**
	 * @param r
	 */
	public Multiplier(MyResultRow r) {
		super(r);
		setAmount(r);
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(MyResultRow r) {
		amount = Double.parseDouble(r.get("amount"));
	}

	
	
	public static List<Multiplier> getAll() {
		return new Multiplier().getAll(Multiplier.class);
	}

	
	public static Multiplier get(String primaryKey) {
		return new Multiplier().get(primaryKey, Multiplier.class);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Multiplier [" + super.toString() + ", amount=" + amount + "]";
	}


}
