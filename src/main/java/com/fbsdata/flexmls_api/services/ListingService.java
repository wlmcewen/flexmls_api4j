package com.fbsdata.flexmls_api.services; 

import java.util.List;
import java.util.Map;

import com.fbsdata.flexmls_api.ApiParameter;
import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.models.Listing;

public class ListingService extends BaseService<Listing> {

	@Override
	protected String getPath() {
		return "/listings";
	}

	public ListingService(Client c) {
		super(c);
	}
	
	public List<Listing> my() throws FlexmlsApiClientException {
		return my(EMPTY);
	}
	public List<Listing> my(Map<ApiParameter, String> opts) throws FlexmlsApiClientException {
		return getClient().get("/my" + getPath(), opts).getResults(model());
	}
	
}
