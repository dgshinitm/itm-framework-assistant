package com.intothemobile.fwk.spring.service;

/**
 * Application에서 제공하는 메뉴 처리를 위한 interface.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @param <T> Menu entity class
 */
public interface ItmMenuService<T> {
	/**
	 * 지정한 pk로 메뉴 정보를 조회한다.
	 * @param pk Primary key
	 * @return 메뉴 ojbect
	 */
	T findMenu(String pk);
}
