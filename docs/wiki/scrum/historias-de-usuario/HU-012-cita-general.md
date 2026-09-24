---
id: HU-012
tipo: historia-de-usuario
titulo: Cita general
estado: Pendiente de aprobación
epica: "[[EP-004-ciclo-de-citas]]"
esfuerzo: Alto
sprint_sugerido: S3 — Disponibilidad y cita general
dependencias: ["[[HU-011-consulta-de-disponibilidad]]"]
relacionadas: ["[[HU-014-mis-citas]]", "[[HU-016-auditoria-de-estados]]"]
fuentes: ["PRD RF-11", "PRD RN-01", "PRD RN-02"]
---
# HU-012 — Cita general
## Historia de usuario
**COMO** USER **QUIERO** confirmar una cita de Medicina General **PARA** obtener atención en una franja disponible.
## Alcance
- Selección de profesional general y horario; creación autoaprobada si sigue disponible.
## Fuera de alcance
- Aprobación ADMIN y especialidades distintas a Medicina General.
## Reglas de negocio
- No doble reserva; estado inicial APPROVED; no intervención ADMIN.
## Dependencias y relaciones
- Épica: [[EP-004-ciclo-de-citas]]; depende de [[HU-011-consulta-de-disponibilidad]]; relacionadas: [[HU-014-mis-citas]], [[HU-016-auditoria-de-estados]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** reserva transaccional, validación final y auditoría.
## Tareas de desarrollo
- [ ] **T-01 — Definir confirmación y respuesta ante franja ya tomada.** Dificultad: Medio.
- [ ] **T-02 — Implementar reserva con validación final e historial.** Dificultad: Alto.
- [ ] **T-03 — Probar autoaprobación y conflictos simultáneos conforme a decisión aprobada.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Confirmación
Dado Medicina General, profesional general y horario disponible, cuando USER confirma, entonces se crea la cita en estado APPROVED.
### CA-02 — Franja no disponible
Dado una franja que dejó de estar disponible antes de confirmar, cuando USER confirma, entonces no se crea una doble reserva y recibe un resultado verificable.
### CA-03 — Sin ADMIN
Dado una cita general creada, cuando se revisa su ciclo inicial, entonces no requiere decisión ADMIN para APPROVED.
## Definition of Done
- [ ] CA-01 a CA-03 validados; la estrategia de concurrencia aprobada tiene evidencia.
- [ ] Reserva, slots y auditoría inicial son coherentes con el modelo y tienen pruebas.
- [ ] Integración REST/UI maneja éxito y conflicto; trazabilidad actualizada.
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
- Bloqueada para desarrollo si no se aprueba la estrategia de concurrencia.
