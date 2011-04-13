package com.fbsdata.flexmls_api;

import java.util.Map;

public interface HttpActions<T> {
	
	T get(String path, Map<String, String> options) throws FlexmlsApiClientException;
	T post(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	T put(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	T delete(String path, Map<String, String> options)throws FlexmlsApiClientException;

}
