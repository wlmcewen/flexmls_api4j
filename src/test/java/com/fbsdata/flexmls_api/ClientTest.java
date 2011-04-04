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
	public void testAuthenticate() throws FlexmlsApiClientException {
		MockConnection conn = new MockConnection();
		conn.stubPost("/v1/session?ApiKey=fvt_privfull_key&ApiSig=708dcf8ed16b997a7208dff6630709eb","", "session.json", 200);
		
		config.setApiKey("fvt_privfull_key");
		config.setApiSecret("TopSecret");
		config.setEndpoint("api.wade.dev.fbsdata.com");
		Client c = new Client(config,conn,conn);
		Session s = c.authenticate();
		assertNotNull("Session", s);
		assertEquals("c729d695fc1613af58de764fa44881cb", s.getToken());
		assertEquals("private_full", s.getRoles().get(0));
		// assertEquals(new Date(), s.getExpiration()); TODO Parse format string
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