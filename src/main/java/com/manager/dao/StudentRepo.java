package com.manager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manager.entities.Instructor;
import com.manager.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    // For pagination
    @Query("from Student as s where s.ins.id =:instructorId")
    public Page<Student> findStudentByInstructor(@Param("instructorId") int instructorId, Pageable pageable);
    
    // Searching by name and instructor
    public List<Student> findByNameContainingAndIns(String name, Instructor ins);
}
