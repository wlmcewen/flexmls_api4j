package com.fbsdata.flexmls_api.models;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.fbsdata.flexmls_api.Configuration;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.MockClient;
import com.fbsdata.flexmls_api.PropertyAsserter;
import com.fbsdata.flexmls_api.Response;
import com.fbsdata.flexmls_api.services.ExampleService;

public class ModelTest {
	MockClient c;
	
	@Test
	public void testProperties(){
		ExampleModel m = new ExampleModel();
		PropertyAsserter.assertBasicGetterSetterBehavior(m);
		m.setAttribute("FOO", "BAR");
		assertEquals("BAR", m.getAttribute("FOO"));
	}
	
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
		ExampleModel m = r.getResults(ExampleModel.class).get(0);
		assertEquals(1, m.getBar());
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet("/test", "test.json", 200);
		ExampleService s = new ExampleService(c);
		ExampleModel m = s.find().get(0);
		assertEquals(1, m.getBar());
	}
}
