package com.fbsdata.flexmls_api;

import static org.junit.Assert.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fbsdata.flexmls_api.models.ExampleModel;

public class ClientTest {
	Client c = null;
	Configuration config = new Configuration();
	Map<String, String> sample = new HashMap<String, String>();
	MockConnection conn;

	@Before
	public void setup(){
		config.setApiKey("MyKey");
		config.setApiSecret("password");
		config.setEndpoint("http://testapi.flexlms.com");
		conn = new MockConnection();
		c = new Client(config, conn, conn);
		sample = new HashMap<String, String>();
		sample.put("Optimus",   "semi");
		sample.put("Soundwave", "walkman");
		sample.put("Starscream", "F-15 Eagle");
		sample.put("Megatron",  "Walther P-38");
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		mockAuth();
		conn.stubGet(
			"/v1/test?ApiSig=9debfcde9289ab5fc1b1eb689b06b041&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15 Eagle&Megatron=Walther P-38&Optimus=semi",
			"test.json", 
			200);
		Response r = c.get("/test", sample);
		assertTrue(r.isSuccess());
		List<ExampleModel> models = r.getResults(ExampleModel.class);
		assertEquals(3, models.size());
		ExampleModel myFoo = models.get(0);
		assertEquals("MyFoo", myFoo.getFoo());
		assertEquals(1, myFoo.getBar());
	}

	@Test
	public void testPost() throws FlexmlsApiClientException {
		mockAuth();
		conn.stubPost(
			"/v1/test?ApiSig=d654d1f48499143e80b8208d45b9b25e&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15 Eagle&Megatron=Walther P-38&Optimus=semi",
			"foo=Test&bar=10",
			"success.json",
			201);
		Response r = c.post("/test", "foo=Test&bar=10", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testPut() throws FlexmlsApiClientException {
		mockAuth();
		conn.stubPut(
				"/v1/test/1234?ApiSig=d4c79156866d1db5b1ca52149ef1699b&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15 Eagle&Megatron=Walther P-38&Optimus=semi",
				"foo=Test&bar=10",
				"success.json",
				201);
		Response r = c.put("/test/1234", "foo=Test&bar=10", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testDelete() throws FlexmlsApiClientException {
		mockAuth();
		conn.stubDelete(
				"/v1/test/1234?ApiSig=0ef980e9b7523a691b6ea5bcd8208486&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15 Eagle&Megatron=Walther P-38&Optimus=semi",
				"success.json", 
				200);
		Response r = c.delete("/test/1234", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testAuthenticate() throws FlexmlsApiClientException {
		mockAuth();
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
	
	private void mockAuth() throws FlexmlsApiClientException{
		conn.stubPost("/v1/session?ApiKey=MyKey&ApiSig=9526c9b45b579ffb67facafa52351ec9","", "session.json", 200);
	}
	
}