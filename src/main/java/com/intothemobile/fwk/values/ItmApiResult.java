package com.intothemobile.fwk.values;

import com.intothemobile.fwk.ancestors.ItmValue;

public class ItmApiResult extends ItmValue {
	private static final long serialVersionUID = 6654080828997252924L;

	private String type;
	private String message;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
