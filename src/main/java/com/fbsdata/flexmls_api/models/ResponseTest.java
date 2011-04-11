package com.fbsdata.flexmls_api.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.FlexmlsApiException;
import com.fbsdata.flexmls_api.PropertyAsserter;
import com.fbsdata.flexmls_api.Response;

public class ResponseTest {
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Response(new FlexmlsApiClientException("")));
	}

	@Test
	public void testCheckFailures() throws FlexmlsApiClientException {
		// Sky is falling response 
		FlexmlsApiClientException exception = new FlexmlsApiClientException("This is an error!");
		Response r = new Response(exception);
		try {
			r.checkFailures();
			fail("Failures were expected");
		} catch (FlexmlsApiClientException e) {
			assertEquals(exception, e);
		}
		// API ERROR Response
		Response y = new Response(null, null);
		y.setCode(1000);
		y.setMessage("API ERROR");
		y.setStatus(500);
		try {
			y.checkFailures();
			fail("Failures were expected");
		} catch (FlexmlsApiException e) {
			assertEquals(1000, e.getCode());
			assertEquals(500, e.getStatus());
			assertEquals("API ERROR", e.getMessage());
		}
		// Success
		Response z = new Response(null, null);
		z.setSuccess(true);
		z.checkFailures();
	}

}
