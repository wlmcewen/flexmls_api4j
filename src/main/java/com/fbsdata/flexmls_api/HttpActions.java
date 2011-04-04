package com.fbsdata.flexmls_api;

import java.util.Map;

public interface HttpActions<T> {
	
	public T get(String path, Map<String, String> options) throws FlexmlsApiClientException;
	public T post(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	public T put(String path, String body, Map<String, String> options)throws FlexmlsApiClientException;
	public T delete(String path, Map<String, String> options)throws FlexmlsApiClientException;

}
