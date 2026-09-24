# Síntesis

## Hechos

- El workspace contiene `citas-api` (Java/Spring Boot) y `citas-web` (TypeScript aún sin framework seleccionado).
- El frontend consume directamente la API REST de Spring Boot; no existe Express/BFF.
- La lógica de negocio pertenece a `citas-api`; la UI pertenece a `citas-web`.
- La wiki global se versiona dentro de este repositorio backend.

## Estado inicial

Ambos repositorios están sin implementación de aplicación, sin commits base y sin rama `develop`. No existe todavía contrato REST ni HU/CA/DoD generados.

## Pendientes

Resolver los riesgos de [Riesgos](risks.md) antes de implementar o ingerir decisiones no aprobadas.
