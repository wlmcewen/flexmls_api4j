package com.fbsdata.flexmls_api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import static junit.framework.Assert.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
	
	/**
	 * Example ssl test that might be useful for debugging.
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public void testExampleSSL() throws Exception {
		
		ClassLoader cl = HttpClientTest.class.getClassLoader();
		URL url = cl.getResource("test.keystore");
		KeyStore keystore  = KeyStore.getInstance("jks");
		char[] pwd = "nopassword".toCharArray();
		keystore.load(url.openStream(), pwd);

		TrustManagerFactory tmf = TrustManagerFactory.getInstance(
		        TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keystore);
		TrustManager[] tm = tmf.getTrustManagers();

		KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(
		        KeyManagerFactory.getDefaultAlgorithm());
		kmfactory.init(keystore, pwd);
		KeyManager[] km = kmfactory.getKeyManagers();

		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(km, tm, null);

//		LocalTestServer localServer = new LocalTestServer(sslcontext);
//		localServer.registerDefaultHandlers();
//
//		localServer.start();
		try {

		    DefaultHttpClient httpclient = new DefaultHttpClient();
		    TrustStrategy trustStrategy = new TrustStrategy() {

		        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		            for (X509Certificate cert: chain) {
		            }
		            return false;
		        }

		    };

		    SSLSocketFactory sslsf = new SSLSocketFactory("TLS", null, null, keystore, null,
		            trustStrategy, new AllowAllHostnameVerifier());
		    Scheme https = new Scheme("https", 443, sslsf);
		    httpclient.getConnectionManager().getSchemeRegistry().register(https);

		    InetSocketAddress address = null; //localServer.getServiceAddress();
		    HttpHost target1 = new HttpHost(address.getHostName(), address.getPort(), "https");
		    HttpGet httpget1 = new HttpGet("/random/100");
		    HttpResponse response1 = httpclient.execute(target1, httpget1);
		    HttpEntity entity1 = response1.getEntity();
		    EntityUtils.consume(entity1);
		    HttpHost target2 = new HttpHost("www.verisign.com", 443, "https");
		    HttpGet httpget2 = new HttpGet("/");
		    HttpResponse response2 = httpclient.execute(target2, httpget2);
		    HttpEntity entity2 = response2.getEntity();
		    EntityUtils.consume(entity2);
		} finally {
//		    localServer.stop();
		}		
		
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
