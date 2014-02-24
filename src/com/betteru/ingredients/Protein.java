package com.betteru.ingredients;

import com.github.mlaursen.database.objects.MyResultRow;

public class Protein extends Macro {

	/**
	 * @param amt
	 */
	public Protein(String amt) {
		super(amt);
		// TODO Auto-generated constructor stub
	}

	public Protein(double a) {
		super(a);
	}
	
	public Protein(MyResultRow r) {
		super(r);
	}
	
}
