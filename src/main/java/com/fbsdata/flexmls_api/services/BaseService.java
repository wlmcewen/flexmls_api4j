package com.fbsdata.flexmls_api.services;

import com.fbsdata.flexmls_api.Client;

public class BaseService {
	
	public static Client c = null;
	
	public Client getClient(){
		return c;
	}
	

}
