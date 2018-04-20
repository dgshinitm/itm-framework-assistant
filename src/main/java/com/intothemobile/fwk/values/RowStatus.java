package com.intothemobile.fwk.values;

public enum RowStatus {
	ACTIVE,
	INACTIVE,
	RESERVATION,
	DELETED;
	
	@Override
	public String toString() {
		return name().toString().toLowerCase();
	}
	
	public String getValue() {
		return name().toString().toLowerCase();
	}
}
