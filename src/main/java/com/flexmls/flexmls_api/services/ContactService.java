package com.flexmls.flexmls_api.services; 


import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.Contact;

public class ContactService extends BaseService<Contact> {

	@Override
	public String getPath() {
		return "/contacts";
	}

	public ContactService(Client c) {
		super(c);
	}
	
}
