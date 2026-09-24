# Arquitectura

## Backend

Java 21, Spring Boot 3.5.x, Maven, arquitectura hexagonal, Spring Data JPA, MySQL 8.4, Flyway, Spring Security y JWT access/refresh, REST/JSON.

El dominio no depende de Spring/JPA/HTTP; aplicación contiene casos de uso; REST y persistencia son adaptadores.

## Frontend

Node.js 24 LTS, TypeScript y React o Angular según Stitch/Google AI Studio. La URL de API debe ser configurable por environment.

## Calidad

Se esperan pruebas de dominio, aplicación, persistencia/REST relevante y validaciones de build/typecheck del frontend.
