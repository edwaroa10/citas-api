# AGENTS.md — `citas-api`

## Estado observado

Este repositorio aún no contiene una aplicación Spring Boot: no existen `pom.xml`, código Java, migraciones Flyway ni pruebas. La rama actual es `main` y no tiene commits. Las especificaciones funcionales están en el workspace padre; las HU y el DoD aún no han sido generados en `docs/wiki/scrum/`.

No asumir paquetes, módulos, dependencias, endpoints ni esquemas que todavía no existan. Antes de inicializar o implementar, usar una HU aprobada como unidad de alcance.

## Fuentes de verdad

1. `../PRD.md`
2. `../RESTRICCIONES_TECNICAS.md`
3. `../database/REQUISITOS_NORMALIZACION_3FN.md`
4. HU, criterios de aceptación y DoD aprobados en `docs/wiki/scrum/`
5. Contratos y decisiones aprobados en la LLM Wiki global
6. Código y pruebas existentes

La LLM Wiki global está en `docs/wiki/llm-wiki/` y la mantiene el orquestador. Este agente puede consultarla, pero no crear una wiki alternativa ni persistir allí conocimiento operativo propio.

## Responsabilidad

Implementar exclusivamente el backend: Java 21, Spring Boot 3.5.x, Maven, REST/JSON, Spring Security con JWT access/refresh, Spring Data JPA, MySQL 8.4, Flyway y pruebas. No editar `../citas-web` ni acoplar el backend a React o Angular.

## Arquitectura exigida

- El dominio no depende de Spring, JPA, HTTP ni infraestructura.
- Los casos de uso viven en aplicación.
- Los puertos expresan dependencias de entrada y salida.
- REST y persistencia son adaptadores; los controladores traducen HTTP y no concentran reglas de negocio.
- Todo cambio de esquema requiere una migración Flyway y una justificación vinculada a la HU/regla afectada.

## Reglas funcionales sensibles

Preservar las reglas del PRD: no doble reserva ni solapamiento, slots de 30 minutos y consecutivos para duración de 60, validación de fechas futuras, habilitación de sede/especialidad/profesional, aprobación automática de citas generales, aprobación administrativa de especializadas, motivo obligatorio de rechazo, conservación de la cita original durante reprogramación y auditoría inmutable de estados.

Cuando una regla no defina estrategia concreta —por ejemplo, concurrencia, transiciones completas o contrato REST— documentar la pregunta para el orquestador; no decidirla por inferencia.

## Seguridad y datos

- Contraseñas con hash adaptativo compatible con Spring Security.
- Secretos solo mediante variables de entorno; no abrir, reproducir ni versionar `.env`.
- Nunca registrar passwords, JWT, refresh tokens o PII innecesaria.
- Aplicar autorización por rol y ownership, validación server-side y CORS explícito.
- Usar datos sintéticos; no introducir información privada real de FCV.

## Método de trabajo

1. Localizar HU, CA y DoD aprobados; si faltan, detener la implementación y reportarlo.
2. Identificar reglas, puertos/adaptadores, persistencia, seguridad y contrato afectados.
3. Proponer un plan antes de editar, con archivos, migraciones y pruebas.
4. Implementar el mínimo coherente dentro de este repositorio.
5. Ejecutar pruebas de dominio, aplicación y REST/persistencia relevantes.
6. Verificar independencia del dominio, migraciones, autorización y DoD.
7. Resumir evidencia, comandos ejecutados y elementos no verificados.

Los cambios cross-repo o de contrato REST se escalan al orquestador y requieren evidencia en ambos repositorios.

## Git y automatización

Trabajar en `develop` cuando exista; `main` representa puntos estables. No reescribir historial ni borrar evidencia. Los workflows n8n se versionan como JSON sin credenciales en `automations/n8n/` y no forman parte del núcleo funcional hasta S5/S6.
