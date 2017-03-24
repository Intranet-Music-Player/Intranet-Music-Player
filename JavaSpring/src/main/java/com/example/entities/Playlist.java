package com.example.entities;
import java.util.List;

import javax.persistence.*;

@Entity
public class Playlist {
   
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="PLAYLIST_ID")
	private Long id;
	@Column(name="PLAYLIST_NAME", nullable = false)
	private String name;
	@Column(name="PLAYLIST_DURATION", nullable = false)
	private int duration;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Song.class)
	@JoinTable  (name = "PLAYLIST_SONG",
			joinColumns = { @JoinColumn (name = "PLAYLIST_ID")},
			inverseJoinColumns = { @JoinColumn (name = "SONG_ID")} 
			)
	private List<Song> songs;
	
	public Long getPlaylistId() {
		return id;
	}
	public void setPlaylistId(Long playlistId) {
		this.id = playlistId;
	}
	public String getPlaylistName() {
		return name;
	}
	public void setPlaylistName(String playlistName) {
		this.name = playlistName;
	}
	public int getPlaylistDuration() {
		return duration;
	}
	public void setPlaylistDuration(int playlistDuration) {
		this.duration = playlistDuration;
	}
	public void addSong(Song song){
		songs.add(song);
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}