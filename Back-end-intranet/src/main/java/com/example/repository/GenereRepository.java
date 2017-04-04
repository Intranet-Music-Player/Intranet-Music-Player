package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Genere;

public interface GenereRepository extends CrudRepository<Genere, Long> {

	Genere findByGenereName(String genereName);
}
