package com.example.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Band {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BAND_ID")
	private Long bandId;
	@Column(name = "NAME", unique = true, nullable = false)
	private String name;
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL,mappedBy="band")
	private Set<Artist> artists;
	
	public Band(Band band) {
		this.bandId = band.getBandId();
		this.name = band.getName();
		this.artists = band.getArtists();
	}

	public Band() {
	}

	public Long getBandId() {
		return bandId;
	}

	public void setBandId(Long bandId) {
		this.bandId = bandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}
	
	
	
}
