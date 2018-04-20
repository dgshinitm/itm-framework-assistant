package com.intothemobile.fwk.ancestors;

/**
 * View page와 Controller의 parameter 형식의 value를 제어하기 위한 method 정의.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @see ItmValue
 * @see ItmEntity
 * @see Paging
 *
 */
public interface ItmParam {
	/**
	 * 목록의 page 처리를 위한 Paging object 반환 
	 * @return Paging object
	 */
	public Paging getPage();
	
	/**
	 * 목록의 page 처리를 위한 Paging object 설정
	 * @param page Paging object
	 */
	public void setPage(Paging page);
}
