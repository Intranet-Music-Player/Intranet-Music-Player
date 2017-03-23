package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Band;

public interface BandRepository extends CrudRepository<Band, Long>{
	
	Band findByName (String bandName);
}
