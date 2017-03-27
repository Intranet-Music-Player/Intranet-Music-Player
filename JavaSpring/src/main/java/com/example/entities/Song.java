package com.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Song {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SONG_ID")
	private Long songId;
	@Column(name="NAMESONG", nullable = false)
	private String nameSong;
	@Column(name="DURATIONSONG", nullable = false)
	private Float durationSong;
	@JsonManagedReference
	@ManyToOne(cascade=CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="GENERE_ID")
	private Genere genere;

	public Song(Song song) {
		this.nameSong= song.getNameSong();
		this.durationSong = song.getDurationSong();
	}
	
	public Song() {}
	
	public Song(String nameSong, Float durationSong, Genere genere) {
		super();
		this.nameSong = nameSong;
		this.durationSong = durationSong;
		this.genere = genere;
	}
	
	public Long getSongId() {
		return songId;
	}
	public void setSongId(Long songId) {
		this.songId = songId;
	}
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
//	
//	public List<Playlist> getSongplaylist() {
//		return songplaylist;
//	}
//	public void setSongplaylist(List<Playlist> songplaylist) {
//		this.songplaylist = songplaylist;
//	}
//	public void addPlaylist (Playlist playlist) {
//		songplaylist.add(playlist);
//	}
	
}
