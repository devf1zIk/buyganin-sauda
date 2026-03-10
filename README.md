# Buyganin Sauda

Microservice based system for order management in an online store.

## Architecture

The system consists of several microservices:

- order-service – manages orders
- inventory-service – manages product stock
- api-gateway – entry point for clients
- common-lib – shared DTOs, events, enums

## Tech Stack

- Java
- Spring Boot
- Spring Cloud Gateway
- Apache Kafka
- PostgreSQL
- Docker