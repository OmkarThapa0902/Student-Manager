package com.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manager.entities.Instructor;
import com.manager.entities.Student;

public interface UserRepo extends JpaRepository<Instructor, Integer> {
	
	@Query("select u from Instructor u where u.email = :email")
	public Instructor getInstructorByUserName(@Param("email") String email);

}