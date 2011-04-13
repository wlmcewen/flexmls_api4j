package com.flexmls.flexmls_api;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.ConnectionApacheHttps;

public class ConnectionApacheHttpsTest {
	
	ConnectionApacheHttps c = new ConnectionApacheHttps(new Configuration());

	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
	
	@Test
	public void reset() throws IOException, FlexmlsApiClientException{
		c.setClient(null);
		c.setHost(null);
		c.reset();
		assertNotNull(c.getClient());
		assertNotNull(c.getHost());
		assertEquals(443, c.getHost().getPort());
	}

}
