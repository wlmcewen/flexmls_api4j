package com.fbsdata.flexmls_api.models;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;

/**
 * Top level model for managing JSON entities in the library.
 * 
 */
public class Base {
	private static final Logger logger = Logger.getLogger(Base.class);
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	@JsonAnySetter
	public void setAttribute(String key, Object value){
		if(logger.isDebugEnabled()){
			logger.debug("Added attribute: "  + key);
		}
		attributes.put(key, value);
	}

	@JsonAnyGetter
	public Object getAttribute(String key){
		return attributes.get(key);
	}

	@JsonAnyGetter
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	
}
