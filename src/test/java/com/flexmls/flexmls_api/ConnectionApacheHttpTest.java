package com.flexmls.flexmls_api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.Before;
import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.ConnectionApacheHttp;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.JsonResponseHandler;
import com.flexmls.flexmls_api.Response;

public class ConnectionApacheHttpTest {
	ConnectionApacheHttp c;
	
	@Before
	public void setup(){
		c = new ConnectionApacheHttp(new Configuration());
	}	
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
	
	@Test
	public void get() throws IOException, FlexmlsApiClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpGet.class);
		Response rs2 = c.get("TESTGET");
		assertEquals(rs2, rs);
	}

	@Test
	public void delete() throws IOException, FlexmlsApiClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpDelete.class);
		Response rs2 = c.delete("TESTDELETE");
		assertEquals(rs2, rs);
	}

	@Test
	public void post() throws IOException, FlexmlsApiClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpPost.class);
		Response rs2 = c.post("TESTPOST", "data");
		assertEquals(rs2, rs);
	}

	@Test
	public void put() throws IOException, FlexmlsApiClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpPut.class);
		Response rs2 = c.put("TESTPUT", "data");
		assertEquals(rs2, rs);
	}

	@Test
	public void failure() throws IOException, FlexmlsApiClientException{
		FlexmlsApiClientException ex = new FlexmlsApiClientException("Something blew up");
		Response rs = new Response(ex);
		rs.setSuccess(false);
		mockupHttpClient(rs, HttpPut.class);
		try {
			c.get("TEST");
			fail("exception expected");
		} catch (FlexmlsApiClientException e) {
			assertEquals(ex, e);
		}
	}
	
	@Test
	public void reset() throws IOException, FlexmlsApiClientException{
		c.setClient(null);
		c.reset();
		assertNotNull(c.getClient());
	}

	private <T extends HttpRequest> void mockupHttpClient(Response rs, Class<T> klass) throws IOException {
		HttpClient hc = mock(HttpClient.class);
		HttpHost h = new HttpHost("MY.TEST.SERVER.FBSDATA.COM");
		c.setClient(hc);
		c.setHost(h);
		when(hc.execute(eq(h), any(klass), any(JsonResponseHandler.class))).thenReturn(rs);
	}

}
