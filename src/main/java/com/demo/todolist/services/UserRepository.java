package com.demo.todolist.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.todolist.models.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {
	
	public boolean existsByEmail(String email);
	
	UserDtls findByEmail(String email);

}
