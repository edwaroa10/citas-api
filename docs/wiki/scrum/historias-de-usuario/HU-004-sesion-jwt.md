---
id: HU-004
tipo: historia-de-usuario
titulo: Sesión JWT
estado: Completada
epica: "[[EP-001-fundacion-y-acceso]]"
esfuerzo: Alto
sprint_sugerido: S1 — Fundación segura
dependencias: ["[[HU-003-registro-de-usuario]]"]
relacionadas: ["[[HU-005-recuperacion-de-contrasena]]", "[[HU-022-contrato-rest-directo]]"]
fuentes: ["PRD RF-02", "PRD §8", "RESTRICCIONES_TECNICAS §Backend"]
---
# HU-004 — Sesión JWT
## Historia de usuario
**COMO** usuario registrado **QUIERO** iniciar, renovar y cerrar mi sesión **PARA** acceder con seguridad según mi rol.
## Alcance
- Login email/contraseña, access token de corta duración, refresh token, refresh, revocación/logout y contexto de roles.
## Fuera de alcance
- Recuperación/cambio de contraseña.
## Reglas de negocio
- Access y refresh son separados; secretos solo por entorno; no registrar tokens ni passwords.
## Dependencias y relaciones
- Épica: [[EP-001-fundacion-y-acceso]]; depende de [[HU-003-registro-de-usuario]]; relacionada: [[HU-005-recuperacion-de-contrasena]], [[HU-022-contrato-rest-directo]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** es transversal a autorización, tokens y protección de datos.
## Tareas de desarrollo
- [x] **T-01 — Definir flujo de sesión y almacenamiento seguro según decisión aprobada.** Dificultad: Alto.
- [x] **T-02 — Implementar autenticación, emisión, refresh y revocación.** Dificultad: Alto.
- [x] **T-03 — Probar credenciales, expiración, refresh, logout y rol.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Inicio de sesión
Dado credenciales válidas, cuando un usuario inicia sesión, entonces recibe credenciales de sesión separadas y un contexto de autorización con su rol.
### CA-02 — Renovación y revocación
Dado un refresh token válido, cuando solicita renovación, entonces obtiene una sesión renovada; tras logout/revocación, ese token no permite renovar.
### CA-03 — Protección
Dado credenciales inválidas, token inválido o acceso sin rol/ownership, cuando se intenta operar, entonces se rechaza sin revelar secretos.
## Definition of Done
- [x] CA-01 a CA-03 validados con pruebas relevantes.
- [x] Access/refresh, secretos por entorno y CORS explícito cumplen las restricciones aplicables.
- [x] No hay tokens ni passwords en logs, fixtures o respuestas no autorizadas.
- [x] Trazabilidad Scrum actualizada.
## Evidencia de validación
| Elemento | Resultado | Evidencia | Observación |
|---|---|---|---|
| CA-01 | Cumple | `application/service/LoginService.java` + `adapter/out/security/JjwtAccessTokenIssuerAdapter.java` (JWT con claim `roles`); tests `LoginServiceTest.issuesAccessAndRefreshTokensOnValidCredentials`, `AuthControllerIntegrationTest.loginRefreshAndLogoutFullFlow` | Access y refresh viajan separados en el body (`TokenResponse`) |
| CA-02 | Cumple | `RefreshSessionService` (rotación: revoca el token usado y emite uno nuevo) + `LogoutService` (revocación); tests `RefreshSessionServiceTest.rotatesRefreshTokenAndIssuesNewAccessToken` y sus 4 casos negativos, `AuthControllerIntegrationTest.loginRefreshAndLogoutFullFlow` (reintento tras refresh → 401; refresh tras logout → 401) | — |
| CA-03 | Cumple | `ApiExceptionHandler` mapea `InvalidCredentialsException`/`InvalidRefreshTokenException` a 401 sin detalle interno; `JwtAuthenticationFilter` + `SecurityConfig` (`anyRequest().authenticated()`); tests `LoginServiceTest.rejectsWrongPassword/rejectsUnknownEmail/rejectsInactiveUser`, `RefreshSessionServiceTest.rejectsUnknownToken/rejectsExpiredToken/rejectsRevokedToken/rejectsWhenUserNoLongerExistsOrIsInactive`, `AuthControllerIntegrationTest.loginRejectsWrongPassword` | — |
| DoD | Cumple | `config/JwtProperties.java` lee `JWT_ACCESS_SECRET`/`JWT_REFRESH_SECRET`/`JWT_ACCESS_MINUTES`/`JWT_REFRESH_DAYS` por entorno; `SecurityConfig` restringe CORS a `FRONTEND_ORIGIN`; inspección estática confirma que ningún log/DTO expone tokens o passwords; `mvn test` 23/23 verde | — |
## Historial de validación
- 2026-09-17 — Creada en estado `Pendiente de aprobación`.
- 2026-09-29 — HU aprobada por el usuario (`Aprobada`); mecanismo de transporte de tokens decidido (ver Notas).
- 2026-09-29 — Implementada y validada con evidencia (`mvn test`, 23/23 verde); HU pasa a `Completada`.
## Notas y decisiones
- Aprobado (2026-09-29): duraciones desde `.env` (`JWT_ACCESS_MINUTES=15`, `JWT_REFRESH_DAYS=7`). Access token JWT firmado HS256 con claims de rol; refresh token opaco (no JWT), almacenado con hash HMAC-SHA256 (clave `JWT_REFRESH_SECRET`) en `refresh_tokens`, transportado como JSON en el body de `POST /api/v1/auth/refresh` (no cookie). Cada refresh rota el token (revoca el usado, emite uno nuevo).
