package com.example.school.repository;

import com.example.school.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByEmail(String email);
    List<Student> findByNameContainingIgnoreCase(String name);
}