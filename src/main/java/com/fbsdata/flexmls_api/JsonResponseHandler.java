package com.fbsdata.flexmls_api;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonResponseHandler implements ResponseHandler<Response> {
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public Response handleResponse(HttpResponse response) {
		JsonNode root;
		Response r = null;
		try {
			root = mapper.readValue(response.getEntity().getContent(), JsonNode.class);
			r = parse(root, response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			r = new Response(new FlexmlsApiClientException("Failure parsing JSON resonse.  The server response may be invalid", e));
		}
		return r;
	}
	
	private Response parse(JsonNode root, int status){
		 // can reuse, share globally
		JsonNode rootNode = root.get("D");
		Response r = new Response(mapper, rootNode);
		r.setSuccess(rootNode.get("Success").getValueAsBoolean());
		if(!r.isSuccess()){
			r.setCode(rootNode.get("Code").getValueAsInt());
			r.setMessage(rootNode.get("Message").getValueAsText());
		}
		// TODO pagination
		return r;		
	}
}
