package com.example.entities;

import java.util.List;
import javax.persistence.*;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long userId;
	@Column(name = "USERNAME", nullable = false)
	private String username;
	@Column(name = "USERLOGIN", unique = true, nullable = false)
	private String userlogin;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=Playlist.class)
	@JoinTable (name= "USER_PLAYLIST" , 
				joinColumns = { @JoinColumn(name = "USER_ID")},
				inverseJoinColumns = {@JoinColumn(name="PLAYLIST_ID")}
				)
	private List<Playlist> playlists;
	
	public User(User user) {
		this.username= user.getUsername();
		this.userlogin=user.getUserlogin();
		this.password = user.getPassword();
		this.email= user.email;
	}
	public User() {
	}

	public User(String username, String userlogin, String password, String email) {
		super();
		this.username = username;
		this.userlogin = userlogin;
		this.password = password;
		this.email = email;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
	}
	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void addPlaylist (Playlist playlist){
		playlists.add(playlist);
	}
	
	
}		