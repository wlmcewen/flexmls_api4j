package com.fbsdata.flexmls_api.services; 


import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.models.Contact;

public class ContactService extends BaseService<Contact> {

	@Override
	protected String getPath() {
		return "/contacts";
	}

	public ContactService(Client c) {
		super(c);
	}
	
}
