# Lab 1: Java Maven Starter Project
A foundational Java application configured with Apache Maven and JDBC for managing student records in a MySQL database.

## Prerequisites

Before running the application, ensure the following are installed:

* **Java Development Kit (JDK):** Version 17 or higher
* **Apache Maven:** Version 3.8+ (or use the included Maven wrapper `./mvnw`)
* **Git**


Verify your local environment:
```bash
java -version
mvn -version
```
## What This Project Does
* **Task 1:** Connects to MySQL, creates a database called `StudentsDB`, and sets up a `students` table.
* **Task 2:** Inserts sample student records into the table using PreparedStatement.
* **Task 3:** Reads and prints all the student records from the database.
* **Task 4:** Updates a student's grade and deletes a student record.
* **Task 5:** Handles database transactions with commit and rollback.
