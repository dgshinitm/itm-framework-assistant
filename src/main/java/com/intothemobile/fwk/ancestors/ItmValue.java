package com.intothemobile.fwk.ancestors;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Value object 표현을 위한 abstract class.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public abstract class ItmValue implements Serializable {
	private static final long serialVersionUID = -5555177673399045475L;

	/**
	 * toString 메소드 재정의. Json format으로 반환함.
	 * @return Json format String.
	 */
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
