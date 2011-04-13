package com.flexmls.flexmls_api;

import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;

public class ConfigurationTest {
	Configuration c = new Configuration();
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
		
}