package com.flexmls.flexmls_api.models;

import org.codehaus.jackson.annotate.JsonProperty;

import com.flexmls.flexmls_api.models.ResourceEntity;

public class ExampleModel extends ResourceEntity {

	@JsonProperty("Foo")
	String foo;
	@JsonProperty("Bar")
	int bar;
	
	// TODO I'd like to have some sort of validation that guarantees all properties exist.
	@JsonProperty("Barx")
	int barx;
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public int getBar() {
		return bar;
	}
	public void setBar(int bar) {
		this.bar = bar;
	}
	public int getBarx() {
		return barx;
	}
	public void setBarx(int barx) {
		this.barx = barx;
	}
	
}
