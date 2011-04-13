package com.flexmls.flexmls_api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flexmls.flexmls_api.Connection;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.JsonResponseHandler;
import com.flexmls.flexmls_api.Response;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class MockConnection extends Connection<Response> {
	private static final Logger logger = Logger.getLogger(MockConnection.class);

	JsonResponseHandler parser = new JsonResponseHandler();
	Connection<Response> c;
	
	public MockConnection() {
		c = mock(Connection.class);
	}
	
	public void stubGet(String path, String fixture, int status) throws FlexmlsApiClientException {
		logger.debug("STUBBED: " + path);
		Response r = parseFile(fixture, status);
		when(c.get(path)).thenReturn(r);
	}
	public void stubPost(String path, String body, String fixture, int status) throws FlexmlsApiClientException {
		Response r = parseFile(fixture, status);
		when(c.post(path, body)).thenReturn(r);
	}

	public void stubDelete(String path, String fixture, int status) throws FlexmlsApiClientException {
		Response r = parseFile(fixture, status);
		when(c.delete(path)).thenReturn(r);
	}
	public void stubPut(String path, String body, String fixture, int status) throws FlexmlsApiClientException {
		Response r = parseFile(fixture, status);
		when(c.put(path, body)).thenReturn(r);
	}
	
	private Response parseFile(String fixture, int status) throws FlexmlsApiClientException {
		try {
			File f = new File("src/test/fixtures/" + fixture);
			return parser.parseResponse(new FileInputStream(f), status);
		} catch (IOException e) {
			throw new FlexmlsApiClientException("Mock test failed to find json file " + fixture);
		}
	}

	@Override
	public Response get(String path, Map<String, String> options)
			throws FlexmlsApiClientException {
		return c.get(path);
	}

	@Override
	public Response post(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return c.post(path, body);
	}

	@Override
	public Response put(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return c.put(path, body);
	}

	@Override
	public Response delete(String path, Map<String, String> options)
			throws FlexmlsApiClientException {
		// TODO Auto-generated method stub
		return c.delete(path);
	}
	
}
