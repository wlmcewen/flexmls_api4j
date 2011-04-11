package com.fbsdata.flexmls_api;


import org.junit.Test;

public class ConnectionApacheHttpsTest {
	
	ConnectionApacheHttps c = new ConnectionApacheHttps(new Configuration());

	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}

}
