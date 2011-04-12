package com.fbsdata.flexmls_api;

import java.util.HashMap;
import java.util.Map;

public class Client extends BaseClient<ApiParameter> {
	public Client(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super();
		this.config = config;
		secure = secureConnection;
		connection = defaultConnection;
	}
	public Client(Configuration config) {
		super();
		this.config = config;
		secure = new ConnectionApacheHttps(config);
		connection = config.isSsl() ? secure : new ConnectionApacheHttp(config);
	}
	
	@Override
	Map<String, String> stringifyParameterKeys(Map<ApiParameter, String> parms) {
		Map<String, String> strings = new HashMap<String, String>();
		for (ApiParameter parm : parms.keySet()) {
			strings.put(parm.getCode(), parms.get(parm));
		}
		return strings;
	}
}
