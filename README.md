# Student Management System

## Description
This project is a simple Student Management System developed using Spring Boot and MySQL.

It allows users to perform CRUD operations (Create, Read, Update, and Delete) on student records stored in a MySQL database.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman

## Database
- Database Name: `student_management_db`
- Table Name: `students`

Each student has the following information:
- ID
- Name
- Email
- Age
- Department

## API Endpoints

Base URL: `http://localhost:8080/api/v1/student`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/student` | Add a new student |
| GET | `/api/v1/student` | Get all students |
| GET | `/api/v1/student/{id}` | Get a student by ID |
| PUT | `/api/v1/student/{id}` | Update a student |
| DELETE | `/api/v1/student/{id}` | Delete a student |

## Testing
All CRUD operations were tested successfully using Postman, and student records are stored in the MySQL database.