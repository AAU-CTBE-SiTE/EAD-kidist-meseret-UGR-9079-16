package com.craft;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void addStudent() {
        System.out.println("Student added successfully!");
    }

    public void updateStudent() {
        System.out.println("Student updated successfully!");
    }

    public void deleteStudent() {
        System.out.println("Student deleted successfully!");
    }

    public void displayStudents() {
        System.out.println("Displaying all students...");
    }
    public String getStudentName() {
        return "Abebe";
    }
}
