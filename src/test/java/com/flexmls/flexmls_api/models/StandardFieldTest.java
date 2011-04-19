package com.flexmls.flexmls_api.models;


import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.ApiParameter;
import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.MockClient;
import com.flexmls.flexmls_api.PropertyAsserter;
import com.flexmls.flexmls_api.Response;
import com.flexmls.flexmls_api.models.StandardField.Field;
import com.flexmls.flexmls_api.services.StandardFieldService;


public class StandardFieldTest {

	private static final String JSON = "standardfields.json";
	
	MockClient c;
	StandardFieldService s;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new StandardFieldService(c);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new SystemInfo());
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet(s.getPath(), JSON, 200);
		Response r = c.get(s.getPath(), new HashMap<ApiParameter, String>());
		assertNotNull(r);
		StandardField m = r.getResults(StandardField.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet(s.getPath(), JSON, 200);
		StandardField m = s.find().get(0);
		validate(m);
	}
	
	private void validate(StandardField m){
		Field city = m.getField("City");
		assertEquals("/v1/standardfields/City", city.getResourceUri());
		assertEquals(StandardField.Type.Character, city.getType());
		assertTrue("City is searchable", city.isSearchable());
		assertTrue("City has a list", city.isHasList());
		Field listPrice = m.getField("ListPrice");
		assertEquals("/v1/standardfields/ListPrice", listPrice.getResourceUri());
		assertEquals(StandardField.Type.Decimal, listPrice.getType());
		assertTrue("ListPrice is searchable", listPrice.isSearchable());
		assertFalse("ListPrice has no list", listPrice.isHasList());
	}
}
