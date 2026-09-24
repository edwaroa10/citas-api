---
id: HU-004
tipo: historia-de-usuario
titulo: Sesión JWT
estado: Pendiente de aprobación
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
- [ ] **T-01 — Definir flujo de sesión y almacenamiento seguro según decisión aprobada.** Dificultad: Alto.
- [ ] **T-02 — Implementar autenticación, emisión, refresh y revocación.** Dificultad: Alto.
- [ ] **T-03 — Probar credenciales, expiración, refresh, logout y rol.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Inicio de sesión
Dado credenciales válidas, cuando un usuario inicia sesión, entonces recibe credenciales de sesión separadas y un contexto de autorización con su rol.
### CA-02 — Renovación y revocación
Dado un refresh token válido, cuando solicita renovación, entonces obtiene una sesión renovada; tras logout/revocación, ese token no permite renovar.
### CA-03 — Protección
Dado credenciales inválidas, token inválido o acceso sin rol/ownership, cuando se intenta operar, entonces se rechaza sin revelar secretos.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas relevantes.
- [ ] Access/refresh, secretos por entorno y CORS explícito cumplen las restricciones aplicables.
- [ ] No hay tokens ni passwords en logs, fixtures o respuestas no autorizadas.
- [ ] Trazabilidad Scrum actualizada.
## Evidencia de validación
| Elemento | Resultado | Evidencia | Observación |
|---|---|---|---|
| CA-01 | Pendiente | — | — |
| CA-02 | Pendiente | — | — |
| CA-03 | Pendiente | — | — |
| DoD | Pendiente | — | — |
## Historial de validación
- 2026-09-17 — Creada en estado `Pendiente de aprobación`.
## Notas y decisiones
- Duraciones y mecanismo concreto de transporte de tokens no están definidos por fuentes.
