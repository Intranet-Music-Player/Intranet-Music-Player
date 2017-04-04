package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistRequest {
	private String name;

	@JsonCreator
	public PlaylistRequest(@JsonProperty("name") String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
