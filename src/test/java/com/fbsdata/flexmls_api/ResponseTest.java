package com.fbsdata.flexmls_api;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;


public class ResponseTest {
	private static final String RESPONSE = "{\"D\":{\"Success\": true,\"Results\":[]}}";

	@Before 
	public void setup(){
	}
	
	@Test
	public void testGet() throws JsonParseException, JsonMappingException, IOException {
		Response<Object> r = Response.parse(RESPONSE, Object.class);
		System.out.println("R " + r.toString());
		JsonNode n = null;
	}

}
