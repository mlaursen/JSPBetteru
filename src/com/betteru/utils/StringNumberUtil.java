package com.betteru.utils;

import java.text.DecimalFormat;

import com.github.mlaursen.database.objects.MyResultRow;

public class StringNumberUtil {

	public static final String ONE_FORTH = "1/4", ONE_THIRD = "1/3", ONE_HALF ="1/2", ONE_EIGTH = "1/8";
	public static final String[] FRACTIONS = new String[]{ ONE_HALF, ONE_FORTH, ONE_THIRD, ONE_EIGTH };
	
	/**
	 * Converts a double into a Fraction string.
	 * i.e. .25 -> 1/4
	 * @param d	The double
	 * @return	A possible fraction
	 */
	public static String formatFractionString(double d) { return formatFractionString(d + ""); }
	
	/**
	 * Converts a string of a number into a fraction String.
	 * i.e. "0.25" -> "1/4"
	 * @param number
	 * @return
	 */
	public static String formatFractionString(String number) {
		number = number.trim();
		if(number.contains("."))
			number = "0" + number;
		String[] split = number.split("\\.");
		if(split.length == 2) {
			int whole = Integer.parseInt(split[0]);
			double dec = Double.parseDouble(number);
			dec = dec - whole;
			double dec2 = Double.parseDouble(new DecimalFormat("#.##").format(dec));
			double dec3 = Double.parseDouble(new DecimalFormat("#.###").format(dec));
			String s = (whole == 0 ? "" : whole + " ");
			if(dec2 == .5) {
				return s + ONE_HALF;
			}
			else if(dec2 == .33)
				return s + ONE_THIRD;
			else if(dec2 == .25)
				return s + ONE_FORTH;
			else if(dec3 == .125)
				return s + ONE_EIGTH;
		}
		return formatDecimalString(number);
	}
	
	/**
	 * Formats a decimal to 2 decimal places
	 * @param d
	 * @return
	 */
	public static String formatDecimalString(double d) { return formatDecimalString(d+""); }
	public static String formatDecimalString(String dec) {
		return String.format("%4.2f", Double.parseDouble(dec));
	}
	
	/**
	 * Converts a String fraction, i.e. "1/4" into it's double counterpart
	 * @param frac
	 * @return
	 */
	public static double calculateStringFraction(String frac) {
		frac = frac.trim();
		String[] split = frac.split("/");
		if(split.length == 2) {
			double numerator = Integer.parseInt(split[0]);
			double denomenator = Integer.parseInt(split[1]);
			return numerator / denomenator;
		}
		else {
			try {
				return Double.parseDouble(frac);
			}
			catch(NullPointerException | NumberFormatException e) {
				return 0;
			}
		}
	}

	
	/**
	 * Attempts to parse a double from a ResultRow. Returns -1 if it was unsuccessful and displays
	 * an error in the System.err
	 * @param r	The MyResultRow to get the field from
	 * @param field The field name
	 * @return
	 */
	public static Double attemptParseDouble(MyResultRow r, String field) {
		return attemptParseDouble(r.get(field), 0);
	}
	
	public static Double attemptParseDouble(MyResultRow r, String field, double fallback) {
		return attemptParseDouble(r.get(field), fallback);
	}
	
	public static Double attemptParseDouble(String possibleDouble) { return attemptParseDouble(possibleDouble, 0); }
	public static Double attemptParseDouble(String possibleDouble, double fallback) {
		try {
			return Double.parseDouble(possibleDouble);
		}
		catch(NullPointerException | NumberFormatException e) {
			//System.err.println(possibleDouble + " was unable to be parsed as a double.");
			return fallback;
		}
	}
	
	public static Integer attemptParseInteger(MyResultRow r, String field) {
		return attemptParseInteger(r.get(field), 0);
	}
	
	public static Integer attemptParseInteger(MyResultRow r, String field, Integer fallback) {
		return attemptParseInteger(r.get(field), fallback);
	}
	
	public static Integer attemptParseInteger(String possibleInteger) { return attemptParseInteger(possibleInteger, 0); }
	public static Integer attemptParseInteger(String possibleInteger, Integer fallback) {
		try {
			return Integer.parseInt(possibleInteger);
		}
		catch(NullPointerException | NumberFormatException e) {
			//System.err.println(possibleInteger + " was unable to be parsed as an Integer.");
			return fallback;
		}
	}
}
