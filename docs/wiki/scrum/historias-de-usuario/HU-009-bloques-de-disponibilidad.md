---
id: HU-009
tipo: historia-de-usuario
titulo: Bloques de disponibilidad
estado: Pendiente de aprobación
epica: "[[EP-003-disponibilidad-profesional]]"
esfuerzo: Alto
sprint_sugerido: S3 — Disponibilidad y cita general
dependencias: ["[[HU-008-profesionales]]"]
relacionadas: ["[[HU-010-duracion-por-especialidad]]", "[[HU-011-consulta-de-disponibilidad]]"]
fuentes: ["PRD RF-08", "PRD RN-06", "PRD RN-07"]
---
# HU-009 — Bloques de disponibilidad
## Historia de usuario
**COMO** PROFESSIONAL **QUIERO** administrar mis bloques futuros por sede y consultar mi calendario **PARA** publicar mi disponibilidad de atención.
## Alcance
- Múltiples bloques por día, sede por bloque, editar/eliminar futuros sin citas comprometidas, calendario y slots de 30 min.
## Fuera de alcance
- Reservar citas o editar bloques comprometidos.
## Reglas de negocio
- No pasado, no solapamiento del mismo profesional y sede previamente asignada.
## Dependencias y relaciones
- Épica: [[EP-003-disponibilidad-profesional]]; depende de [[HU-008-profesionales]]; relacionadas: [[HU-010-duracion-por-especialidad]], [[HU-011-consulta-de-disponibilidad]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** validación temporal, ownership, solapamiento y dependencia de citas.
## Tareas de desarrollo
- [ ] **T-01 — Definir modelo de bloque y discretización.** Dificultad: Alto.
- [ ] **T-02 — Implementar calendario y operaciones con ownership.** Dificultad: Alto.
- [ ] **T-03 — Probar pasado, solapamientos, sede y bloque comprometido.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Creación válida
Dado un PROFESSIONAL habilitado en una sede, cuando crea un bloque futuro sin solapamiento, entonces el bloque aparece en su calendario discretizado en slots de 30 minutos.
### CA-02 — Rechazo de bloque inválido
Dado un bloque pasado, solapado o para sede no asignada, cuando intenta guardarlo, entonces se rechaza y el calendario previo no cambia.
### CA-03 — Edición protegida
Dado un bloque futuro sin citas comprometidas, cuando lo edita/elimina, entonces se actualiza; si tiene citas comprometidas, la operación se impide.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas de reglas temporales y ownership.
- [ ] Persistencia/migración Flyway y consulta de calendario son coherentes con 3FN.
- [ ] Integración frontend/backend usa contrato aprobado y muestra estados de error.
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
- “Cita comprometida” debe quedar alineada con la matriz de estados aprobada.
