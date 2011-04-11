package com.fbsdata.flexmls_api.models;

public class SubResourceEntity extends ResourceEntity {

	String parentEntity;
	
	public SubResourceEntity(String parentEntity) {
		super();
	}

	public String getParentEntity() {
		return parentEntity;
	}

	protected void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}
}
