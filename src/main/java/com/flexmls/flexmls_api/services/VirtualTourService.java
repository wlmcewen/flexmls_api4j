package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.VirtualTour;

public class VirtualTourService extends SubResourceService<VirtualTour> {
	
	public VirtualTourService(Client c, String prefix) {
		super(c,prefix);
	}

	@Override
	public String getSubResourcePath() {
		return "/virtualtours";
	}

	
}
