package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import java.util.List;



//user is the entity and Interger is the unique data type for the in the table for the id
public interface UserInterface extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByPasswordResetKet(String passwordResetKet);

	

	
	
	
	

}
