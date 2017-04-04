package com.example.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Genere {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GENERE_ID")
	private Long genereId;
	@Column(name = "GENERENAME",unique=true, nullable = false)
	private String genereName;
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL,mappedBy="genere")
	private Set<Song> songs;
	
	public Long getGenereId() {
		return genereId;
	}
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	public void setGenereId(Long genereId) {
		this.genereId = genereId;
	}
	public String getGenereName() {
		return genereName;
	}
	public void setGenereName(String genereName) {
		this.genereName = genereName;
	}
}
