# Clean Architecture Microservices (CAM)

## Overview

This is a **Modern Microservices Back-End** application using **Java** and **Spring Boot**.

It serves as a template to demonstrate the implementation of microservices using
modern **Java** and **Spring Boot** and follow some of the best practices in development.

---

## Quick Info

![Java](https://img.shields.io/badge/java-21-brightgreen)
![SpringBoot](https://img.shields.io/badge/spring--boot-3.4.4-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.9-blue)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-brightgreen)

![Last Commit](https://img.shields.io/github/last-commit/arivan-amin/Clean-Architecture-Microservices)
![Repo Size](https://img.shields.io/github/repo-size/arivan-amin/Clean-Architecture-Microservices)
![Contributors](https://img.shields.io/github/contributors/arivan-amin/Clean-Architecture-Microservices)
![Platform](https://img.shields.io/badge/platform-Linux%20%7C%20Windows%20%7C%20MacOS-informational)

## Architecture Overview

```
                               +----------------+
                               |    Keycloak    |
                               | (Auth Server)  |
                               +-------+--------+
                                       | Keycloak not yet implemented
                                       v
                               +-------+--------+             +-------------------+
      Client Request           |   API Gateway  | ----------> | Discovery Server  |<---
---------------------------->  |    (Spring)    | <---------- |      (EUREKA)     |<- |
                               +-------+--------+             +-------------------+ | |
                                       |                                            | |
                         Authenticated | Request                                    | |
                                       |                                            | |
                                       v                                            | |
                 Actuator    +---------+---------+     AOP     +-----------------+  | |
          -------------------|  Patient Service  | ----------> | Audit Log Event |  | |
          |     Prometheus   |   (RESTful API)   | <---------- |     (Kafka)     |  | |
          v                  +---------+---------+             +--------+--------+  | |
+---------+--------+                    |     |                         |           | |
|      Grafana     |                    |     ------------              | Consume   | |
|  Metrics & Logs  |                    |                |              |           | |
|  Visualization   |              Write |                |              v           | |
+---------+--------+              Logs  |   Write Logs   |     +--------+--------+  | |
          |                             |  --------------|-----|  Audit Service  |--- |
          | Read Logs                   v  v             |     +--------+--------+    |
          |                     +-------+-------+        |                            |
          --------------------> | Docker Volume |        |                            |
                                +-------+-------+        ------------------------------ 
                                                               Register with Eureka
```

## Notable Features

### Automatic Audit Logs Recording

Uses Spring **AOP** to create Audit Events automatically whenever any API in any of the services are
called and uses **Kafka** to send them to the **Audit Service** to be recorded, allowing the
controllers to be clutter-free and simple.

### Clean Restful API in all services

The API follows the modern best practices in Restful services recommendations like using *
*ResponseEntity** and returning **ProblemDetail**.

### CQRS

Command and Query Separation Principle to implement Business logic.

### Rate Limiting

Implemented in **API Gateway** using **Redis Rate Limiter**.

### ArchUnit

Validate architectural boundaries and verify adherence to best coding standards.

### PMD and Pitest

Use PMD to verify the coding style and Pitest for mutation testing.

### RestControllerAdvice

Handle specific exceptions and return a unified and standard error response instead of an exception
stack trace using Spring **ProblemDetail**.

### OpenAPI and Swagger Docs

Provide detailed documentation for all endpoints.

### Entity and DTO separation

Decouples core business logic from presentation using request and response pojo.

### Core Entity and Jpa separation

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

## Architecture concepts demonstrated and implemented

- **Microservices**
- **Clean Architecture**
- **Clean Code**
- **Mutation Testing**
- **Command-Query Responsibility Separation**
- **Solid Principles**
- **Loose Coupling of Components**
- **Spring Dependency Injection**
- **Aspect-Oriented Programming (AOP)**
- **Rate Limiting API**

---

## Technical Features Implemented

- **Microservices Architecture**.
- **Automatic Audit Logs recording**: Uses Spring AOP to automatically store audit logs.
- **Robust Monitoring**: Real-time monitoring with Grafana, Loki, and Tempo.
- **Event-Driven Communication**: Using Kafka.
- **Centralized Logging & Tracing**: Using Logback and Tempo.
- **Database Migrations**: Using Liquibase.
- **Dockerized Deployment**: Using Docker and Docker Compose.

---

### Currently, the following services are implemented, other services will be added:

- Discovery Server
- API Gateway
- Patient Service
- Audit Service

---

## Technologies used and their responsibility

- **Java 21**
- **Spring Boot**
- **Spring Cloud**
- **Eureka**: Dynamic service registry.
- **MySQL**: Services data storage.
- **Kafka**: Event streaming for microservices.
- **Keycloak**: Authentication and SSO provider.
- **Docker**
- **Grafana, Loki, Tempo**: Observability stack for metrics, logging, and tracing.
- **JUnit & Mockito**: Unit testing and Mocking.
- **Pitest**: Mutation testing.
- **ArchUnit**: Architecture boundaries testing and coding standards validation.
- **PMD**: Validate coding standards and best practices.
- **Swagger/OpenAPI**: API documentation.
- **Liquibase**: Database Migrations.
- **Lombok**: Cleaner code with reduced boilerplate.

---

## Grafana Monitoring Sample

![image](https://raw.githubusercontent.com/arivan-amin/Clean-Architecture-Microservices/master/Docs/Grafana/Grafana-Dashboard-1.png)

## 🚀 Installation Guide

### Prerequisites

- **Java 21**
- **Maven**
- **Docker** & **Docker Compose**

---

### Steps to Get Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/arivan-amin/Clean-Architecture-Microservices.git
   cd Clean-Architecture-Microservices
   ```

2. **Build Core and Testing Module:**
   ```bash
   cd Core
   mvn clean install
   cd ..
   cd Testing
   mvn clean install
   cd ..
   ```

3. **Build and Package the Project:**
   ```bash
   mvn clean package
   ```

4. **Set Environment Variables (Linux/MacOS):**
   ```bash
   export EUREKA_USER=admin
   export EUREKA_PASSWORD=admin
   ```
   ```
   *(For Windows, use `set` command)*
   ```

5. **Run Services with Docker Compose:**
   ```bash
   docker compose up -d
   ```

# Access the Services

## API Gateway

[http://localhost:8080](http://localhost:8080)

## Eureka Dashboard

[http://localhost:8080/eureka/web](http://localhost:8080/eureka/web)

## API Documentation

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Grafana Dashboard

#### Import pre-built dashboard JSON configuration from `docker/grafana/` folder

[http://localhost:3000/dashboards](http://localhost:3000/dashboards)

---

## 🧪 Testing

- **Run Unit and Integration Tests:**
   ```bash
   mvn test
   ```

---

## ⚙️ Microservices Overview

- **Discovery Server**: Dynamic service discovery and registry.
- **API Gateway**: Centralized entry point for routing and security.
- **Core Module**: Shared utilities and functionality.
- **Patient Service**: Manages patient data.
- **Audit Service**: Logs events and ensures compliance.
- **SSO Service**: Handles authentication and authorization using Keycloak.

---

## 🤝 Contributing

We welcome contributions! Fork the repository, create a new branch, and submit a pull request.

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more
details.

---

## 📧 Contact

For questions or inquiries:

- **Name:** Arivan Amin
- **Email:** [arivanamin@gmail.com](mailto:arivanamin@gmail.com)
