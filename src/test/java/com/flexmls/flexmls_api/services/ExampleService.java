package com.flexmls.flexmls_api.services;

import java.util.HashMap;

import com.flexmls.flexmls_api.ApiParameter;
import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.Response;
import com.flexmls.flexmls_api.models.ExampleModel;
import com.flexmls.flexmls_api.services.BaseService;

public class ExampleService extends BaseService<ExampleModel> {
	static final String PATH = "/test";
	
	public ExampleService(Client c) {
		super(c);
	}

	public ExampleModel get(String id) throws FlexmlsApiClientException{
		Client c = getClient();
		Response r = c.get(PATH + "/" + id, new HashMap<ApiParameter, String>());
		return r.getResults(ExampleModel.class).get(0);
	}

	@Override
	public String getPath() {
		return PATH;
	}

}
