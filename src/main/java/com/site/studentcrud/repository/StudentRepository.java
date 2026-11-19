package com.site.studentcrud.repository;

import com.site.studentcrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}