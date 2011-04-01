package com.fbsdata.flexmls_api;

import java.util.HashMap;
import java.util.Map;


public abstract class Connection<R> implements HttpActions {
	// TODO Make this truly read only.
	private static final Map<String, String> EMPT_MAP = new HashMap<String, String>(); 
	
	public Object get(String path) throws FlexmlsApiClientException {
		return get(path, EMPT_MAP);
	}
	public Object post(String path, String body) throws FlexmlsApiClientException {
		return post(path, body, EMPT_MAP);
	}

	public Object put(String path, String body) throws FlexmlsApiClientException {
		return put(path, body, EMPT_MAP);
	}

	public Object delete(String path) throws FlexmlsApiClientException {
		return delete(path, EMPT_MAP);
	}

}
