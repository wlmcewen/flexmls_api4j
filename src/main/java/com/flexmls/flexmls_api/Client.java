package com.flexmls.flexmls_api;

import java.util.HashMap;
import java.util.Map;

public class Client extends BaseClient<ApiParameter> {
	public Client(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}
	public Client(Configuration config) {
		super(config);
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
