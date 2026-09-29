---
id: HU-021
tipo: historia-de-usuario
titulo: Cierre de atención
estado: Aprobada
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
- Matriz completa de transiciones de estado más allá de COMPLETED/NO_SHOW.
## Reglas de negocio
- Debe registrarse historial; una cita se considera "pasada/aplicable" automáticamente al llegar la hora de fin de su slot agendado.
## Dependencias y relaciones
- Épica: [[EP-006-operacion-profesional-y-trazabilidad]]; depende de [[HU-020-agenda-del-profesional]], [[HU-016-auditoria-de-estados]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** transición de estado con autorización y criterio pendiente.
## Tareas de desarrollo
- [ ] **T-01 — Implementar cálculo de "pasada/aplicable" al fin del slot.** Dificultad: Bajo.
- [ ] **T-02 — Implementar cierre con ownership e historial.** Dificultad: Alto.
- [ ] **T-03 — Probar COMPLETED, NO_SHOW, cita no aplicable y ajena.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Cierre válido
Dado una cita propia APPROVED cuya hora de fin de slot ya transcurrió, cuando PROFESSIONAL la marca, entonces queda COMPLETED o NO_SHOW.
### CA-02 — Restricción
Dado una cita ajena o no aplicable, cuando PROFESSIONAL intenta cerrarla, entonces su estado no cambia.
### CA-03 — Historial
Dado un cierre, cuando se revisa auditoría, entonces registra el cambio con actor y fuente aplicables.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Regla de aplicabilidad (fin de slot) implementada; matriz completa de transiciones de estado, si aplica más allá de COMPLETED/NO_SHOW, queda fuera de esta HU.
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
- 2026-09-28 — Condición de "pasada/aplicable" aprobada por el usuario (ver Notas y decisiones).
- 2026-09-28 — HU aprobada por el usuario (`Aprobada`).
## Notas y decisiones
- Aprobado (2026-09-28): "pasada/aplicable" = hora de fin del slot agendado ya transcurrida, sin margen de tolerancia adicional.
