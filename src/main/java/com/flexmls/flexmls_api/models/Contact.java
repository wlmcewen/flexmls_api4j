package com.flexmls.flexmls_api.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Contact extends ResourceEntity {
	
	@JsonProperty("DisplayName")
	String displayName;
	@JsonProperty("PrimaryEmail")
	String primaryEmail;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	
}
