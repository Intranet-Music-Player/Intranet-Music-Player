package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Song;

public interface SongRepository extends CrudRepository<Song, Long> {

	Song findByNameSong (String nameSong);
}
