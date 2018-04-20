package com.intothemobile.fwk.values;

import com.intothemobile.fwk.ancestors.ItmValue;

public class RedirectResult extends ItmValue {
	private static final long serialVersionUID = 3439861270610853083L;
	
	private String type;
	private String message;
	private boolean notify;
	private String target;
	
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
	public boolean isNotify() {
		return notify;
	}
	public boolean getNotify() {
		return notify;
	}
	public void setNotify(boolean notify) {
		this.notify = notify;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
