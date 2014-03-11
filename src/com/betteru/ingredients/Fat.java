package com.betteru.ingredients;

import com.github.mlaursen.database.objects.MyResultRow;

public class Fat extends Macro {

	/**
	 * @param amt
	 */
	public Fat(String amt) {
		super(amt);
		// TODO Auto-generated constructor stub
	}

	public Fat(double a) {
		super(a);
		setToCals(9);
	}
	
	public Fat(MyResultRow r) {
		super(r);
		setToCals(9);
	}
	
}
