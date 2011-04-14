package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.Photo;

public class PhotoService extends SubResourceService<Photo> {
	
	public PhotoService(Client c, String prefix) {
		super(c,prefix);
	}

	@Override
	public String getSubResourcePath() {
		return "/photos";
	}

	
}
