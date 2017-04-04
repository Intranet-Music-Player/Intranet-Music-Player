package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUserlogin(String userlogin);
	User findByEmail(String email);
}
