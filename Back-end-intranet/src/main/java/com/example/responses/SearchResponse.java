package com.example.responses;

import java.util.ArrayList;

import com.example.entities.Album;
import com.example.entities.Artist;
import com.example.entities.Playlist;
import com.example.entities.Song;

public class SearchResponse {
	private ArrayList<Song> songs;
	private ArrayList<Artist> artists;
	private Playlist playlists;
	private Album albums;
	
	public SearchResponse(ArrayList<Song> songs, ArrayList<Artist> artists, Playlist playlists, Album albums) {
		super();
		this.songs = songs;
		this.artists = artists;
		this.playlists = playlists;
		this.albums = albums;
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	public ArrayList<Artist> getArtists() {
		return artists;
	}
	public void setArtists(ArrayList<Artist> artists) {
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
