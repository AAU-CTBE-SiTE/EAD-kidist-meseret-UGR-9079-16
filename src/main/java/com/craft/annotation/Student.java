package com.craft.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student {

    private int id;
    private String name;
    private int age;
    private Address address;
    private List<Subject> subjects;

    // Constructor injection
    public Student(
            @Value("101") int id,
            @Value("John Doe") String name,
            @Value("20") int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Setter injection
    @Value("102")
    public void setId(int id) {
        this.id = id;
    }

    @Value("Jane")
    public void setName(String name) {
        this.name = name;
    }

    @Value("21")
    public void setAge(int age) {
        this.age = age;
    }

    // Setter object injection
    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }

    // Collections injection
    @Autowired
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Student Address: " + address);

        if (subjects != null) {
            for (Subject subject : subjects) {
                System.out.println(subject.getSubjectName());
            }
        }
    }
}