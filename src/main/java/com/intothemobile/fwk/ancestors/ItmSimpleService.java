package com.intothemobile.fwk.ancestors;

import java.util.List;

/**
 * CRUD 관련 기본 기능을 정의한 interface.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @param <T> {@link ItmValue}를 상속한 class.
 */
public interface ItmSimpleService<T extends ItmValue> {
	/**
	 * Entity object를 추가한다.
	 * 
	 * @param entity 추가할 entity object.
	 * @return int The number of rows affected by the adding.
	 * @throws Exception Exception
	 */
	int add (T entity) throws Exception;
	
	/**
	 * Entity object를 수정한다.
	 * 
	 * @param entity 수정할 entity object.
	 * @return int The number of rows affected by the editing.
	 * @throws Exception Exception
	 */
	int edit (T entity) throws Exception;
	
	/**
	 * Entity object를 삭제한다.
	 * 
	 * @param entity 삭제할 entity object.
	 * @return int The number of rows affected by the removing.
	 * @throws Exception Exception
	 */
	int remove (T entity) throws Exception;
	
	/**
	 * Entity object로 전달된 parameter를 filtering한 조회 갯수를 구한다.
	 * 
	 * @param entity filtering 조건을 담고 있는 entity object.
	 * @return long The count of rows filtered by the entity.
	 * @throws Exception Exception
	 */
	Long getTotal (T entity) throws Exception;
	
	/**
	 * Entity object로 전달된 parameter 조건에 해당하는 entity object를 구한다.
	 * 
	 * @param entity 조건에 해당하는 parameter object.
	 * @return T 조회된 entity object.
	 * @throws Exception Exception
	 */
	T find (T entity) throws Exception;
	
	/**
	 * Entity object로 전달된 parameter를 filtering한 조회 목록을 구한다.
	 * 
	 * @param entity filtering 조건을 담고 있는 entity object.
	 * @return List&lt;T&gt; 조회된 목록.
	 * @throws Exception Exception
	 */
	List<T> findList (T entity) throws Exception;
}
