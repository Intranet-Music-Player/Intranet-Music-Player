package com.example.requsest;

import com.example.entities.Band;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumRequest {

	private String nameAlbum;
	private String datePub;
	private Band band;
	
	
	public AlbumRequest (@JsonProperty("nameAlbum") String nameAlbum,
						 @JsonProperty("datePub") String datePub,
						 @JsonProperty("band") Band band) {
		
		super();
		this.nameAlbum = nameAlbum;
		this.datePub = datePub;
		this.band = band;
	}
	
	public AlbumRequest() {}
	
	public String getNameAlbum() {
		return nameAlbum;
	}
	public void setNameAlbum(String nameAlbum) {
		this.nameAlbum = nameAlbum;
	}
	public String getDatePub() {
		return datePub;
	}
	public void setDatePub(String datePub) {
		this.datePub = datePub;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	
	
}
