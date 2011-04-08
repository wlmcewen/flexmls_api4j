package com.fbsdata.flexmls_api.services;

import java.util.HashMap;

import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.Response;
import com.fbsdata.flexmls_api.models.ExampleModel;

public class ExampleService extends BaseService {
	static final String PATH = "/test";
	
	public ExampleModel get(String id) throws FlexmlsApiClientException{
		Client c = getClient();
		Response r = c.get(PATH + "/" + id, new HashMap<String, String>());
		return r.getResults(ExampleModel.class).get(0);
	}

}
