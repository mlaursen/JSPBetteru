/**
 * 
 */
package com.betteru.database;


/**
 * @author mikkel.laursen
 *
 */
public class Package {

	private String name;
	private Procedure[] procedures;
	public Package(String n, Procedure... procedures) {
		name = n;
		this.procedures = procedures;
	}
	
	public String getName() {
		return name;
	}

	/**
	 * @return the procedures
	 */
	public Procedure[] getProcedures() {
		return procedures;
	}

	/**
	 * @param procedures the procedures to set
	 */
	public void setProcedures(Procedure[] procedures) {
		this.procedures = procedures;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String callProcedure(String n) {
		for(Procedure p : procedures)
			if(p.getName().equals(n))
				return p.toString();
		return "";
	}
	
	/**
	 * Creates an uppercase tring to be used in a {call ...} 
	 * @param procedureName
	 * @return
	 */
	public String call(String procedureName) {
		return name.toUpperCase() + "." + callProcedure(procedureName);
	}
}
