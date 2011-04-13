package com.flexmls.flexmls_api;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.flexmls.flexmls_api.Configuration;

public class ConfigurationTest {
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Configuration());
	}
	
	@Test
	public void testLoad(){
		Configuration c = Configuration.load();
		assertEquals("v1", c.getVersion());
		assertNull(c.getApiSecret());
		assertNull(c.getApiUser());
	}
	
	@Test
	public void testLoadFromProperties(){
		Configuration c = new Configuration();
		Configuration.loadFromProperties(c, new File("src/test/resources/test_flexmls_api.properties"));
		assertEquals("v1", c.getVersion());
		assertEquals("PASSWORDZ",c.getApiSecret());
		assertEquals("10000000000000000000000000",c.getApiUser());
		assertEquals("sample_key", c.getApiKey());
		assertEquals("test.flexmls.com", c.getEndpoint());
		assertTrue(c.isSsl());

		Configuration badconfig = new Configuration();
		Configuration.loadFromProperties(badconfig, new File("not_a_file"));
		assertFalse(badconfig.isSsl());
		assertNull(badconfig.getApiSecret());
	}
}