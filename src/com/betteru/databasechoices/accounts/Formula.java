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
public class Formula extends AccountChoice {
	
	private static final long serialVersionUID = 8008827085566309447L;
	public static final Formula DEFAULT = new Formula();
	public static final Formula HARRIS_BENEDICT = new Formula("HARRIS BENEDICT", 1);
	public static final Formula MIFFLIN_ST_JOER = new Formula("MIFFLIN-ST JOER", 2);
	
	/**
	 * 
	 */
	public Formula() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param primaryKey
	 */
	public Formula(String primaryKey) {
		super(primaryKey);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param primaryKey
	 * @param id
	 */
	public Formula(String primaryKey, int id) {
		super(primaryKey, id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param r
	 */
	public Formula(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.mlaursen.bootstrap.forms.fields.DropdownChoice#getAllChoices()
	 */
	@Override
	public List<DropdownChoice> getAllChoices() {
		List<DropdownChoice> choices = new ArrayList<DropdownChoice>();
		choices.add(DEFAULT);
		choices.add(MIFFLIN_ST_JOER);
		choices.add(HARRIS_BENEDICT);
		return choices;
	}
	
}
