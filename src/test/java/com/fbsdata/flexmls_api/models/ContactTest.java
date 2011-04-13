package com.fbsdata.flexmls_api.models;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.fbsdata.flexmls_api.ApiParameter;
import com.fbsdata.flexmls_api.Configuration;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.MockClient;
import com.fbsdata.flexmls_api.Response;
import com.fbsdata.flexmls_api.services.ContactService;

public class ContactTest {
	MockClient c;
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet("/contacts", "contacts.json", 200);
		Response r = c.get("/contacts", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		Contact m = r.getResults(Contact.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet("/contacts", "contacts.json", 200);
		ContactService s = new ContactService(c);
		Contact m = s.find().get(0);
		validate(m);
	}
	private void validate(Contact m){
		assertEquals("20101230223226074201000000", m.getId());
		assertEquals("contact1@fbsdata.com", m.getPrimaryEmail());
		assertEquals("Contact One", m.getDisplayName());
		assertEquals(0, m.getAttributes().size());
	}
}
