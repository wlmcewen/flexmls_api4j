package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.SystemInfo;

public class SystemInfoService extends BaseService<SystemInfo> {

	@Override
	public String getPath() {
		return "/system";
	}

	public SystemInfoService(Client c) {
		super(c);
	}
	
}
