package com.fbsdata.flexmls_api;

import java.util.Map;

public interface HttpActions {
	
	public Object get(String path, Map<String, String> options) throws FlexmlsApiClientException;
	public Object post(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	public Object put(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	public Object delete(String path, Map<String, String> options)throws FlexmlsApiClientException;

}
