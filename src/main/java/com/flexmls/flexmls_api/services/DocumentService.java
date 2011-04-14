package com.flexmls.flexmls_api.services; 

import com.flexmls.flexmls_api.Client;
import com.flexmls.flexmls_api.models.Document;

public class DocumentService extends SubResourceService<Document> {
	
	public DocumentService(Client c, String prefix) {
		super(c,prefix);
	}

	@Override
	public String getSubResourcePath() {
		return "/documents";
	}

	
}
