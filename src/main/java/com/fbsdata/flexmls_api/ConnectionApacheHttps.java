package com.fbsdata.flexmls_api;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class ConnectionApacheHttps extends ConnectionApacheHttp {

	public ConnectionApacheHttps(Configuration config) {
		super(config);
	}

	@Override
	public void reset() {
		super.reset();
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,new TrustManager[]{new FullTrustManager()},null);
			SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
			Scheme https = new Scheme("https", 443, sf);
			client.getConnectionManager().getSchemeRegistry().register(https);
			host = new HttpHost(config.getEndpoint(), 443, "https");
		} catch (Exception e) {
			System.out.println("Failed to setup SSL authentication for the client (https disabled):  " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Basic trust manager that accepts everyone.  This should be scoped to the appropriate certificates.
	 * 
	 * TODO Restrict trusted certificates and issuers.
	 *
	 */
	public static class FullTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
	
}
