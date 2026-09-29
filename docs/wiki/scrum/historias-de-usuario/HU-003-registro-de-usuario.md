---
id: HU-003
tipo: historia-de-usuario
titulo: Registro de usuario
estado: Completada
epica: "[[EP-001-fundacion-y-acceso]]"
esfuerzo: Alto
sprint_sugerido: S1 — Fundación segura
dependencias: ["[[HU-001-diseno-de-datos-3fn]]", "[[HU-002-catalogos-fijos]]"]
relacionadas: ["[[HU-004-sesion-jwt]]"]
fuentes: ["PRD RF-01", "PRD §8", "RESTRICCIONES_TECNICAS §Backend"]
---
# HU-003 — Registro de usuario
## Historia de usuario
**COMO** visitante **QUIERO** crear una cuenta USER con mis datos mínimos **PARA** acceder a las capacidades de citas.
## Alcance
- Nombres, apellidos, tipo/número de documento, email, teléfono y contraseña; rol USER.
## Fuera de alcance
- Creación de PROFESSIONAL por ADMIN y recuperación de contraseña.
## Reglas de negocio
- Email y documento únicos; password con hash adaptativo; datos sintéticos; validación server-side.
## Dependencias y relaciones
- Épica: [[EP-001-fundacion-y-acceso]]; depende de [[HU-001-diseno-de-datos-3fn]] y [[HU-002-catalogos-fijos]]; relacionada: [[HU-004-sesion-jwt]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** combina persistencia, seguridad, validaciones y una experiencia frontend/backend coherente.
## Tareas de desarrollo
- [x] **T-01 — Definir interacción de registro y validaciones.** Dificultad: Medio.
- [x] **T-02 — Implementar caso de uso con unicidad y hash seguro.** Dificultad: Alto.
- [x] **T-03 — Integrar contrato y pruebas de errores/éxito.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Registro válido
Dado un visitante con todos los datos mínimos válidos, cuando confirma el registro, entonces se crea una cuenta con rol USER sin almacenar la contraseña en texto plano.
### CA-02 — Unicidad
Dado un email o documento ya registrado, cuando se intenta registrar otra cuenta, entonces se informa el conflicto y no se crea una cuenta duplicada.
### CA-03 — Datos inválidos
Dado un dato obligatorio o formato inválido, cuando se envía el registro, entonces se muestran/restituyen errores verificables y no se persiste una cuenta parcial.
## Definition of Done
- [x] CA-01 a CA-03 validados con pruebas de dominio/aplicación y REST aplicables.
- [x] Password usa hash adaptativo y no se registra ni expone en logs/respuestas.
- [x] Validación server-side y autorización inicial coherentes con el contrato aprobado.
- [x] Trazabilidad Scrum actualizada.
## Evidencia de validación
| Elemento | Resultado | Evidencia | Observación |
|---|---|---|---|
| CA-01 | Cumple | `application/service/RegisterUserService.java` + `adapter/out/security/BCryptPasswordHasherAdapter.java`; tests `RegisterUserServiceTest.registersUserWithHashedPasswordAndUserRole`, `AuthControllerIntegrationTest.registerCreatesUserWithoutExposingPassword` (`mvn test`, 23/23 verde) | Rol `USER` asignado; `RegisterResponse` no expone password/hash |
| CA-02 | Cumple | `RegisterUserService` (`existsByEmail`/`existsByDocument` + `DataIntegrityRaceException` como red de seguridad ante condición de carrera) + constraints `uq_users_email`/`uq_users_document` en `V1__schema.sql`; tests `RegisterUserServiceTest.rejectsDuplicateEmail/rejectsDuplicateDocument/translatesRaceConditionOn*`, `AuthControllerIntegrationTest.registerRejectsDuplicateEmail/registerRejectsDuplicateDocument` | — |
| CA-03 | Cumple | Bean Validation en `adapter/in/web/dto/RegisterRequest.java` + `ApiExceptionHandler.handleValidation`; test `AuthControllerIntegrationTest.registerRejectsInvalidPayload` (400 con `errors[]`) | — |
| DoD | Cumple | Ver CA-01 a CA-03; BCrypt vía `PasswordEncoder` (Spring Security); inspección estática confirma que ningún log/DTO expone password/hash; contrato documentado en `llm-wiki/wiki/contracts.md` | — |
## Historial de validación
- 2026-09-17 — Creada en estado `Pendiente de aprobación`.
- 2026-09-29 — HU aprobada por el usuario (`Aprobada`) para implementar junto con [[HU-004-sesion-jwt]] (GOAL_01_GUIADO_SIMPLE).
- 2026-09-29 — Implementada y validada con evidencia (`mvn test`, 23/23 verde); HU pasa a `Completada`.
## Notas y decisiones
- Aprobado (2026-09-29): contrato `POST /api/v1/auth/register` (ver `llm-wiki/wiki/contracts.md`); el resto de rutas del sistema sigue pendiente de [[HU-022-contrato-rest-directo]].
