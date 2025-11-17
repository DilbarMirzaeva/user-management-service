# User Management Service

A Spring Boot REST API for managing users with CRUD operations, status management, and AOP-based logging.

---

## Features

- Create, Read, Update, Delete users (soft delete)
- Update user status (ACTIVE, INACTIVE, DELETED)
- Validate user input (email, phone, date of birth)
- MapStruct DTO mapping
- **AOP-based logging** for service method execution and parameters
- Unit tests with JUnit 5 + Mockito
- PostgreSQL database integration
- Dockerized application for easy deployment

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- MapStruct
- Spring AOP
- JUnit 5 & Mockito
- Docker & Docker Compose

---

## Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- Docker & Docker Compose

---

### Running Locally with Docker

1. Clone the repository:

```bash
git clone https://github.com/DilbarMirzaeva/user-management-service.git
cd user-management-service
