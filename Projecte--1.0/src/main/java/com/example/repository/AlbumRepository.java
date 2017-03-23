package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
	
	Album findByNameAlbum(String nameAlbum);
}
