package com.site.studentcrud.controller;

import com.site.studentcrud.model.Student;
import com.site.studentcrud.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // READ ALL
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully!";
    }
}