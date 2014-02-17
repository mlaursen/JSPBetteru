package com.betteru.database;

public class MyClob {

	private String v;
	public MyClob(String v) {
		this.v = v;
	}
	
	public String getValue() {
		return v;
	}
	
	public void setValue(String v) {
		this.v = v;
	}
	
	@Override
	public String toString() {
		return v;
	}

}
