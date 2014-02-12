/**
 * 
 */
package com.betteru.database;

/**
 * @author mikkel.laursen
 *
 */
public class Procedure {

	private String name, displayName;
	private boolean hasCursor;
	private String[] params;
	public Procedure(String n, String... params) {
		name = n;
		displayName = n;
		hasCursor = true;
		this.params = params;
	}
	

	
	public Procedure(String n, String displayName, boolean hasCursor, String... params) {
		name = n;
		this.displayName = displayName;
		this.hasCursor = hasCursor;
		this.params = params;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * THIS AUTOMATICALLY ADDES A :CURSOR AS THE FINAL PARAMETER IF HASCURSOR IS TRUE
	 * Turns everything to uppercase
	 */
	public String toString() {
		String s = displayName.toUpperCase() + "(";
		for(int i = 0; i < params.length; i++) {
			String p = params[i].toUpperCase();
			s += ":" + p + (i+1 < params.length ? ", " : "");
			
		}
		s += hasCursor ? (params.length == 0 ? "" : ", ") + ":CURSOR" : "";
		return s + ")";
	}

	/**
	 * @return the params
	 */
	public String[] getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String[] params) {
		this.params = params;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hasCursor
	 */
	public boolean isHasCursor() {
		return hasCursor;
	}

	/**
	 * @param hasCursor the hasCursor to set
	 */
	public void setHasCursor(boolean hasCursor) {
		this.hasCursor = hasCursor;
	}

}
