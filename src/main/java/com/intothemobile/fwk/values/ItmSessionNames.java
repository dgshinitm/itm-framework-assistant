package com.intothemobile.fwk.values;

public enum ItmSessionNames {
	SITE_META,
	LOGIN_USER;
	
	@Override
	public String toString() {
		return "_ITM_SESSION_"+name().toString()+"_";
	}
}
