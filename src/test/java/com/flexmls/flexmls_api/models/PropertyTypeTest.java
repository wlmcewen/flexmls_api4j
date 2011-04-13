package com.flexmls.flexmls_api.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.ApiParameter;
import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.MockClient;
import com.flexmls.flexmls_api.PropertyAsserter;
import com.flexmls.flexmls_api.Response;
import com.flexmls.flexmls_api.models.PropertyType;
import com.flexmls.flexmls_api.services.PropertyTypeService;


public class PropertyTypeTest {

	private static final String JSON = "propertytypes.json";
	
	MockClient c;
	PropertyTypeService s;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new PropertyTypeService(c);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new PropertyType());
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet(s.getPath(), JSON, 200);
		Response r = c.get(s.getPath(), new HashMap<ApiParameter, String>());
		assertNotNull(r);
		PropertyType m = r.getResults(PropertyType.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet(s.getPath(), JSON, 200);
		PropertyType m = s.find().get(0);
		validate(m);
	}
	
	private void validate(PropertyType m){
		assertEquals("Residential", m.getMlsName());
		assertEquals("A", m.getMlsCode());
		assertEquals(0, m.getAttributes().size());
	}
}
