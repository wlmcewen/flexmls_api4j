package com.fbsdata.flexmls_api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class ConnectionApacheHttp extends Connection<Response> {
	
	protected HttpClient client;
	protected Configuration config;
	protected HttpHost host;
	private ResponseHandler<Response> handler;
	
	public ConnectionApacheHttp(Configuration config) {
		this.config = config;
		reset();
	}
	
	@Override
	public Response get(String path, Map<String, String> options) throws FlexmlsApiClientException {
		HttpRequest r = new HttpGet(path);
		return request(r);
	}
	@Override
	public Response delete(String path, Map<String, String> options)
			throws FlexmlsApiClientException {
		HttpRequest r = new HttpDelete(path);
		return request(r);
	}

	@Override
	public Response post(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		HttpPost r = new HttpPost(path);
		setData(r, body);
		return request(r);
	}
	
	@Override
	public Response put(String path, String body, Map<String, String> options)
			throws FlexmlsApiClientException {
		HttpPut r = new HttpPut(path);
		setData(r, body);
		return request(r);
	}
	
	private static void setData(HttpEntityEnclosingRequestBase r, String body) throws FlexmlsApiClientException{
		HttpEntity data;
		try {
			data = new StringEntity(body);
			r.setEntity(data);
		} catch (UnsupportedEncodingException e) {
			throw new FlexmlsApiClientException("Message cannot be sent as the body is encoded in an unsupported format.", e);
		} 
	}

	protected Response request(HttpRequest r) throws FlexmlsApiClientException {
		Response rs = null;
		try {
			rs = client.execute(host, r, handler);
		} catch (Exception e) {
			throw new FlexmlsApiClientException("Connection failure with HTTP.  Check your url settings.", e);
		}
		rs.checkFailures();
		return rs;
	}
	
	public void reset() {
		client = new DefaultHttpClient();
		host = new HttpHost(config.getEndpoint());
		handler = new JsonResponseHandler();
	}
	
}
