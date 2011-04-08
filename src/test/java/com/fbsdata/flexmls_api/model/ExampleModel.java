package com.fbsdata.flexmls_api.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ExampleModel {

	@JsonProperty("Foo")
	String foo;
	@JsonProperty("Bar")
	int bar;
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
}
