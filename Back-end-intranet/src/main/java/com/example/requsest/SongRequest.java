package com.example.requsest;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SongRequest {
	
	private String nameSong;
	private Integer durationSong;
	private String genereN;
	
	
	@JsonCreator
	public SongRequest (@JsonProperty("namesong") String nameSong, 
						@JsonProperty("durationsong") Integer durationSong, 
						@JsonProperty("genereName") String genereN) {
		
		super();
		this.nameSong = nameSong;
		this.durationSong = durationSong;
		this.genereN = genereN;
	}
	
	
	public SongRequest() {}


	public String getNameSong() {
		return nameSong;
	}
	public void setNameSong(String nameSong) {
		this.nameSong = nameSong;
	}
	public Integer getDurationSong() {
		return durationSong;
	}
	public void setDurationSong(Integer durationSong) {
		this.durationSong = durationSong;
	}
	public String getGenereN() {
		return genereN;
	}
	public void setGenereN(String genereN) {
		this.genereN = genereN;
	}
}
