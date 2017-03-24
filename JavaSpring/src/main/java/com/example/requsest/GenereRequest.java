package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenereRequest {
	
	private String genereName;

	@JsonCreator
	public GenereRequest (@JsonProperty("genereName") String genereName) {
		super();
		this.genereName = genereName;
	}
	public GenereRequest () {}
	
	public String getGenereName() {
		return genereName;
	}
	public void setGenereName(String genereName) {
		this.genereName = genereName;
	}

}
