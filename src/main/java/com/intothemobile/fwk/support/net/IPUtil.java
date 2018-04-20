package com.intothemobile.fwk.support.net;

public class IPUtil {
	public static String digitToHex(String ip) {
		String[] addr = ip.split("\\.");
		
		String hex = "";
		for (String s : addr) {
			s = Integer.toHexString(Integer.parseInt(s));
			if (s.length() < 2) {
				s = "0"+s;
			}
			hex += s;
		}
		
		return hex.toUpperCase();
	}
}
