package com.intothemobile.fwk.ancestors;

import java.util.List;

/**
 * Sql session 공통 기능을 정의한 interface.
 * <p>Spring - MyBatis 연동 기능 호환을 위해 만들었음.</p>
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @param <T> {@link ItmValue}를 상속한 class.
 */
public interface ItmMapper<T extends ItmValue> {
	
	/**
	 * Entity 객체 단건 insert를 위한 method.
	 * <p>Mapper id가 insert인 mapper를 실행함.</p>
	 * 
	 * @param entity T type Object.
	 * @return int The number of rows affected by the insert.
	 */
	int insert(T entity);
	
	/**
	 * Entity 객체 단건 update를 위한 method.
	 * <p>Mapper id가 update인 mapper를 실행함.</p>
	 * 
	 * @param entity T type Object.
	 * @return int The number of rows affected by the update.
	 */
	int update(T entity);
	
	/**
	 * Entity 객체 단건 delete를 위한 method.
	 * <p>Mapper id가 delete인 mapper를 실행함.</p>
	 * 
	 * @param entity T type Object.
	 * @return int The number of rows affected by the delete.
	 */
	int delete(T entity);
	
	/**
	 * Entity 객체에 전달된 parameter를 filtering한 조회 갯수를 구하기 위한 method.
	 * <p>Mapper id가 selectTotal인 mapper를 실행함.</p>
	 * {@code e.g. SELECT COUNT(ID) FROM SOME_TABLE WHERE SOME_CONDITION = 'someValue'}
	 * 
	 * @param entity T type Object.
	 * @return long The count of rows filtered by the entity.
	 */
	Long selectTotal(T entity);
	
	/**
	 * Entity 객체 단건 select를 위한 method.
	 * <p>Mapper id가 select인 mapper를 실행함.</p>
	 * 
	 * @param entity T type Object.
	 * @return T type mapped object.
	 */
	T select(T entity);
	
	/**
	 * Entity 객체에 전달된 parameter를 filtering한 조회 목록을 구하기 위한 method.
	 * <p>Mapper id가 selectList인 mapper를 실행함.</p>
	 * <pre>
	 * e.g. SELECT FIELD_1
	 *           , FIELD_2
	 *        FROM SOME_TABLE
	 *       WHERE SOME_CONDITION = 'someValue'
	 * </pre>
	 * 
	 * @param entity T type Object.
	 * @return T type list object.
	 */
	List<T> selectList(T entity);
}
