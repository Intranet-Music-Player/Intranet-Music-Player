package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ValorationPk implements Serializable{

	@Column(name="USER_ID")
	private Long user;
	@Column(name="PLAYLIST_ID")
	private Long playlist;

	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public Long getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Long playlist) {
		this.playlist = playlist;
	}

}