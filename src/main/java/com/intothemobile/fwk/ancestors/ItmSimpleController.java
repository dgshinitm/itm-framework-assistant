package com.intothemobile.fwk.ancestors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.intothemobile.fwk.exception.ItmRuntimeException;

/**
 * Spring MVC Controller의 공통 기능을 제공하는 class.
 * <p>Exception handler를 제공한다.</p>
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @see ItmRuntimeException
 *
 */
public class ItmSimpleController {
	private static final Logger logger = LoggerFactory.getLogger(ItmSimpleController.class);
	
	/**
	 * 일반적인 exception 발생 시에 실행되는 method.
	 * 
	 * <p>ModelAndView 객체를 반환하며, 다음의 정보를 제공한다.</p>
	 * <ul>
	 * <li>errorMessage(String) : Exception으로 전달된 message.</li>
	 * <li>isDebugMode(boolean) : logger 환경 설정에 정의된 debugEnabled.  </li>
	 * <li>Result(String) : "ERROR" 문자열 반환. jTable 지원을 위한 value.</li>
	 * <li>Message(String) : Exception으로 전달된 message. jTable 지원을 위한 value.</li>
	 * </ul>
	 * <p>error page 표시를 위해 "error/error" view로 이동한다.
	 * Springframeework의 DispatcherServlet init parameter 설정 시에 해당 view가 정의되어 있어야 한다.</p>
	 * <pre>
	 * 사용예
	 * <code>
	 * &lt;bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"&gt;
	 * 	&lt;property name="prefix" value="/WEB-INF/views/" /&gt;
	 * 	&lt;property name="suffix" value=".jsp" /&gt;
	 * &lt;/bean&gt;
	 * </code>
	 * 위와 같이 설정된 경우 /WEB-INF/views/error/error.jsp 파일이 존재해야 한다.
	 * </pre>
	 * 
	 * @param e Exception 발생한 exception
	 * @return ModelAndView Spring MVC ModelAndView 객체
	 */
	@ExceptionHandler(Exception.class)
    public ModelAndView genericExceptionHandler(Exception e) {
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
		
		logger.error(e.getMessage());
		
		ModelAndView mView = new ModelAndView("/error/error");
		mView.addObject("errorMessage", e.getMessage());
		mView.addObject("isDebugMode", logger.isDebugEnabled());

		// jTable Error message
		mView.addObject("Result", "ERROR");
		mView.addObject("Message", e.getMessage());
		return mView;
    }
	
	/**
	 * ItmRuntimeException 발생 시에 실행되는 method.
	 * 
	 * <p>ModelAndView 객체를 반환하며, 다음의 정보를 제공한다.</p>
	 * <ul>
	 * <li>errorCode(String) : Exception으로 전달된 error code.</li>
	 * <li>errorMessage(String) : Exception으로 전달된 message.</li>
	 * <li>isDebugMode(boolean) : logger 환경 설정에 정의된 debugEnabled.  </li>
	 * <li>Result(String) : "ERROR" 문자열 반환. jTable 지원을 위한 value.</li>
	 * <li>Message(String) : Exception으로 전달된 message. jTable 지원을 위한 value.</li>
	 * </ul>
	 * <p>error page 표시를 위해 "error/error" view로 이동한다.
	 * Springframeework의 DispatcherServlet init parameter 설정 시에 해당 view가 정의되어 있어야 한다.</p>
	 * <pre>
	 * 사용예
	 * <code>
	 * &lt;bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"&gt;
	 * 	&lt;property name="prefix" value="/WEB-INF/views/" /&gt;
	 * 	&lt;property name="suffix" value=".jsp" /&gt;
	 * &lt;/bean&gt;
	 * </code>
	 * 위와 같이 설정된 경우 /WEB-INF/views/error/error.jsp 파일이 존재해야 한다.
	 * </pre>
	 * 
	 * @param e ItmRuntimeException 발생한 exception
	 * @return ModelAndView Spring MVC ModelAndView 객체
	 */
	@ExceptionHandler(ItmRuntimeException.class)
    public ModelAndView runtimeExceptionHandler(ItmRuntimeException e) {
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
		
		logger.error(e.getMessage());
		
		ModelAndView mView = new ModelAndView(e.getErrorView());
		mView.addObject("errorCode", e.getErrorCode());
		mView.addObject("errorMessage", e.getMessage());
		mView.addObject("isDebugMode", logger.isDebugEnabled());

		// jTable Error message
		mView.addObject("Result", "ERROR");
		mView.addObject("Message", e.getMessage());
		return mView;
    }
}
