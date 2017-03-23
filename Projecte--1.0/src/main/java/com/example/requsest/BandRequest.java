package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandRequest {
	
	private String name;

	@JsonCreator
	public BandRequest(@JsonProperty("name") String name) {
		super();
		this.name = name;
	}
	public BandRequest() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
