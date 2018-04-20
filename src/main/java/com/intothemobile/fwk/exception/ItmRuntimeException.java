package com.intothemobile.fwk.exception;

/**
 * RuntimeException 확장.
 * <p>RuntimeException 발생 시 error code 및 공통 error page로 이동하기 위한 view를 설정할 수 있다.</p>
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public class ItmRuntimeException extends Exception {
	private static final long serialVersionUID = -8670172281602520738L;
	
	private String errorCode = "9999";
	private String errorView = "/error/error";

	public ItmRuntimeException() {
		super("Runtime exception occured");
	}
	public ItmRuntimeException(String code, String message) {
		super(message);
		this.errorCode = code;
	}
	public ItmRuntimeException(String code, String message, String view) {
		super(message);
		this.errorCode = code;
		this.errorView = view;
	}
	
	/**
	 * Log 출력을 위한 error message 반환.
	 * @return String 구분자를 pipe(|)로 연결한 error 문자열 반환
	 */
	public String getErrorLog() {
		return this.getErrorLog("|");
	}
	
	/**
	 * Log 출력을 위한 error message 반환.
	 * @param separator Message 항목을 구분하기 위한 구분자.
	 * @return String 구분자를 separator로 연결한 error 문자열 반환
	 */
	public String getErrorLog(String separator) {
		StringBuilder error = new StringBuilder();
		
		error.append(this.errorCode)
		.append(separator)
		.append(this.getMessage())
		.append(separator)
		.append(this.getCause() != null ? this.getCause() : "")
		;
		
		return error.toString();
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorView() {
		return errorView;
	}
	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}
}
