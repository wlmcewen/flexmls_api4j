package com.fbsdata.flexmls_api;


import org.junit.Test;

public class ConnectionApacheHttpTest {
	
	ConnectionApacheHttp c = new ConnectionApacheHttp(new Configuration());

	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}

}
