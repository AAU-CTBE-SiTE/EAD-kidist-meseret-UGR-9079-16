# Spring AOP Lab – Student Management System

## 1. Project Overview

This project demonstrates **Aspect-Oriented Programming (AOP)** using the Spring Framework.

The application implements a simple Student Management System with operations such as adding, updating, deleting, and displaying students.

Spring AOP is used to separate common functionality, such as logging and exception monitoring, from the main business logic.

## 2. Technologies Used

- Java 17
- Spring Framework 6.2.7
- Spring AOP
- AspectJ Weaver 1.9.24
- Maven
- IntelliJ IDEA

## 3. Project Structure

```text
spring-aop-lab/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── craft/
│                   ├── App.java
│                   ├── AppConfig.java
│                   ├── StudentService.java
│                   └── LoggingAspect.java
│
├── pom.xml
├── .gitignore
└── README.md
```

## 4. Project Components

### App.java
The main entry point of the application. It creates the Spring container, retrieves the StudentService bean, executes student operations, and closes the container.

### AppConfig.java
Configures the Spring application using Java-based configuration.

It uses:
- `@Configuration` to declare a configuration class.
- `@ComponentScan` to discover Spring components.
- `@EnableAspectJAutoProxy` to enable Spring AOP proxy support.

### StudentService.java
Contains the main student management operations:

- `addStudent()`
- `updateStudent()`
- `deleteStudent()`
- `displayStudents()`
- `getStudentName()`
- `testException()`

### LoggingAspect.java
Contains the common logging behavior using five types of Spring AOP advice.

## 5. AOP Advice Implemented

| Advice | Description |
|---|---|
| `@Before` | Executes before a matched method |
| `@After` | Executes after a matched method finishes, whether successfully or exceptionally |
| `@AfterReturning` | Executes after a method completes successfully |
| `@AfterThrowing` | Executes when a matched method throws an exception |
| `@Around` | Wraps method execution and controls when the original method proceeds |

## 6. Maven Dependencies

The project uses these dependencies:

- `spring-context`: Provides the Spring application context and component management.
- `spring-aop`: Provides Spring AOP functionality.
- `aspectjweaver`: Supports AspectJ annotation-based aspect definitions.


## 7. Expected Application Output

```text
===== STUDENT MANAGEMENT SYSTEM =====

===== 1. ADD STUDENT =====
Before method execution...
Student added successfully!
After method execution...

===== 2. UPDATE STUDENT =====
Before method execution...
Student updated successfully!
After method execution...

===== 3. DELETE STUDENT =====
Before method execution...
Student deleted successfully!
After method execution...

===== 4. DISPLAY STUDENTS (@AROUND) =====
Around: Before method execution...
Before method execution...
Displaying all students...
After method execution...
Around: After method execution...
Execution time: 0 milliseconds

===== 5. GET STUDENT NAME (@AFTER RETURNING) =====
Before method execution...
Method returned successfully. Student name: Abebe
After method execution...
Returned student name: Abebe

===== 6. TEST EXCEPTION (@AFTER THROWING) =====
Before method execution...
Exception occurred: Student operation failed!
After method execution...
Caught exception: Student operation failed!

===== APPLICATION FINISHED =====
```

*Note: Execution time and advice ordering may vary.*

## 8. Conclusion

This project demonstrates how Spring AOP separates cross-cutting concerns from the main business logic of an application.
