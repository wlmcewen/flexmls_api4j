package com.fbsdata.flexmls_api.services;

import java.util.HashMap;

import com.fbsdata.flexmls_api.ApiParameter;
import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.Response;
import com.fbsdata.flexmls_api.models.ExampleModel;

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
	protected String getPath() {
		return PATH;
	}

}
