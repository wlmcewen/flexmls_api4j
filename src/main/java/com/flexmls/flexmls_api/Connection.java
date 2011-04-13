package com.flexmls.flexmls_api;

import java.util.HashMap;
import java.util.Map;


public abstract class Connection<R> implements HttpActions<R, String> {
	// TODO Make this truly read only.
	private static final Map<String, String> EMPT_MAP = new HashMap<String, String>(); 
	
	public R get(String path) throws FlexmlsApiClientException {
		return get(path, EMPT_MAP);
	}
	public R post(String path, String body) throws FlexmlsApiClientException {
		return post(path, body, EMPT_MAP);
	}

	public R put(String path, String body) throws FlexmlsApiClientException {
		return put(path, body, EMPT_MAP);
	}

	public R delete(String path) throws FlexmlsApiClientException {
		return delete(path, EMPT_MAP);
	}

}
