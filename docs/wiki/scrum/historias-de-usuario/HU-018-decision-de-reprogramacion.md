---
id: HU-018
tipo: historia-de-usuario
titulo: Decisión de reprogramación
estado: Pendiente de aprobación
epica: "[[EP-005-reprogramacion-y-operacion-administrativa]]"
esfuerzo: Alto
sprint_sugerido: S5 — Reprogramación y gestión
dependencias: ["[[HU-017-solicitud-de-reprogramacion]]", "[[HU-016-auditoria-de-estados]]"]
relacionadas: ["[[HU-019-bandeja-administrativa]]"]
fuentes: ["PRD RF-15", "PRD RN-04", "PRD RN-09", "PRD RN-10"]
---
# HU-018 — Decisión de reprogramación
## Historia de usuario
**COMO** ADMIN **QUIERO** aprobar o rechazar una reprogramación pendiente **PARA** actualizar la cita sin perder reservas indebidamente.
## Alcance
- Aprobación mueve cita a nueva franja y libera antigua; rechazo libera provisional y conserva original; motivo cuando corresponda.
## Fuera de alcance
- Reprogramar cambiando profesional.
## Reglas de negocio
- PENDING solo; rechazo permite conservar o cancelar original posteriormente.
## Dependencias y relaciones
- Épica: [[EP-005-reprogramacion-y-operacion-administrativa]]; depende de [[HU-017-solicitud-de-reprogramacion]], [[HU-016-auditoria-de-estados]]; relacionada: [[HU-019-bandeja-administrativa]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** transición coordinada de dos franjas y auditoría.
## Tareas de desarrollo
- [ ] **T-01 — Implementar decisión autorizada de PENDING.** Dificultad: Alto.
- [ ] **T-02 — Coordinar liberación/asignación sin pérdida de original.** Dificultad: Alto.
- [ ] **T-03 — Probar aprobación, rechazo, motivo e historial.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Aprobación
Dado una reprogramación PENDING, cuando ADMIN aprueba, entonces se liberan slots antiguos, se asignan nuevos y se actualiza la cita.
### CA-02 — Rechazo
Dado una reprogramación PENDING, cuando ADMIN rechaza con el motivo requerido, entonces se libera la reserva provisional y la cita original permanece intacta.
### CA-03 — Control de estado
Dado una solicitud que no está PENDING o actor no ADMIN, cuando intenta decidirla, entonces no se cambia ninguna franja ni cita.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas transaccionales aplicables.
- [ ] Motivo, actor, fuente y cambios se auditan mediante [[HU-016-auditoria-de-estados]].
- [ ] Contrato/UI separan aprobación, rechazo y errores; trazabilidad actualizada.
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
- “Motivo cuando corresponda” requiere precisar los casos antes de implementación.
