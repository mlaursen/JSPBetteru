/**
 * 
 */
package com.betteru.databasechoices.accounts;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.bootstrap.utils.Util;
import com.github.mlaursen.database.objects.DatabaseObject;
import com.github.mlaursen.database.objects.MyResultRow;
import com.github.mlaursen.database.procedures.GetAllable;
import com.github.mlaursen.database.procedures.Getable;
import com.github.mlaursen.database.utils.ClassUtil;

/**
 * @author mikkel.laursen
 *
 */
public abstract class AccountChoice extends DatabaseObject implements Getable, GetAllable, DropdownChoice {
	
	private static final long serialVersionUID = -2950820576979866840L;
	protected int dropdownKey;
	public AccountChoice() {
		super();
		this.primaryKey = this.defaultChoice();
	}
	public AccountChoice(String primaryKey) {
		super(primaryKey, "name");
		this.primaryKey = primaryKey == null ? this.defaultChoice() : primaryKey;
	}
	
	public AccountChoice(String primaryKey, int id) {
		this(primaryKey);
		dropdownKey = id;
	}

	/**
	 * @param r
	 */
	public AccountChoice(MyResultRow r) {
		super("name", r);
	}
	
	@Override
	public String getDropdownValue() {
		return Util.capitalizeFirst(primaryKey, " ");
	}

	
	@Override
	public String getDropdownKey() {
		return dropdownKey + "";
	}

	@Override
	public void setDropdownKey(int k) {
		dropdownKey = k;
	}

	public String defaultChoice() {
		return "Select your " + (ClassUtil.formatClassName(getClass(), null, " ").trim());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountChoice [" + (primaryKey != null ? "primaryKey=" + primaryKey + ", " : "") + "dropdownKey=" + dropdownKey + "]";
	}
}
