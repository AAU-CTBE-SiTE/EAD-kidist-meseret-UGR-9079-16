# Lab 2: Java Persistence API (JPA)

## Project Description

This project demonstrates how to use the Java Persistence API (JPA) with Hibernate ORM and MySQL to manage student records.

It covers basic database operations (CRUD) and JPQL (Java Persistence Query Language) for retrieving and managing student data.

## Technologies Used

- Java 17
- Maven
- Jakarta Persistence API (JPA)
- Hibernate ORM
- MySQL
- Lombok
- IntelliJ IDEA

## Project Structure

```text
lab_2_repo/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── craft/
        │           ├── Main.java
        │           └── Student.java
        └── resources/
            └── META-INF/
                └── persistence.xml
```

## Database Configuration

Create the MySQL database using:

```sql
CREATE DATABASE jpadb1;
```

The database connection is configured in `persistence.xml`.

Update the database username and password to match your local MySQL configuration.

Hibernate is configured to update the database schema automatically.

## Student Entity

The `Student` entity represents a student record with the following fields:

- `id` — Student ID, generated automatically.
- `name` — Student name.
- `tech` — Student's technology.

## CRUD Operations

The project demonstrates the following operations:

- **Create:** Save a student record.
- **Read:** Retrieve a student by ID.
- **Update:** Modify a student's name or technology.
- **Delete:** Remove a student by ID.

## JPQL Queries

The project includes JPQL examples for:

- Retrieving all students and student names.
- Searching by student ID, name, and technology.
- Filtering with `LIKE`, `IN`, and `BETWEEN`.
- Sorting with `ORDER BY`.
- Using `DISTINCT` and aggregate functions such as `COUNT`, `MAX`, `MIN`, `AVG`, and `SUM`.
- Using named and positional parameters.
- Updating and deleting records with bulk queries.
- Grouping with `GROUP BY` and filtering groups with `HAVING`.
- Using subqueries and `EXISTS`.
- Using string functions such as `UPPER` and `LENGTH`.

