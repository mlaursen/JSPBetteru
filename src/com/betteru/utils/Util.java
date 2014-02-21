package com.betteru.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import com.github.mlaursen.database.objects.MyResultRow;

public class Util {

	public static java.sql.Date stringToDate(String s) {
		boolean hyphen = s.contains("-");
		s = s.replace("-", "").replace("/", "");
		try {
			String format = hyphen ? "yyyyMMdd" : "MMddyyyy";
			
			return new java.sql.Date(new SimpleDateFormat(format).parse(s).getTime());
		}
		catch (ParseException e) {
			return new java.sql.Date(Calendar.getInstance().getTime().getTime());
		}
	}

	

	public static java.sql.Date sysdateToDate(String sys) {
		if (sys != null) {
			try {
				java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd h:m:s").parse(sys);
				return new java.sql.Date(utilDate.getTime());
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar cal = Calendar.getInstance();
		return new java.sql.Date(cal.getTime().getTime());
	}

	/**
	 * 
	 * @param user
	 * @param pswd
	 * @return
	 */
	public static String createHash(String user, String pswd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();
			String ran = uuidToHexString(UUID.randomUUID());
			md.update((ran + " fjdka fjkfdas 439 F" + user).getBytes("UTF-8"));
			String salt = bytesToHexString(md.digest());
			return repeatedHashing(salt, pswd);
		}
		catch (Exception e) {
			return "";
		}
	}

	public static String repeatedHashing(String salt, String pswd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hash = salt + pswd;
			for (int i = 0; i < 10000; i++) {
				md.update(hash.getBytes("UTF-8"));
				hash = bytesToHexString(md.digest());
			}
			return salt + hash;
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * http://www.mkyong.com/java/java-sha-hashing-example/
	 * 
	 * @param bs
	 * @return
	 */
	public static String bytesToHexString(byte[] bs) {
		int pow = 16;
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return
	 */
	public static String createCode() {
		return createCode(32);
	}

	public static String createCode(int size) {
		String c = uuidToHexString(UUID.randomUUID());
		return c.substring(0, size);
	}

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public static String uuidToHexString(UUID uuid) {
		return Long.toHexString(uuid.getMostSignificantBits()) + Long.toBinaryString(uuid.getLeastSignificantBits());
	}
}
