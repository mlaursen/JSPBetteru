/**
 * 
 */
package com.betteru.accounts;

import java.util.List;

import com.betteru.database.MyResultRow;

/**
 * @author mikkel.laursen
 *
 */
public class UnitSystem extends AccountChoice {

	public UnitSystem() { }

	/**
	 * @param n
	 */
	public UnitSystem(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 */
	public UnitSystem(MyResultRow r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	public UnitSystem get(String name) {
		return get(name, UnitSystem.class);
	}
	
	public List<UnitSystem> getAll() {
		return getAll(UnitSystem.class);
	}
}
