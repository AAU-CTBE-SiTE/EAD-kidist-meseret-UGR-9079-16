package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);

        System.out.println(student1 == student2);

        student1.setName("John Doe");
        System.out.println(student2.getName());
    }
}
