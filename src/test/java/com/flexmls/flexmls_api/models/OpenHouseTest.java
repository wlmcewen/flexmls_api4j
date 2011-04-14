package com.flexmls.flexmls_api.models;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.MockClient;
import com.flexmls.flexmls_api.PropertyAsserter;
import com.flexmls.flexmls_api.services.ListingService;
import com.flexmls.flexmls_api.services.OpenHouseService;


public class OpenHouseTest {

	MockClient c;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new OpenHouse());
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_openhouses.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		OpenHouse d = l.getStandardFields().getOpenHouses().get(0);
		assertEquals("20101127153422574618000000", d.getId());
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet("/listings/20060412165917817933000000/openhouses", "listing_open_houses_index.json", 200);
		OpenHouseService s = new OpenHouseService(c, "/listings/20060412165917817933000000");
		OpenHouse o = s.find().get(0);
		assertEquals("20101127153422574618000000", o.getId());
		assertTrue(o.getStartTime().before(o.getEndTime()));
	}

}
