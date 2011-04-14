package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.Video;

public class VideoService extends SubResourceService<Video> {
	
	public VideoService(Client c, String prefix) {
		super(c,prefix);
	}

	@Override
	public String getSubResourcePath() {
		return "/videos";
	}

	
}
