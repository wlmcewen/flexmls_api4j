package com.flexmls.flexmls_api;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All the documented API response codes.
 */
public enum ApiParameter {
	// Search
	_filter("_filter"),
	_expand("_expand"),
	// Pagination
	_pagination("_pagination"),
	_limit("_limit"),
	_page("_page"),
	_orderby("_orderby"),
	// Market Stats
	LocationField("LocationField"),
	LocationValue("LocationValue"),
	Options("Options"),
	PropertyTypeCode("PropertyTypeCode"),
	// Hotsheet
	HotSheet("HotSheet"),
	OpenHouses("OpenHouses"),
	// Api client fundamentals
	ApiKey("ApiKey"),
	ApiSig("ApiSig"),
	AuthToken("AuthToken"),
	ApiUser("ApiUser");

	private static final Map<String, ApiParameter> MAP = new HashMap<String, ApiParameter>();

	static {
		for (ApiParameter s : EnumSet.allOf(ApiParameter.class)) {
			MAP.put(s.getCode(), s);
		}
	}

	private String code;

	private ApiParameter(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static ApiParameter get(String code) throws FlexmlsApiClientException {
		if(MAP.containsKey(code)){
			throw new FlexmlsApiClientException("Unsupported API Parameter");
		}
		return MAP.get(code);
	}
}
