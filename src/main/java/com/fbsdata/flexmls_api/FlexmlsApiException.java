package com.fbsdata.flexmls_api;

/**
 * Errors Specifically returned from the API
 * 
 */
public class FlexmlsApiException extends FlexmlsApiClientException {
	private static final long serialVersionUID = -8156427208964545915L;
	private String message;
	private int code;
	private int status;
	public FlexmlsApiException(String message, int code, int status) {
		super();
		this.message = message;
		this.code = code;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
	public int getStatus() {
		return status;
	}
}
