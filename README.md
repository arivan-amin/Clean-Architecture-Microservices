# Clean Architecture Microservices with Spring Boot 4

## Overview

This is a **Spring Boot 4** Microservices Back-End using **Java** and **Spring 7** following
**Clean Architecture** and **SOLID Principles**.

It serves as a template to demonstrate the implementation of microservices, following some of the
best development practices.

---

## Quick Info

![Java](https://img.shields.io/badge/java-25-brightgreen)
![SpringBoot](https://img.shields.io/badge/spring--boot-4.0.1-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.11-blue)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-brightgreen)

![Last Commit](https://img.shields.io/github/last-commit/arivan-amin/Spring-Clean-Microservices)
![Repo Size](https://img.shields.io/github/repo-size/arivan-amin/Spring-Clean-Microservices)
![Contributors](https://img.shields.io/github/contributors/arivan-amin/Spring-Clean-Microservices)
![Platform](https://img.shields.io/badge/platform-Linux%20%7C%20Windows%20%7C%20MacOS-informational)

## Architecture Overview

![image](https://raw.githubusercontent.com/arivan-amin/Clean-Architecture-Microservices/master/Docs/Diagram/Architecture-Diagram.jpg)

## Technologies used and their responsibility

- **Java 25**
- **Spring Boot 4**
- **Spring Cloud 2025.1**
- **MySQL 9.5**: Services data storage.
- **Kafka 4.1**: Event streaming for microservices.
- **Docker**
- **Eureka**: Dynamic service registry.
- **Grafana, Loki, Tempo**: Observability stack for metrics, logging, and tracing.
- **JUnit & Mockito**: Unit testing and Mocking.
- **ArchUnit**: Architecture boundaries testing and coding standards validation.
- **PMD**: Validate coding standards and best practices.
- **Pitest**: Mutation testing.
- **Swagger/OpenAPI**: API documentation.
- **Liquibase**: Database Migrations.
- **Lombok**: Cleaner code with reduced boilerplate.

---

## Architecture concepts and technical features demonstrated and implemented

- **Microservices Architecture**.
- **Clean Architecture & Clean Code**
- **Command-Query Responsibility Separation (CQRS)**
- **SOLID Principles**
- **Mutation Testing**
- **Spring Dependency Injection**
- **Aspect-Oriented Programming (AOP)**
- **Rate Limiting API**
- **Automatic Audit Logs recording**: Uses Spring AOP to store audit logs automatically.
- **Event-Driven Communication**: Using Kafka.
- **Robust Monitoring**: Real-time monitoring with Grafana, Loki, and Tempo.
- **Centralized Logging & Distributed Tracing**: Using Loki and Tempo.
- **Database Migrations**: Using Liquibase.
- **Dockerized Deployment**: Using Docker and Docker Compose.

---

## Notable Features

### Automatic Audit Logs Recording

Use Spring **AOP** to create Audit Events automatically whenever any API in any of the services is
called and save it to persistence, allowing the controllers to be clutter-free.

### Clean Restful API in all services

The API follows the modern best practices in RESTful services recommendations,
like using **ResponseEntity** and returning **ProblemDetail**.

### CQRS

Command and Query Separation Principle to implement Business logic.

### Rate Limiting

Implemented in **API Gateway** using **Redis Rate Limiter**.

### Outbox Pattern

Implements Outbox Pattern for sending audit events to guarantee 100% message delivery.

### ArchUnit

Validate architectural boundaries and verify adherence to best coding standards.

### PMD and Pitest

Use PMD to verify the coding style and Pitest for mutation testing.

### RestControllerAdvice

Handle specific exceptions and return a unified and standard error response instead of an exception
stack trace using Spring **ProblemDetail**.
Example of API response for every error.

```
{
    "type": "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/RuntimeException.html",
    "title": "Requested Patient Not Found",
    "status": 404,
    "detail": "Patient by the requested id not found",
    "instance": "/patients/protected/v1/accounts/33bff7c7-77ee-4c51-9ee0-c870b437f82e",
    "category": "Resource Not Found",
    "timestamp": "2025-04-22T18:45:43.927431130Z"
}
```

### OpenAPI and Swagger Docs

Provides detailed documentation for all endpoints.

### Entity and DTO separation

Decouples core business logic from presentation using request and response POJO.

### Core Entity and JPA separation

Core entities have no association with JPA and are never annotated with @Entity.

## Sample audit log from Audit-Service captured from API calls in Patient-Service

```
        // Create Patient Endpoint
        {
            "id": "6797e0215829937787277607",
            "serviceName": "patient-service",
            "location": "/patients/protected/v1/accounts",
            "action": "Create",
            "data": "CreatePatientRequest(firstName=Hayden, lastName=Ondricka, email=Helen21@gmail.com, dateOfBirth=977659882000, gender=MALE, address=Bernhard Cape)",
            "creationDate": "2025-01-27T14:36:01.528",
            "duration": "50ms",
            "response": "CreatePatientResponse(id=9622e5ef-5ab7-4faf-89db-7dd970ea8ef0)"
        }

        // Delete Patient Endpoint
        {
            "id": "6797e0115829937787277605",
            "serviceName": "patient-service",
            "location": "/patients/protected/v1/accounts/{id}",
            "action": "Delete",
            "data": "e8cd23d1-4bad-44bb-9b58-a3ca89dbf793",
            "creationDate": "2025-01-27T14:35:45.631",
            "duration": "25ms",
            "response": "Void"
        },
        
        // Read Patient by ID Endpoint
        {
            "id": "6797e0145829937787277606",
            "serviceName": "patient-service",
            "location": "/patients/protected/v1/accounts/{id}",
            "action": "Read",
            "data": "e8cd23d1-4bad-44bb-9b58-a3ca89dbf793",
            "creationDate": "2025-01-27T14:35:48.66",
            "duration": "14ms",
            "response": "Error: Patient by the requested id not found"
        }
```

### Currently, the following services are implemented; other services will be added:

- Discovery Server
- API Gateway
- Patient Service
- Audit Service

---

## Grafana Monitoring Sample

![image](https://raw.githubusercontent.com/arivan-amin/Clean-Architecture-Microservices/master/Docs/Grafana/Grafana-Dashboard-1.png)

## Installation Guide

### Prerequisites

- **Java 25**
- **Maven**
- **Docker** & **Docker Compose**

---

### Steps to Get Started

1. **Clone the Repository:**
   ```
   git clone https://github.com/arivan-amin/Clean-Architecture-Microservices.git
   cd Spring-Clean-Microservices
   ```

2. **Build and deploy the services to Docker using JIB:**
   ```
   mvn clean install
   ```

3. **Set Environment Variables (Linux/MacOS):**
   ```
   export EUREKA_USER=admin
   export EUREKA_PASSWORD=admin
   ```
   ```
   *(For Windows, use `set` command)*
   ```

4. **Start the required backbone apps with Docker Compose:**
   ```
   docker compose up -d
   ```
5. **Start the services(Patient, Audit, ...) manually or uncomment the section in docker compose
   file
   to run everything with Docker Compose:**
   ```
   docker compose up -d
   ```

# Access the Services

## API Gateway

[http://localhost:8080](http://localhost:8080)

## Eureka Dashboard

#### Username: admin

#### Password: admin

[http://localhost:8080/eureka/web](http://localhost:8080/eureka/web)

## API Documentation

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Grafana Dashboard

#### Import pre-built dashboard JSON configuration from the `docker/grafana/` folder

[http://localhost:3000/dashboards](http://localhost:3000/dashboards)

---

## Testing

- **Run Unit and Integration Tests:**
   ```bash
   mvn test
   ```

---

## Microservices Overview

- **Discovery Server**: Dynamic service discovery and registry.
- **API Gateway**: Centralized entry point for routing and security.
- **Core Module**: Shared utilities and functionality.
- **Outbox Storage Module**: Contains classes related to outbox messages storage, shared with
  all the modules.
- **Patient Service**: Manages patient data.
- **Audit Service**: Stores Audit Events, ensures compliance, and data integrity.

---

## Contributing

We welcome contributions! Fork the repository, create a new branch, and submit a pull request.

---

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more
details.

---

## Contact

For questions or inquiries:

- **Name:** Arivan Amin
- **Email:** [arivanamin@gmail.com](mailto:arivanamin@gmail.com)
