# User Management Service

## Project Overview
**User Management Service** is a Spring Boot backend application that manages user data through RESTful APIs. The service includes CRUD operations, status updates, DTO mapping, exception handling, and database integration. It is deployed on **Render** using Docker.

### Key Features
- CRUD operations for users
- Update user status (active/inactive)
- DTO mapping using MapStruct
- AOP-based logging
- Global exception handling
- Unit testing
- PostgreSQL integration
- Dockerized deployment

### Technologies Used
- Java 17
- Spring Boot 3.2
- Spring Data JPA / Hibernate
- PostgreSQL
- MapStruct
- Docker & Docker Compose
- JUnit 5
- Lombok
- Gradle
- Render (Deployment)

## API Endpoints

Base URL: `/api/v1/users`

| HTTP Method | Endpoint            | Description                       | Request Body |
|------------|-------------------|----------------------------------|--------------|
| POST       | `/save`            | Create a new user                | `UserRequest` JSON |
| GET        | `/all`             | Get all users                     | - |
| GET        | `/{id}`            | Get a user by ID                  | - |
| PUT        | `/{id}`            | Update a user by ID               | `UserRequest` JSON |
| DELETE     | `/{id}`            | Delete a user by ID               | - |
| PATCH      | `/{id}`            | Update a user's status            | `StatusUpdateRequest` JSON |

