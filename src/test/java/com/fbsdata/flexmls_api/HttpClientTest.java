package com.fbsdata.flexmls_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static junit.framework.Assert.*;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;


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
