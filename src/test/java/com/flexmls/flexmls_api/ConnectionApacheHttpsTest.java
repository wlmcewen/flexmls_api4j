package com.flexmls.flexmls_api;


import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.ConnectionApacheHttps;

public class ConnectionApacheHttpsTest {
	
	ConnectionApacheHttps c = new ConnectionApacheHttps(new Configuration());

	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}

}
