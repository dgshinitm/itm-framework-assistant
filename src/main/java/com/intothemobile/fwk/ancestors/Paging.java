package com.intothemobile.fwk.ancestors;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Paging 정보를 위한 class.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public class Paging implements Serializable {
	private static final long serialVersionUID = -232108873119603056L;

	/**
	 * 전체 row 수
	 */
	private Long totalRows;
	
	/**
	 * 전체 page 수
	 */
	private int totalPages;
	
	/**
	 * 한 화면에 보여줄 page 수
	 */
	private int blockSize;
	
	/**
	 * 현재 page 번호
	 */
	private Long page;
	
	/**
	 * 요청된 row 수 (한 화면에 보여주는 row 수)
	 */
	private Long requestedRows;
	
	/**
	 * 시작 row 순번
	 */
	private Long startNo;
	
	/**
	 * 끝 row 순번
	 */
	private Long endNo;
	
	/**
	 * 첫번째 page 번호
	 */
	private Long firstPage;
	
	/**
	 * 마지막 page 번호
	 */
	private Long lastPage;

	/**
	 * 정렬 field 명
	 */
	private String sortIndex;
	
	/**
	 * 정렬 방식 (asc / desc)
	 */
	private String sortOrder;
	
	public Paging () {
		this.page          = (long) 1;
		this.requestedRows = (long) 10;
		this.blockSize     = 10;
		resetValues();
	}
	
	public Paging (String page, String requestedRows, String startNo, String endNo) {
		this.page          = page          != null ? Long.parseLong(page)          : 1;
		this.requestedRows = requestedRows != null ? Long.parseLong(requestedRows) : 10;
		this.blockSize     = 10;
	}
	
	public void setSortInfo (String sortIndex, String sortOrder) {
		this.sortIndex = sortIndex;
		this.sortOrder = sortOrder;
	}

	private void resetValues() {
		if (page == null) page = (long) 1;
		if (requestedRows == null) requestedRows = (long) 10;
		
		this.startNo = (page - 1) * requestedRows;
		this.endNo = page * requestedRows;
		
		if (this.totalRows != null && this.requestedRows != null) {
			this.totalPages = (int) Math.ceil((double) this.totalRows / this.requestedRows);
			if (this.totalPages < page) page = (long) 1;
			
			// 시작 페이지 세팅
			this.firstPage = this.page - ( ( this.page - 1) % blockSize );
			// 끝 페이지 세팅
			this.lastPage = Math.min(this.firstPage + this.blockSize, this.totalPages + 1);
		}
	}
	
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
		resetValues();
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getRequestedRows() {
		return requestedRows;
	}
	public void setRequestedRows(Long requestedRows) {
		this.requestedRows = requestedRows;
		resetValues();
	}
	public Long getStartNo() {
		return startNo;
	}
	public void setStartNo(Long startNo) {
		this.startNo = startNo;
	}
	public Long getEndNo() {
		return endNo;
	}
	public void setEndNo(Long endNo) {
		this.endNo = endNo;
	}
	public String getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public Long getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(Long firstPage) {
		this.firstPage = firstPage;
	}
	public Long getLastPage() {
		return lastPage;
	}
	public void setLastPage(Long lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
