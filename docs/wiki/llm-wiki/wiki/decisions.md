# Decisiones

## Aprobadas

- **2026-09-28 — Concurrencia/doble reserva (RN-01):** restricción única en BD por slot confirmado + retención `HELD` con expiración automática de 30 minutos.
- **2026-09-28 — Vencimiento de retenciones:** 30 minutos desde `REQUESTED`/`PENDING` sin decisión ADMIN.
- **2026-09-28 — Cierre de atención (RF-17):** una cita es "pasada/aplicable" automáticamente al llegar la hora de fin de su slot.
- **2026-09-28 — Entrega del token de recuperación en desarrollo (RF-03):** respuesta de API condicionada a perfil de entorno de desarrollo; nunca en producción/logs.
- **2026-09-28 — Framework de frontend:** React + Vite + TypeScript.
- **2026-09-29 — Stack backend inicial ejecutado:** Spring Boot 3.5.0 + Java 21 + Flyway (`V1__schema`, `V2__seed_catalogos_fijos`, `V3__seed_datos_sinteticos`) aplicados sobre MySQL 8.4 (`citas_fcv_training`).
- **2026-09-29 — Frontend importado:** export de Google AI Studio (React 19 + Vite 8 + Tailwind v4) reconciliado en `citas-web`, sin Express/`@google/genai` (no usados en el código fuente). Prototipo visual con datos simulados; sin integración REST todavía.
- **2026-09-29 — Contrato REST de autenticación:** ver [[contracts.md]] — `POST /api/v1/auth/{register,login,refresh,logout}`.
- **2026-09-29 — Transporte de tokens JWT:** access token JWT HS256 con claim de rol, duración `JWT_ACCESS_MINUTES`; refresh token opaco con hash HMAC-SHA256 (clave `JWT_REFRESH_SECRET`) en `refresh_tokens`, duración `JWT_REFRESH_DAYS`, transportado como JSON en el body (no cookie), con rotación en cada refresh.

## Pendientes

- Matriz completa de transiciones de estado de cita más allá de lo cubierto por RF-11–RF-17.
- Qué ocurre con el estado `REQUESTED`/`PENDING` de la solicitud cuando su retención expira sin decisión ADMIN (ver [[HU-013-cita-especializada]], [[HU-018-decision-de-reprogramacion]]).
- Rutas/DTO/versionado del resto del contrato REST (fuera del slice de auth) — [[HU-022-contrato-rest-directo]].
- Afiliaciones (duplicidad EPS/plan/régimen), formato de errores fuera del slice de auth, autenticación de webhooks n8n, bootstrap Git.
