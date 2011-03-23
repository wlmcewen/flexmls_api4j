package com.fbsdata.flexmls_api;

public class Configuration {
	
	private String apiSecret;
	private String apiKey;
	private String apiUser;
	private String endpoint = "http://api.flexmls.com";
	private String version = "v1";
	private boolean ssl;
	private String userAgent = "flexmls API Java client";
	
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiUser() {
		return apiUser;
	}
	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isSsl() {
		return ssl;
	}
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
}
