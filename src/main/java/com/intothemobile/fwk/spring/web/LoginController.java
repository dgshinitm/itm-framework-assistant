package com.intothemobile.fwk.spring.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intothemobile.fwk.spring.entity.AuthenticatedUser;

public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Value("#{configProperties['itm.fwk.authenticated.user.session.name']}")
	private String authenticatedUserSessionName;
	
	@Value("#{configProperties['itm.fwk.login.controller.prefix']}")
	private String PREFIX = "system";
	
	@Value("#{configProperties['itm.fwk.index.page']}")
	private String INDEX_PAGE;

	public LoginController() {}

	@RequestMapping({"/login"})
	public ModelAndView login(HttpSession session) {
		ModelAndView mView = new ModelAndView(PREFIX+"/main/redirect");
		
		if (logger.isDebugEnabled()) { logger.debug("LoginController.login"); }
		
		mView.addObject("redirectUrl", "/loginForm");
		
		return mView;
	}

	@RequestMapping({"/loginForm"})
	public ModelAndView loginForm(HttpSession session) {
		ModelAndView mView = new ModelAndView(PREFIX+"/main/login");
		
		if (logger.isDebugEnabled()) { logger.debug("LoginController.loginForm"); }
		
		return mView;
	}

	@RequestMapping({"/logout"})
	public ModelAndView logout(HttpSession session) {
		ModelAndView mView = new ModelAndView(PREFIX+"/main/login");
		
		if (logger.isDebugEnabled()) { logger.debug("LoginController.loginForm"); }
		
		session.invalidate();

		return mView;
	}

	@RequestMapping("/loginSuccess")
	public ModelAndView loginSuccess(HttpSession session) {
		ModelAndView mView = new ModelAndView(new RedirectView(INDEX_PAGE));

		AuthenticatedUser admin = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		admin.setPassword(null);
		
		if (logger.isDebugEnabled()) {
			logger.debug("LoginController.Success : Logined user is => " + admin);
		}
		
		session.setAttribute(authenticatedUserSessionName, admin);
		
		return mView;
	}
}
