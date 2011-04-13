package com.flexmls.flexmls_api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {
	// default configuration file
	private static final String PROPERTIES = "flexmls_api.properties";

	public enum ENV {
		API_KEY,
		API_SECRET,
		API_USER,
		ENDPOINT,
		SSL,
		VERSION;
	}
	
	private String apiSecret;
	private String apiKey;
	private String apiUser;
	private String endpoint = "api.flexmls.com";
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
	private static Logger logger = Logger.getLogger(Configuration.class);
	public static void loadFromEnvironment(Configuration c){
		setup(System.getenv(), c);
	}
	
	public static void loadFromProperties(Configuration c){
		loadFromProperties(c, new File(PROPERTIES));
	}
	public static void loadFromProperties(Configuration c, File f){
		Properties properties = new Properties();
		try {
			if(f.exists() && f.isFile()){
				logger.debug("Loading configuration from properties file... " + f.getPath());
			    properties.load(new FileInputStream(f));
				logger.debug("Found properties: " + properties.toString());
				loadFromProperties(c,properties);
			}
		} catch (IOException e) {
			logger.warn("Unable to read properties file '" + f.getPath() + "', skipping.", e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadFromProperties(Configuration c, Properties properties){
		setup((Map)properties, c);
	}
	
	private static void setup(Map<String,String> map, Configuration c){
		if(map.containsKey(ENV.API_KEY.toString())){
			c.setApiKey(map.get(ENV.API_KEY.toString()));
		}
		if(map.containsKey(ENV.API_USER.toString())){
			c.setApiUser(map.get(ENV.API_USER.toString()));
		}
		if(map.containsKey(ENV.API_SECRET.toString())){
			c.setApiSecret(map.get(ENV.API_SECRET.toString()));
		}
		if(map.containsKey(ENV.ENDPOINT.toString())){
			c.setEndpoint(map.get(ENV.ENDPOINT.toString()));
		}
		if(map.containsKey(ENV.SSL.toString())){
			c.setSsl("true".equalsIgnoreCase(map.get(ENV.SSL.toString())));
		}
		if(map.containsKey(ENV.VERSION.toString())){
			c.setVersion(map.get(ENV.VERSION.toString()));
		}
	}
	
	public static Configuration load(){
		Configuration c = new Configuration();
		loadFromProperties(c);
		loadFromEnvironment(c);
		return c;
	}
	
}
