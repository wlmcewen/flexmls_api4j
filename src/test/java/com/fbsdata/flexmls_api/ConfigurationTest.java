package com.fbsdata.flexmls_api;

import org.junit.Test;

public class ConfigurationTest {
	Configuration c = new Configuration();
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
		
}