package com.intothemobile.fwk.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Spring security AccessDeniedHanler 구현체.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public class ItmAccessDeniedHandler implements AccessDeniedHandler {

	public ItmAccessDeniedHandler() {}

	private String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedExecption) throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + errorPage);
	}
}
