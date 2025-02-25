package com.aman.editschema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aman.editschema.model.UserSchema;

public interface UserRepo extends JpaRepository<UserSchema, Integer> {
	
}


