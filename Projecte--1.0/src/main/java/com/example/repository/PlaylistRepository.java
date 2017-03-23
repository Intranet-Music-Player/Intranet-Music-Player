package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
	
	Playlist findByName( String name);
}
