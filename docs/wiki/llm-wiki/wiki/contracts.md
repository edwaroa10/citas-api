# Contratos REST

## Estado

Contrato global todavía no aprobado en su totalidad (ver [[HU-022-contrato-rest-directo]]: rutas/DTO/versionado del resto del sistema siguen pendientes). Se aprobó únicamente el slice de autenticación descrito abajo, para implementar [[HU-003-registro-de-usuario]] y [[HU-004-sesion-jwt]].

## Auth — aprobado (2026-09-29)

Base path: `/api/v1/auth`. JSON sobre HTTPS/HTTP según entorno; sin Express/BFF; CORS explícito restringido a `FRONTEND_ORIGIN`.

### `POST /api/v1/auth/register`
- Request: `{ firstName, lastName, documentType, documentNumber, email, phone, password }` (todos obligatorios).
- 201: `{ id, firstName, lastName, email, roles: ["USER"] }`.
- 409: email o documento ya registrados.
- 400: validación de campos.

### `POST /api/v1/auth/login`
- Request: `{ email, password }`.
- 200: `{ accessToken, refreshToken, tokenType: "Bearer", expiresInSeconds, roles: [...] }`.
- 401: credenciales inválidas.

### `POST /api/v1/auth/refresh`
- Request: `{ refreshToken }` (JSON en el body; no cookie — decisión explícita para evitar exigir HTTPS/SameSite=None en desarrollo local).
- 200: mismo shape que login; el refresh usado queda revocado (rotación) y se emite un refresh nuevo.
- 401: refresh inválido, expirado o ya revocado.

### `POST /api/v1/auth/logout`
- Request: `{ refreshToken }`.
- 204: el refresh token queda revocado (idempotente).

### Errores
Shape común: `{ status, error, message, errors?: [{ field, message }] }`.

## Evidencia requerida

Cada contrato aprobado debe enlazar HU/CA/DoD, controlador y caso de uso backend, pruebas, cliente frontend, estados de UI y estrategia de compatibilidad. Para el slice de auth: controlador `AuthController`, casos de uso `RegisterUserUseCase`/`LoginUseCase`/`RefreshSessionUseCase`/`LogoutUseCase`, pruebas en `src/test/java` de `citas-api`. Cliente frontend aún no integrado (`citas-web` sigue en prototipo con datos simulados).
