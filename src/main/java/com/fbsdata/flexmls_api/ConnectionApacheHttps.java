package com.fbsdata.flexmls_api;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.log4j.Logger;


public class ConnectionApacheHttps extends ConnectionApacheHttp {
	private static final int SSL_PORT = 443;
	private static Logger logger = Logger.getLogger(ConnectionApacheHttps.class);

	public ConnectionApacheHttps(Configuration config) {
		super(config);
		resetChild();
	}

	@Override
	protected final void resetChild() {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,new TrustManager[]{new FullTrustManager()},null);
			SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
			Scheme https = new Scheme("https", SSL_PORT, sf);
			getClient().getConnectionManager().getSchemeRegistry().register(https);
			setHost(new HttpHost(getConfig().getEndpoint(), SSL_PORT, "https"));
		} catch (Exception e) {
			logger.error("Failed to setup SSL authentication for the client (https disabled).", e);
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
