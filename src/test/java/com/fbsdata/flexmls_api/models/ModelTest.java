package com.fbsdata.flexmls_api.models;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.fbsdata.flexmls_api.Configuration;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.MockClient;
import com.fbsdata.flexmls_api.Response;

public class ModelTest {
	MockClient c;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet("/test", "test.json", 200);
		
		Response r = c.get("/test", new HashMap<String, String>());
		assertNotNull(r);
		
		
	}

}
