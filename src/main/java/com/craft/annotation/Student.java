package com.craft.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String name;
    private int age;
    private Address address;

    public Student(
            @Value("101") int id,
            @Value("John Doe") String name,
            @Value("20") int age,
            Address address) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Student Address: " + address);
    }
}