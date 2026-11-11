# Spring Boot CRUD Operations

## Description

This project demonstrates CRUD (Create, Read, Update, and Delete) operations using Spring Boot REST APIs.

It uses HTTP methods to perform operations on actor data.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Maven
- Postman

## CRUD Operations

| Operation | HTTP Method | Description |
|---|---|---|
| Create | POST | Add a new actor |
| Read | GET | Retrieve all actors or an actor by ID |
| Update | PUT | Update all actor information |
| Partial Update | PATCH | Update specific actor information |
| Delete | DELETE | Remove an actor |

## Base URL

```text
http://localhost:8080/api/v1
```

## API Endpoints

| Method | Endpoint |
|---|---|
| GET | `/actor` |
| GET | `/actor/{id}` |
| POST | `/actor` |
| PUT | `/actor/{id}` |
| PATCH | `/actor/{id}` |
| DELETE | `/delete/{id}` |

## Testing

All CRUD operations are tested using Postman by sending HTTP requests and checking the responses.

