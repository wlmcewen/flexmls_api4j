package com.fbsdata.flexmls_api;

import static org.junit.Assert.*;


import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	Client c = null;
	Configuration config = new Configuration();
	Map<String, String> sample = new HashMap<String, String>();

	@Before 
	public void setup(){
		config.setApiKey("MyKey");
		config.setApiSecret("password");
		config.setEndpoint("http://testapi.flexlms.com");
		c = new Client(config);
		sample = new HashMap<String, String>();
		sample.put("Optimus",   "semi");
		sample.put("Soundwave", "walkman");
		sample.put("Starscream", "F-15 Eagle");
		sample.put("Megatron",  "Walther P-38");
	}
	
	
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testPost() {
		fail("Not yet implemented");
	}

	@Test
	public void testPut() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuthenticate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSign() {
		assertEquals("b6799a05f2da802bbac47e790cc42183", c.sign("SignMePlz"));
	}

	@Test
	public void testSignToken() {
		assertEquals("ff700e42091375c44c0881963b64cbb0", c.signToken("/path", sample, ""));
	}

	@Test
	public void testBuildParamString() {
		assertEquals("MegatronWalther P-38OptimussemiSoundwavewalkmanStarscreamF-15 Eagle", c.buildParamString(sample));
	}
	
}