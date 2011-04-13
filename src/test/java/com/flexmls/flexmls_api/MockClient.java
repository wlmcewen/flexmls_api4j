package com.flexmls.flexmls_api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.Configuration;
import com.flexmls.flexmls_api.Connection;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.Response;
import com.flexmls.flexmls_api.Session;


public class MockClient extends Client {
	
	private MockConnection con;
	
	private MockClient(Configuration config,
			Connection<Response> defaultConnection,
			Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}

	public static void mock(){
		
	}

	public static MockClient mock(Configuration cf) throws FlexmlsApiClientException {
		MockConnection cn = new MockConnection();
		MockClient c = new MockClient(cf, cn, cn);
		c.con = cn;
		c.reauth();
		return c;
	}
	
	public List<String> getRoles(){
		List<String> l = new ArrayList<String>();
		l.add("idx");
		return l;
	}
	
	@Override
	Session authenticate() throws FlexmlsApiClientException {
		Session s = new Session("FAKE_TOKEN", getRoles(), new Date(new Date().getTime() + 300000));
		setSession(s);
		return s;
	}
	
	@Override
	protected String signToken(String path, Map<String, String> options,
			String body) {
		return "FAKE_SIG";
	}

	public void stubGet(String path, String fixture, int status) throws FlexmlsApiClientException {
		String s = setupRequest(path, "", new HashMap<String, String>());
		con.stubGet(s, fixture, status);
	}
	
	public void stubPost(String path, String body, String fixture, int status) throws FlexmlsApiClientException {
		
	}

	public void stubDelete(String path, String fixture, int status) throws FlexmlsApiClientException {
		
	}
	public void stubPut(String path, String body, String fixture, int status) throws FlexmlsApiClientException {
		
	}

}
