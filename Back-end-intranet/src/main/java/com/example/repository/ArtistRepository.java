package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
	Artist findByName( String name);
	
}
