package com.craft.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan("com.craft.annotation")
public class AppConfig {

    @Bean
    @Order(1)
    public Subject math() {
        return new Subject("Maths 101");
    }

    @Bean
    @Order(2)
    public Subject java() {
        return new Subject("Java EE");
    }

    @Bean
    public Student constructorStudent() {
        return new Student(103, "Michael", 22);
    }

    @Bean
    public Student objectStudent(Address address) {
        Student student = new Student(104, "Abebe", 23);
        student.setAddress(address);
        return student;
    }

    @Bean
    public Student collectionStudent(java.util.List<Subject> subjects) {
        Student student = new Student(107, "Collection Student", 20);
        student.setSubjects(subjects);
        return student;
    }

    @Bean
    @Scope("singleton")
    public Student singletonStudent() {
        return new Student(105, "Singleton", 24);
    }

    @Bean
    @Scope("prototype")
    public Student prototypeStudent() {
        return new Student(106, "Prototype", 25);
    }
}