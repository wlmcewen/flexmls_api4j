package com.fbsdata.flexmls_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Session {
	
	String token;
	List<String> roles = new ArrayList<String>();
	Date expiration;
	
	public Session(String token, List<String> roles, Date expiration) {
		super();
		this.token = token;
		this.roles = Collections.unmodifiableList(roles);
		this.expiration = expiration;
	}
	public String getToken() {
		return token;
	}
	public List<String> getRoles() {
		return roles;
	}
	public Date getExpiration() {
		return expiration;
	}
	
	public boolean isExpired(){
		return expiration.before(new Date());
	}

}
