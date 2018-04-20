package com.intothemobile.fwk.exception;

public class ItmServiceException extends Throwable {
	private static final long serialVersionUID = -8670172281602520738L;

	public ItmServiceException() {
		super("Service exception occured");
	}
	public ItmServiceException(String message) {
		super(message);
	}
}
