package com.fbsdata.flexmls_api.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fbsdata.flexmls_api.Client;

public class Base {
	
	protected Client getClient(){
		return null;
	}
	
	
	public static <T> List<T> get(String id){
		List<T> l = new ArrayList<T>();
		return l;
	}
	
	public static <T> List<T> get(String id, Map<String, String> opts){
		List<T> l = new ArrayList<T>();
		return l;
	}

}
