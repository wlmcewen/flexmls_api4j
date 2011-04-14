package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.OpenHouse;

public class OpenHouseService extends SubResourceService<OpenHouse> {
	
	public OpenHouseService(Client c, String prefix) {
		super(c,prefix);
	}

	@Override
	public String getSubResourcePath() {
		return "/openhouses";
	}

	
}
