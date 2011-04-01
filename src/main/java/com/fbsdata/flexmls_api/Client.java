package com.fbsdata.flexmls_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client implements HttpActions {

	Configuration config = null;
	Connection<Response> connection = null;
	
	public Client(Configuration config) {
		super();
		this.config = config;
	}

	@Override
	public Object get(String path, Map<String, String> options) throws FlexmlsApiClientException {
		String signedPath = signToken(path, options, "");
		return connection.get(signedPath);
	}

	@Override
	public Object post(String path, String body, Map<String, String> options) throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(String path, String body, Map<String, String> options) throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object delete(String path, Map<String, String> options) throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Session authenticate() throws FlexmlsApiClientException {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		String signature = sign(b.toString());
		String path = authPath(signature);
		
		Object response = connection.post(path,"", new HashMap<String, String>());
		
		Session s = null;
		// TODO Auto-generated method stub
		return s;
	}
	
	private String authPath(String sig){
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append("/session?");
		b.append("ApiKey=").append(config.getApiKey());
		b.append("&ApiSig=").append(sig);
		return b.toString();
	}
	
	protected String sign(String s){
		return MD5.checksum(s);
	}
	
	protected String signToken(String path, Map<String, String> options, String body){
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		b.append("ServicePath/").append(config.getVersion()).append(path);
		b.append(buildParamString(options));
		b.append(body);
		return sign(b.toString());
	}
	
	protected String buildParamString(Map<String, String> params) {
		List<String> list = new ArrayList<String>(params.keySet());
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (String key : list) {
			buffer.append(key).append(params.get(key));
		}
		return buffer.toString();
	}
}
