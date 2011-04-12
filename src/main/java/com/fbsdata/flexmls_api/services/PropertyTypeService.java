package com.fbsdata.flexmls_api.services; 

import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.models.PropertyType;

public class PropertyTypeService extends BaseService<PropertyType> {

	@Override
	public String getPath() {
		return "/propertytypes";
	}

	public PropertyTypeService(Client c) {
		super(c);
	}
	
}
