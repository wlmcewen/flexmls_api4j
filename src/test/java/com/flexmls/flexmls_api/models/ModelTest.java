package com.flexmls.flexmls_api.models;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.ApiParameter;
import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.MockClient;
import com.flexmls.flexmls_api.PropertyAsserter;
import com.flexmls.flexmls_api.Response;
import com.flexmls.flexmls_api.services.ExampleService;

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
		Response r = c.get("/test", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		List<ExampleModel> results = r.getResults(ExampleModel.class);
		ExampleModel m = results.get(0);
		assertEquals(1, m.getBar());
		ExampleModel m2 = results.get(1);
		assertEquals(2, m2.getBar());
		ExampleModel m3 = results.get(2);
		assertEquals(3, m3.getBar());
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet("/test", "test.json", 200);
		ExampleService s = new ExampleService(c);
		ExampleModel m = s.find().get(0);
		assertEquals(1, m.getBar());
	}
}
