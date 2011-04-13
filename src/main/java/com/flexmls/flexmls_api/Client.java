package com.flexmls.flexmls_api;

import java.util.HashMap;
import java.util.Map;

/**
 * Main API client interface.  This client is strictly set to use only parameters and feature set 
 * specified in the documentation.  An instance of this class is provided to all service 
 * implementations.
 * 
 * @see BaseClient
 */
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
			strings.put(parm.toString(), parms.get(parm));
		}
		return strings;
	}
}
