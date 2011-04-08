package com.fbsdata.flexmls_api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Homebrew MD5 library. Chose this for our lightweight needs instead of adding
 * yet another dependency.
 * 
 * @author wade
 * 
 */
public class MD5 {
	
	private MD5(){}  // Utility class
	
	static final String HEXES = "0123456789abcdef";

	private static MessageDigest md = null;
	
	// We really shouldn't have a problem loading this in
	private static MessageDigest md(){
		if(md == null){
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return md;
	}

	public static String checksum(String s) {
		return checksum(s.getBytes());
	}

	public static String checksum(byte[] bytes) {
		return getHex(md().digest(bytes));
	}

	public static String getHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
}
