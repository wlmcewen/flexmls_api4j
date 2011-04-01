package com.fbsdata.flexmls_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Response {
	ObjectMapper mapper = new ObjectMapper();
	int code;
	String message;
	boolean success;
	private JsonNode rootNode;
	FlexmlsApiClientException exception;
	
	public Response(FlexmlsApiClientException exception) {
		super();
		this.exception = exception;
	}

	public Response(ObjectMapper mapper,JsonNode rootNode) {
		super();
		this.mapper = mapper;
		this.rootNode = rootNode;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public <T> List<T> getResults(Class<T> resultClass) throws JsonParseException, IOException {
		JsonNode results = rootNode.get("Results");
		List<T> r = new ArrayList<T>(); 
		if(!results.isArray())
			throw new JsonMappingException("This ain't no results array!");
		for (int i = 0; i < results.size(); i++) {
			JsonNode n = results.get(0);
			T result = mapper.readValue(n, resultClass);
			r.add(result);
		}
		return r;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void checkFailures() throws FlexmlsApiClientException {
		if(exception != null)
			throw exception;
	}
	
}
