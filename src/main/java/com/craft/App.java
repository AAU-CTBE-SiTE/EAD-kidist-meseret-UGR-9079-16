package com.craft;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class App {
    public static void main(String[] args) {

        // Create Spring container
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Get StudentService bean
        StudentService studentService =
                context.getBean(StudentService.class);

        // Call student operations
        System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");
        studentService.addStudent();
        studentService.updateStudent();
        studentService.deleteStudent();
        studentService.displayStudents();

        // Close Spring container
        context.close();
    }
}
