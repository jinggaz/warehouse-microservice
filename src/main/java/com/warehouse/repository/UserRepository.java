package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByName(String name);
	
	boolean existsUserByName(String name);
}
