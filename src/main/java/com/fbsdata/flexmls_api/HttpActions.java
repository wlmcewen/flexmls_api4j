package com.fbsdata.flexmls_api;

import java.util.Map;

public interface HttpActions<T, U> {
	
	T get(String path, Map<U, String> options) throws FlexmlsApiClientException;
	T post(String path, String body, Map<U, String> options)throws FlexmlsApiClientException;
	T put(String path, String body, Map<U, String> options)throws FlexmlsApiClientException;
	T delete(String path, Map<U, String> options)throws FlexmlsApiClientException;

}
