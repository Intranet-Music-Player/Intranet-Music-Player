package com.example.requsest;

import com.example.entities.Band;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistRequest {
	private String name;
	private String birth;
	private Band band;
	
	@JsonCreator
	public ArtistRequest (@JsonProperty("name") String name, 
						  @JsonProperty("birth") String birth,
						  @JsonProperty("band") Band band) {
		
		super();
		this.name = name;
		this.birth = birth;
		this.band = band;
	}
	
	public ArtistRequest() {}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}
	
}
