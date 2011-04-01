package com.fbsdata.flexmls_api;

import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class ClientApacheHttp extends Connection<Response> {
	
	@Override
	public Object post(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		throw new FlexmlsApiClientException("Not Implemented.");
	}

	@Override
	public Object put(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		throw new FlexmlsApiClientException("Not Implemented.");
	}

	@Override
	public Object delete(String path, Map<String, String> options)
			throws FlexmlsApiClientException {
		throw new FlexmlsApiClientException("Not Implemented.");
	}

	HttpClient client;
	Configuration config;
	private ResponseHandler<Response> handler;
	
	public ClientApacheHttp(Configuration config) {
		reset();
	}
	
	@Override
	public Response get(String path, Map<String, String> options) throws FlexmlsApiClientException {
		HttpRequest r = new HttpGet(path);
		return request(r);
	}

	protected Response request(HttpRequest r) throws FlexmlsApiClientException {
		HttpHost host = new HttpHost(config.getEndpoint());
		Response rs = null;
		try {
			rs = client.execute(host, r, handler);
		} catch (Exception e) {
			throw new FlexmlsApiClientException("Connection failure with HTTP.  Check your url settings.", e);
		}
		rs.checkFailures();
		return rs;
	}
	
	public void reset(){
		client = new DefaultHttpClient();
		handler = new JsonResponseHandler();
	}

}
