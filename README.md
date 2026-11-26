# Spring Boot Microservices

## Project Overview

This project demonstrates communication between two independent Spring Boot microservices: Customer Service and Order Service.

Customer Service manages customer information, while Order Service manages customer orders. The two services communicate through HTTP using Spring's RestClient.

## Project Structure

```text
spring-microservices-lab/
│
├── customer-service/
│
├── order-service/
│
└── README.md
```

## Technologies Used

- Java 17
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- MySQL
- RestClient
- Postman
- Maven

## Microservices

### 1. Customer Service

- Port: `8080`
- Database: H2 in-memory database
- Manages customer information.
- Communicates with Order Service to retrieve customer orders.

### 2. Order Service

- Port: `8081`
- Database: MySQL (`orderdb`)
- Manages customer orders.
- Retrieves orders using customer ID.

## API Endpoints

### Customer Service — Port 8080

| Method | Endpoint | Description |
|---|---|---|
| GET | `/customers` | Get all customers |
| POST | `/customers` | Create a customer |
| GET | `/customers/{id}` | Get a customer by ID |
| GET | `/customers/{id}/orders` | Get a customer's orders |

### Order Service — Port 8081

| Method | Endpoint | Description |
|---|---|---|
| GET | `/orders` | Get all orders |
| POST | `/orders` | Create an order |
| GET | `/orders/{id}` | Get an order by ID |
| GET | `/orders/customer/{customerId}` | Get orders by customer ID |

## Database Configuration

- Customer Service uses an H2 in-memory database.
- Order Service uses a MySQL database named `orderdb`.
- The MySQL password is configured through the `DB_PASSWORD` environment variable.

Both microservices must be running for customer-order communication to work.

