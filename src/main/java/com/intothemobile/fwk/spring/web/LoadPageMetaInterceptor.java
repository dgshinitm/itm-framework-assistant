package com.intothemobile.fwk.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.intothemobile.fwk.spring.service.ItmMenuService;

public class LoadPageMetaInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(LoadPageMetaInterceptor.class);
	
	private ItmMenuService<?> menuService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String pk = (String) request.getParameter("currentMenuUid");
		
		/*
		 * When currentMenuUid is not exist, use requested path.
		 */
		if (StringUtils.isEmpty(pk)) {
			pk = request.getRequestURI().substring(request.getContextPath().length());
		}
		
		if (logger.isDebugEnabled()) { logger.debug(pk); }
		
		if (pk != null && !"".equals(pk)) {
			if (modelAndView != null) {
				modelAndView.addObject("itmCurrentMenu", menuService.findMenu(pk));
			} else {
				logger.warn("modelAndView is null");
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Do nothing...
	}

	public ItmMenuService<?> getMenuService() {
		return menuService;
	}

	public void setMenuService(ItmMenuService<?> menuService) {
		this.menuService = menuService;
	}

}
