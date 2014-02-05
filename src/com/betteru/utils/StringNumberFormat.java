package com.betteru.utils;

public class StringNumberFormat {

	public static final String[] FRACTIONS = new String[]{ "1/2", "1/3", "1/4", "1/8" };
	public static String formatFractionString(String number) {
		if(number.contains("."))
			number = "0" + number;
		String[] split = number.split("\\.");
		if(split.length == 2) {
			int whole = Integer.parseInt(split[0]);
			double dec = Double.parseDouble(number);
			dec = dec - whole;
			String s = (whole == 0 ? "" : whole + " ");
			if(dec == .5) {
				return s + FRACTIONS[0];
			}
			else if(dec == .33)
				return s + FRACTIONS[1];
			else if(dec == .25)
				return s + FRACTIONS[2];
			else if(dec == .125)
				return s + FRACTIONS[3];
		}
		return formatDecimalString(number);
	}
	
	public static String formatDecimalString(String dec) {
		return String.format("%4.2f", Double.parseDouble(dec));
	}
	
	public static double calculateStringFraction(String frac) {
		String[] split = frac.split("/");
		if(split.length == 2) {
			double numerator = Integer.parseInt(split[0]);
			double denomenator = Integer.parseInt(split[1]);
			return numerator / denomenator;
		}
		else
			return 0;
	}
}
