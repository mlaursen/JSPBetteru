/**
 * 
 */
package com.betteru.database;

import java.util.Arrays;

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
	public static String[] splitOnUpper(String s) {
		String[] upps = splitOn(s, "(?=\\p{Upper})");
		return upps[0].equals("") ? Arrays.copyOfRange(upps, 1, upps.length) : upps;
	}
	public static String[] splitOn(String s, String regex) {
		return s.split(regex);
	}
}
