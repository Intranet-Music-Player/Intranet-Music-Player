package com.example.requsest;

import com.example.entities.Genere;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SongRequest {
	
	private String nameSong;
	private Float durationSong;
	private Genere genere;
	
	
	@JsonCreator
	public SongRequest (@JsonProperty("namesong") String nameSong, 
						@JsonProperty("durationsong") Float durationSong, 
						@JsonProperty("genere") Genere genere) {
		
		super();
		this.nameSong = nameSong;
		this.durationSong = durationSong;
		this.genere = genere;
	}
	
	
	public SongRequest() {}


	public String getNameSong() {
		return nameSong;
	}
	public void setNameSong(String nameSong) {
		this.nameSong = nameSong;
	}
	public Float getDurationSong() {
		return durationSong;
	}
	public void setDurationSong(Float durationSong) {
		this.durationSong = durationSong;
	}
	public Genere getGenere() {
		return genere;
	}
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
}
