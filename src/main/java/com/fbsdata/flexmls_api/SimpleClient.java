package com.fbsdata.flexmls_api;

import java.util.HashMap;
import java.util.Map;

/**
 * Unrestricted client for doing more experimental queries.  This client is unsupported by the 
 * services, but can be used for hitting the services in a more scripted manner.
 */
public class SimpleClient extends BaseClient<String> {
	public SimpleClient(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super();
		this.config = config;
		secure = secureConnection;
		connection = defaultConnection;
	}
	public SimpleClient(Configuration config) {
		super();
		this.config = config;
		secure = new ConnectionApacheHttps(config);
		connection = config.isSsl() ? secure : new ConnectionApacheHttp(config);
	}
	
	@Override
	Map<String, String> stringifyParameterKeys(Map<String, String> parms) {
		return new HashMap<String, String>(parms);
	}
	
}
