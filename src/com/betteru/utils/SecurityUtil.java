package com.betteru.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SecurityUtil {	

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
	
	/**
	 * Takes in a salt and a password and hashes it many times and returns 
	 * the salt concatentated to the hash.
	 * @param salt
	 * @param pswd
	 * @return
	 */
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
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * Creates a random code of default size (32)
	 * @return
	 */
	public static String createCode() {
		return createCode(32);
	}

	/**
	 * Creates a random code of the size given.
	 * @param size
	 * @return
	 */
	public static String createCode(int size) {
		String c = uuidToHexString(UUID.randomUUID());
		return c.substring(0, size);
	}

	/**
	 * Converts a UUID into a string by adding the most significant bits to the least significant bits
	 * @param uuid
	 * @return
	 */
	public static String uuidToHexString(UUID uuid) {
		return Long.toHexString(uuid.getMostSignificantBits()) + Long.toBinaryString(uuid.getLeastSignificantBits());
	}
}
