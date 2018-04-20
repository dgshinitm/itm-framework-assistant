package com.intothemobile.fwk.spring.entity;

import com.intothemobile.fwk.ancestors.ItmEntity;

/**
 * 사용자 인증을 위한 entity 유형.
 * <p>
 * Spring Security 사용 시 인증된 사용자 정보를 전달하기 위한 entity class.
 * </p>
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public class AuthenticatedUser extends ItmEntity {
	private static final long serialVersionUID = -6564434018012265708L;
	
	String id;
	String password;
	
	public AuthenticatedUser(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
