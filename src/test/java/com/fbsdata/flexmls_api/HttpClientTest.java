package com.fbsdata.flexmls_api;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class HttpClientTest {
	
	@Test
	public void testGet() throws IOException{
		HttpClient c = new DefaultHttpClient();
		HttpHost h = new HttpHost("frink.fbsdata.com");
		
		HttpRequest r = new HttpGet("/~wade/");
		HttpResponse rs = c.execute(h,r);
		assertEquals(200, rs.getStatusLine().getStatusCode());
		
		assertTrue(readString(rs.getEntity().getContent()).contains("KaPOW!"));
		
	}
	
	@Test
	public void testSSL() throws Exception {
		
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null,new TrustManager[]{new ConnectionApacheHttps.FullTrustManager()},null);
		HttpClient c = new DefaultHttpClient();
		SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
		Scheme https = new Scheme("https", 443, sf);
		c.getConnectionManager().getSchemeRegistry().register(https);
		HttpHost h = new HttpHost("api.flexmls.com", 443, "https");
		
		HttpRequest r = new HttpGet("/v1/");
		HttpResponse rs = c.execute(h,r);
		
		assertEquals(404, rs.getStatusLine().getStatusCode());
		String s = readString(rs.getEntity().getContent());
		assertEquals(s, "{\"D\":{\"Success\":false,\"Code\":404,\"Message\":\"Not Found\"}}");
	}
	
	private String readString(InputStream is) throws IOException{
		StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(is));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
	}
	


}
