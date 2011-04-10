package com.fbsdata.flexmls_api.models;

public class SubResourceEntity extends ResourceEntity {
	
	String parentEntity;

	public SubResourceEntity(String parentEntity) {
		super();
		this.parentEntity = parentEntity;
	}

}
