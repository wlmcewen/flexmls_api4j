package com.flexmls.flexmls_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Standard API response object.  All requests should return JSON responses that are supported by 
 * this format.
 *
 */
public class Response {
	private ObjectMapper mapper = new ObjectMapper();
	private int code;
	private int status;
	private String message;
	private boolean success;
	private JsonNode rootNode;
	private FlexmlsApiClientException exception;
	
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
	
	/**
	 * Results list.  Returns instances of the service's model type
	 * @param <T>
	 * @param resultClass
	 * @return
	 * @throws FlexmlsApiClientException
	 */
	public <T> List<T> getResults(Class<T> resultClass) throws FlexmlsApiClientException {
		try {
			JsonNode results = rootNode.get("Results");
			List<T> r = new ArrayList<T>(); 
			if(!results.isArray()){
				throw new JsonMappingException("The JSON returned is missing a results array, which is expected on all api responses.");
			}
			for (int i = 0; i < results.size(); i++) {
				JsonNode n = results.get(0);
				T result = mapper.readValue(n, resultClass);
				r.add(result);
			}
			return r;
		} catch (IOException e) {
			throw new FlexmlsApiClientException("Failure parsing JSON resonse.  The server response may be invalid", e);
		}
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void checkFailures() throws FlexmlsApiClientException {
		if(exception != null){
			throw exception;
		}
		if(!isSuccess()){
			throw new FlexmlsApiException(getMessage(), getCode(), getStatus());
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
