package com.example.responses;

import com.example.entities.Album;
import com.example.entities.Artist;
import com.example.entities.Playlist;
import com.example.entities.Song;

public class SearchResponse {
	private Song songs;
	private Artist artists;
	private Playlist playlists;
	private Album albums;
	
	public SearchResponse(Song songs, Artist artists, Playlist playlists, Album albums) {
		super();
		this.songs = songs;
		this.artists = artists;
		this.playlists = playlists;
		this.albums = albums;
	}
	
	public Song getSongs() {
		return songs;
	}
	public void setSongs(Song songs) {
		this.songs = songs;
	}
	public Artist getArtists() {
		return artists;
	}
	public void setArtists(Artist artists) {
		this.artists = artists;
	}
	public Playlist getPlaylists() {
		return playlists;
	}
	public void setPlaylists(Playlist playlists) {
		this.playlists = playlists;
	}
	public Album getAlbums() {
		return albums;
	}
	public void setAlbums(Album albums) {
		this.albums = albums;
	}
	
}
