# Spring Boot Project

A backend project built with **Spring Boot**, demonstrating:

- User authentication & authorization with **JWT**
- Role-based access control (**User, Admin**)
- CRUD operations with relational entities (User, Profile, Post, Role, etc.)
- Modular folder structure for maintainability
- In-memory database H2-console

---

## Features

- **Authentication**
    - Register / Login with JWT
    - Refresh token, Logout
- **User**
    - View / Update profile
    - CRUD personal posts
- **Admin**
    - Manage users
    - Manage posts
    - Access system config

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA / Hibernate**
- **MySQL** (can switch to H2 for testing)
- **Lombok**
- **Maven**

---

## Project Structure

com.example.demoWeb
│
├── account
│ ├── controller # RestController cho User/Auth
│ ├── dto # Request/Response DTO
│ ├── mapper # Map giữa Entity ↔ DTO
│ ├── model # Entity (User, Role, Profile…)
│ ├── repository # JPA Repository
│ └── service # Service Interface + Impl
│
├── admin
│ ├── controller # API dành cho Admin
│ ├── dto # DTO cho admin request/response
│ ├── mapper # Mapper riêng cho admin (nếu cần)
│ ├── model # Entity liên quan đến quản trị (nếu có)
│ ├── repository # Repo dùng riêng admin
│ └── service.impl # Impl của service
│
├── post
│ ├── controller # CRUD cho Post
│ ├── dto
│ ├── mapper
│ ├── model # Entity Post
│ ├── repository
│ └── service
│
├── profile
│ ├── controller # API quản lý Profile (user profile)
│ ├── dto
│ ├── mapper
│ ├── model # Entity Profile
│ ├── repository
│ └── service
│
├── config # SecurityConfig, WebConfig, etc.
├── exception # GlobalExceptionHandler, custom exception
├── security # JWT utils, filter, authentication manager
└── DemoWebApplication # Main application


---

## Setup & Run

### Prerequisites

- Java 17+
- Maven

### Configuration

The project uses **H2 Database** by default.  
You can access the DB console at:  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Default config in `src/main/resources/application.properties`:```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

jwt.secret=your-256-bit-secret
jwt.expiration=3600000

# Build

mvn clean install

# Run

mvn spring-boot:run

#API Endpoints

Auth (/api/auth)

- POST /register – Register new user

- POST /login – Login and get JWT tokens

- POST /refresh – Refresh access token

- POST /logout – Logout (invalidate token)

User (/api/user)

- GET /me – Get own User data

- GET /profile Get own profile

- PUT /profile – Update own profile

Admin (/api/admin)

- GET /users?name=?&page=?&size=?&sort=name,asc – List users with optional name filter, pagination, and sorting

- GET /users/id - get any user

- POST /users - create new user

- PUT /users/id - update user

- DELETE /users/{id} – Delete user

- GET /posts - list all post

- DELETE /posts/{id} – Delete any post