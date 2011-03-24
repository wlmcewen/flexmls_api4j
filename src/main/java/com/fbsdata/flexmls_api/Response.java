package com.fbsdata.flexmls_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Response <T> {
	
	int code;
	String message;
	List<T> results = new ArrayList<T>();
	boolean success;
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
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public static <T> Response<T> parse(String json, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
		Response<T> r = new Response<T>();
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		JsonNode rootNode = mapper.readValue(json, JsonNode.class);
		rootNode = rootNode.get("D");
		r.setSuccess(rootNode.get("Success").getValueAsBoolean());
		if(!r.isSuccess()){
			r.setCode(rootNode.get("Code").getValueAsInt());
			r.setMessage(rootNode.get("Message").getValueAsText());
		}
		else {
			JsonNode results = rootNode.get("Results");
			if(!results.isArray())
				throw new JsonMappingException("This ain't no results array!");
			for (int i = 0; i < results.size(); i++) {
				JsonNode n = results.get(0);
				T result = mapper.readValue(n, resultClass);
				r.results.add(result);
			}
		}
		// TODO pagination
		return r;
	}

}
