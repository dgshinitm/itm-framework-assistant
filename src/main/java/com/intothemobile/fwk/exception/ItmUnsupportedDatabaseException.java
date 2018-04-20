package com.intothemobile.fwk.exception;

public class ItmUnsupportedDatabaseException extends Exception {
	private static final long serialVersionUID = -4460294423094511776L;

	public ItmUnsupportedDatabaseException() {
		super("Unsupport database type");
	}
	public ItmUnsupportedDatabaseException(String dbType) {
		super("Unsupport database type : " + dbType);
	}
}
