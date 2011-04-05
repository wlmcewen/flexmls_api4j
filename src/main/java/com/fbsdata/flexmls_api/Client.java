package com.fbsdata.flexmls_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client implements HttpActions<Response> {

	Configuration config = null;
	Connection<Response> connection = null;
	Connection<Response> secure = null;
	Session session = null;
	
	public Client(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super();
		this.config = config;
		secure = secureConnection;
		connection = defaultConnection;
	}
	public Client(Configuration config) {
		super();
		this.config = config;
		secure = new ConnectionApacheHttps(config);
		connection = config.isSsl() ? secure : new ConnectionApacheHttp(config);
	}

	@Override
	public Response get(String path, Map<String, String> options) throws FlexmlsApiClientException {
		String signedPath = signToken(path, options, "");
		log("GET", signedPath);
		return connection.get(signedPath);
	}

	@Override
	public Response post(String path, String body, Map<String, String> options) throws FlexmlsApiClientException {
		String signedPath = signToken(path, options, body);
		log("POST", signedPath);
		return connection.post(signedPath, body);
	}

	@Override
	public Response put(String path, String body, Map<String, String> options) throws FlexmlsApiClientException {
		String signedPath = signToken(path, options, body);
		log("PUT", signedPath);
		return connection.put(signedPath, body);
	}

	@Override
	public Response delete(String path, Map<String, String> options) throws FlexmlsApiClientException {
		String signedPath = signToken(path, options, "");
		log("DELETE", signedPath);
		return connection.delete(signedPath);
	}
	
	private void log(String action, String path){
		System.out.println("Request: [" + action + "] - " + path);
	}
	
	Session authenticate() throws FlexmlsApiClientException {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		String signature = sign(b.toString());
		String path = authPath(signature);
		log("post", path);
		Response response = secure.post(path,"");
		List<Session> sessions = response.getResults(Session.class);
		if(sessions.isEmpty()){
			throw new FlexmlsApiClientException("Service error.  No session returned for service authentication.");
		}
		session = sessions.get(0);
		return session;
	}
	
	private String authPath(String sig){
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append("/session?");
		b.append("ApiKey=").append(config.getApiKey());
		b.append("&ApiSig=").append(sig);
		return b.toString();
	}
	
	protected Map<String,String> sessionParams(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ApiKey", config.getApiKey());
		params.put("AuthToken", session.getToken());
		return params;
	}
	
	protected String requestPath(String path, Map<String, String> params){
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append(path).append("?");
		for (String key : params.keySet()) {
			b.append(key).append("=").append(params.get(key));
		}
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
