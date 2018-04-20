package com.intothemobile.fwk.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class MappingJackson2JsonpView extends MappingJackson2JsonView {
	private String[] jsonCallbackNames;

	public MappingJackson2JsonpView() {}
	
	public void setJsonCallbackNames(String[] jsonCallbackNames) {
		this.jsonCallbackNames = jsonCallbackNames;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map params = request.getParameterMap();

		String callbackName = getCallbackFunc(params);
		logger.debug("Jsonp callback function name : " + callbackName);
		
		if (! StringUtils.isEmpty(callbackName)) {
			StringBuilder buff = new StringBuilder();
			buff.append(((String[]) params.get(callbackName))[0]).append("(");

			response.getOutputStream().write(buff.toString().getBytes());

			super.render(model, request, response);

			response.getOutputStream().write(");".getBytes());
			response.setContentType(getContentType());
		} else {
			super.render(model, request, response);
		}
	}

	private String getCallbackFunc(Map<String, String[]> params) {
		if ((this.jsonCallbackNames != null)
				&& (this.jsonCallbackNames.length > 0)) {
			for (String name : this.jsonCallbackNames) {
				if (params.containsKey(name)) {
					return name;
				}
			}
		}
		return null;
	}
}
