/**
 * 
 */
package com_old.betteru.ingredients.database;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;

import com_old.betteru.accounts.AccountChoice;
import com_old.betteru.database.DatabaseObjectListable;
import com_old.betteru.database.MyResultRow;
import com_old.betteru.database.Util;

/**
 * @author mikkel.laursen
 *
 */
public class FoodUnit extends DatabaseObjectListable implements DropdownChoice {
	{ setPrimaryKeyName("name"); }
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
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		List<FoodUnit> all = getAll(FoodUnit.class);
		for(int i = 0; i < all.size(); i++) {
			all.get(i).setDropdownKey(i);
			choices.add(all.get(i));
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
