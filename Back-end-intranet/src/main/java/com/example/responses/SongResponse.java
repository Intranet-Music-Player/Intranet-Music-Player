package com.example.responses;

import com.example.requsest.SongRequest;

public class SongResponse {
	
	private boolean success;
	private String message;
	private SongRequest song;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SongRequest getSong() {
		return song;
	}
	public void setSong(SongRequest song) {
		this.song = song;
	}
	
	public SongResponse songAdded(SongRequest song) {
		
		SongResponse s = new SongResponse();
		s.message = "SONG ADDED";
		s.success = true;
		this.song = song;
		
		return s;
	}
	
}
