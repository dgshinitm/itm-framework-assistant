package com.intothemobile.fwk.spring.service;

import com.intothemobile.fwk.spring.entity.AuthenticatedUser;

/**
 * Spring security 인증을 공통으로 사용하기 위해서 공통으로 필요한 interface 정의. 
 * @author Donggill Shin
 * @since 0.0.1
 */
public interface AuthenticationService {

	/**
	 * ID와 Password로 사용자를 조회한다.
	 * @param authenticatedUser ID 및 Password를 담고 있는 object
	 * @return AuthenticatedUser 조회된 사용자 object
	 */
	AuthenticatedUser findByIdPwd(AuthenticatedUser authenticatedUser);

}
