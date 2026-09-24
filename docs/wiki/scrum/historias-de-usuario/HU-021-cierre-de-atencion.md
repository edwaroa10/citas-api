---
id: HU-021
tipo: historia-de-usuario
titulo: Cierre de atención
estado: Pendiente de aprobación
epica: "[[EP-006-operacion-profesional-y-trazabilidad]]"
esfuerzo: Medio
sprint_sugerido: S6 — Operación y contrato
dependencias: ["[[HU-020-agenda-del-profesional]]", "[[HU-016-auditoria-de-estados]]"]
relacionadas: []
fuentes: ["PRD RF-17", "PRD RN-11"]
---
# HU-021 — Cierre de atención
## Historia de usuario
**COMO** PROFESSIONAL **QUIERO** marcar una cita pasada/aplicable como COMPLETED o NO_SHOW **PARA** reflejar el resultado de la atención.
## Alcance
- Transición a COMPLETED/NO_SHOW e historial.
## Fuera de alcance
- Definir sin aprobación la condición exacta de “aplicable”.
## Reglas de negocio
- Debe registrarse historial; solo cita pasada/aplicable.
## Dependencias y relaciones
- Épica: [[EP-006-operacion-profesional-y-trazabilidad]]; depende de [[HU-020-agenda-del-profesional]], [[HU-016-auditoria-de-estados]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** transición de estado con autorización y criterio pendiente.
## Tareas de desarrollo
- [ ] **T-01 — Aprobar definición de pasada/aplicable y transiciones.** Dificultad: Medio.
- [ ] **T-02 — Implementar cierre con ownership e historial.** Dificultad: Alto.
- [ ] **T-03 — Probar COMPLETED, NO_SHOW, cita no aplicable y ajena.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Cierre válido
Dado una cita propia pasada/aplicable según la regla aprobada, cuando PROFESSIONAL la marca, entonces queda COMPLETED o NO_SHOW.
### CA-02 — Restricción
Dado una cita ajena o no aplicable, cuando PROFESSIONAL intenta cerrarla, entonces su estado no cambia.
### CA-03 — Historial
Dado un cierre, cuando se revisa auditoría, entonces registra el cambio con actor y fuente aplicables.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Regla de aplicabilidad y matriz de transiciones aprobadas/documentadas.
- [ ] Ownership, auditoría y pruebas aplicables completos; trazabilidad actualizada.
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
- Pendiente: condición precisa de cita aplicable.
