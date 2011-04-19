package com.flexmls.flexmls_api.services; 

import java.util.HashMap;
import java.util.List;

import com.flexmls.flexmls_api.ApiParameter;
import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.FlexmlsApiClientException;
import com.flexmls.flexmls_api.models.PropertyType;
import com.flexmls.flexmls_api.models.StandardField;

public class StandardFieldService extends BaseService<StandardField> {

	@Override
	public String getPath() {
		return "/standardfields";
	}

	public StandardFieldService(Client c) {
		super(c);
	}
	public List<StandardField> nearby(String latititude, String longitued, String expand, PropertyType first, PropertyType ... types) throws FlexmlsApiClientException{
		StringBuffer buffer = new StringBuffer(getPath()).append("/nearby/").append(first.getMlsCode());
		for (PropertyType propertyType : types) {
			buffer.append(",").append(propertyType.getMlsCode());
		}
		return getClient().get(buffer.toString(), new HashMap<ApiParameter, String>()).getResults(StandardField.class);
	}
	
}
