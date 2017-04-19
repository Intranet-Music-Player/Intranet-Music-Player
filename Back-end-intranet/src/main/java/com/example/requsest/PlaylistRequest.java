package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistRequest {
	private Long playlistId;
	private String name;
	private String userlogin;
	private Long songId;

	@JsonCreator
	public PlaylistRequest(	@JsonProperty("playlistId") Long playlistId,
							@JsonProperty("playlistName") String name,
							@JsonProperty("userlogin") String userlogin, 
							@JsonProperty("songId") Long songId) {
		
		super();
		this.playlistId = playlistId;
		this.name = name;
		this.userlogin = userlogin;
		this.songId = songId;
	}

	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}
}
