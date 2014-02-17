/**
 * 
 */
package com.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.betteru.accounts.AccountChoice;
import com.betteru.database.DatabaseObjectListable;
import com.betteru.database.MyResultRow;
import com.betteru.database.Util;
import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

/**
 * @author mikkel.laursen
 *
 */
public class FoodUnit extends DatabaseObjectListable implements DropdownChoice {

	private String longName;
	private int id;
	public FoodUnit() {	}

	/**
	 * @param primaryKey
	 */
	public FoodUnit(String primaryKey) {
		super(primaryKey);
	}
	
	public FoodUnit(String primaryKey, int id) {
		this(primaryKey);
		this.id = id;
	}

	/**
	 * @param r
	 */
	public FoodUnit(MyResultRow r) {
		super();
		setPrimaryKey(r.get(getPrimaryKeyName()));
		setLongName(r);
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownValue()
	 */
	@Override
	public String getDropdownValue() {
		return getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getDropdownKey()
	 */
	@Override
	public String getDropdownKey() {
		return id+"";
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownKey(int)
	 */
	@Override
	public void setDropdownKey(int k) {
		id = k;
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#setDropdownValue(java.lang.String)
	 */
	@Override
	public void setDropdownValue(String v) {
		// TODO Auto-generated method stub

	}
	
	public String defaultChoice() {
		return "Select your " + Util.formatClassName(getClass(), null, " ");
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(new AccountChoice(defaultChoice(), 0));
		choices.addAll((List<DropdownChoice>) this.getAll(this.getClass()));
		for(int i = 0; i < choices.size(); i++) {
			choices.get(i).setDropdownKey(i);
		}
		return choices;
	}

	public String getLongName() {
		return longName;
	}
	
	public void setLongName(MyResultRow r) {
		longName = r.get("long_name");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FoodUnit [" + (getPrimaryKey() != null ? "primaryKey=" + getPrimaryKey() + ", " : "")
				+ (longName != null ? "longName=" + longName + ", " : "") + "id=" + id + "]";
	}
	
	
}
