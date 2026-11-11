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

        System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");

        // 1. Add student
        System.out.println("\n===== 1. ADD STUDENT =====");
        studentService.addStudent();

        // 2. Update student
        System.out.println("\n===== 2. UPDATE STUDENT =====");
        studentService.updateStudent();

        // 3. Delete student
        System.out.println("\n===== 3. DELETE STUDENT =====");
        studentService.deleteStudent();

        // 4. Display students - Around advice
        System.out.println("\n===== 4. DISPLAY STUDENTS (@AROUND) =====");
        studentService.displayStudents();

        // 5. Get student name - AfterReturning advice
        System.out.println("\n===== 5. GET STUDENT NAME (@AFTER RETURNING) =====");
        String studentName = studentService.getStudentName();
        System.out.println("Returned student name: " + studentName);

        // 6. Test exception - AfterThrowing advice
        System.out.println("\n===== 6. TEST EXCEPTION (@AFTER THROWING) =====");

        try {
            studentService.testException();
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\n===== APPLICATION FINISHED =====");

        // Close Spring container
        context.close();
    }
}
