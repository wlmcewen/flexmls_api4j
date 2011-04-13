package com.flexmls.flexmls_api;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All the documented API response codes.
 */
public enum ApiCode {
	NOT_FOUND(404), 
	NOT_ALLOWED(405), 

	INVALID_KEY(1000), 
	DISABLED_KEY(1010), 
	SESSION_EXPIRED(1020), 
	SSL_REQUIRED(1030), 
	INVALID_JSON(1035), 
	INVALID_FILTER(1040), 
	MISSING_PARAMETER(1050), 
	INVALID_PARAMETER(1053), 
	INVALID_WRITE(1055), 
	
	RESOURCE_UNAVAILABLE(1500), 
	RATE_LIMIT_EXCEEDED(1550),
	// For missing codes that the library doesn't know about yet.
	UNKNOWN_API_CODE(0);

	private static final Map<Integer, ApiCode> lookup = new HashMap<Integer, ApiCode>();

	static {
		for (ApiCode s : EnumSet.allOf(ApiCode.class)) {
			lookup.put(s.getCode(), s);
		}
	}

	private int code;

	private ApiCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ApiCode get(int code) {
		ApiCode apiCode = lookup.containsKey(code) ? lookup.get(code) : UNKNOWN_API_CODE;
		return apiCode;
	}
}
