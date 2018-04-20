package com.intothemobile.fwk.support.jquery;

import com.google.gson.Gson;

public class JTableParam {
	private Long jtStartIndex = 1L;
	private Long jtPageSize = 10L;
	private String jtSorting;
	
	public Long getJtStartIndex() {
		return jtStartIndex;
	}
	public void setJtStartIndex(Long jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}
	public Long getJtPageSize() {
		return jtPageSize;
	}
	public void setJtPageSize(Long jtPageSize) {
		this.jtPageSize = jtPageSize;
	}
	public String getJtSorting() {
		return jtSorting;
	}
	public void setJtSorting(String jtSorting) {
		this.jtSorting = jtSorting;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
