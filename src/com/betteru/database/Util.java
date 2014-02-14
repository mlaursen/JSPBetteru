/**
 * 
 */
package com.betteru.database;

/**
 * @author mikkel.laursen
 *
 */
public class Util {

	public static String combineWith(String[] strs) { return combineWith(strs, "_"); }
	public static String combineWith(String[] strs, String with) {
		String s = "";
		for(int i = 0; i < strs.length; i++) {
			s += strs[i] + (i+1 < strs.length ? with : "" );
		}
		return s;
	}
}
