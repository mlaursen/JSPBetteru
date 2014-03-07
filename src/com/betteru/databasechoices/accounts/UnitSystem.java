/**
 * 
 */
package com.betteru.databasechoices.accounts;

import java.util.ArrayList;
import java.util.List;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com.github.mlaursen.database.objects.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class UnitSystem extends AccountChoice {
	public static final UnitSystem DEFAULT  = new UnitSystem();
	public static final UnitSystem IMPERIAL = new UnitSystem("IMPERIAL", 1);
	public static final UnitSystem METRIC   = new UnitSystem("METRIC", 2);
	/**
	 * 
	 */
	public UnitSystem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 */
	public UnitSystem(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param id
	 */
	public UnitSystem(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public UnitSystem(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(DEFAULT);
		choices.add(IMPERIAL);
		choices.add(METRIC);
		return choices;
	}

}
