/**
 * 
 */
package com_old.betteru.accounts;

import java.util.List;

import com_old.betteru.database.MyResultRow;

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

	public static UnitSystem get(String name) {
		return new UnitSystem().get(name, UnitSystem.class);
	}
	
	public List<UnitSystem> getAll() {
		return new UnitSystem().getAll(UnitSystem.class);
	}
}
