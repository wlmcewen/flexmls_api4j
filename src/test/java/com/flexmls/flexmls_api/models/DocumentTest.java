package com.flexmls.flexmls_api.models;


import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.MockClient;
import com.flexmls.flexmls_api.PropertyAsserter;
import com.flexmls.flexmls_api.models.Listing;
import com.flexmls.flexmls_api.services.ListingService;


public class DocumentTest {

	MockClient c;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Document());
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_documents.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Document d = l.getStandardFields().getDocuments().get(0);
		assertEquals("20060725224801143085000000", d.getId());
	}

	@Test
	public void testService() throws FlexmlsApiClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_documents.json", 200);
		c.stubGet("/listings/20060725224713296297000000/documents", "listing_document_index.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Document d = s.getDocumentService(l).find().get(0);
		assertEquals("20060725224801143085000000", d.getId());
	}

}
