package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ALBUM_ID")
	private Long albumId;
	@Column(name="NAMEALBUM", nullable = false)
	private String nameAlbum;
	@Column(name="DATAPUB", nullable = false)
	private String datePub;
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="BAND_ID")
	private Band band;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Song.class)
	@JoinTable (name = "ALBUM_SONG",
							joinColumns = { @JoinColumn (name = "ALBUM_ID")},
							inverseJoinColumns = { @JoinColumn (name = "SONG_ID")} 
							)
	private List<Song> albumsongs;
	
	
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
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
	public List<Song> getAlbumsongs() {
		return albumsongs;
	}
	public void setAlbumsongs(List<Song> albumsongs) {
		this.albumsongs = albumsongs;
	}
	public void addSong (Song song) {
		albumsongs.add(song);
	}
	
	
	

}
