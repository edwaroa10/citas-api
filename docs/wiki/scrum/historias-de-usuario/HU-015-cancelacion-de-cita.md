---
id: HU-015
tipo: historia-de-usuario
titulo: Cancelación de cita
estado: Pendiente de aprobación
epica: "[[EP-004-ciclo-de-citas]]"
esfuerzo: Medio
sprint_sugerido: S4 — Cita especializada
dependencias: ["[[HU-014-mis-citas]]"]
relacionadas: ["[[HU-016-auditoria-de-estados]]"]
fuentes: ["PRD RF-14", "PRD RN-09", "PRD RN-11"]
---
# HU-015 — Cancelación de cita
## Historia de usuario
**COMO** USER **QUIERO** cancelar una cita futura no terminal **PARA** liberar su franja sin perder la trazabilidad.
## Alcance
- Cancelación, estado CANCELLED, liberación de slots e historial.
## Fuera de alcance
- Reactivar directamente una cita cancelada.
## Reglas de negocio
- Solo futura/no terminal; CANCELLED libera slots y no se reactiva directamente.
## Dependencias y relaciones
- Épica: [[EP-004-ciclo-de-citas]]; depende de [[HU-014-mis-citas]]; relacionada: [[HU-016-auditoria-de-estados]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** transición autorizada con impacto en disponibilidad y auditoría.
## Tareas de desarrollo
- [ ] **T-01 — Definir elegibilidad de cancelación conforme a estados aprobados.** Dificultad: Medio.
- [ ] **T-02 — Implementar transición, liberación y auditoría.** Dificultad: Alto.
- [ ] **T-03 — Probar pasado, terminal, ownership y no reactivación.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Cancelación permitida
Dado una cita propia, futura y no terminal, cuando USER la cancela, entonces queda CANCELLED y sus slots se liberan.
### CA-02 — Cancelación impedida
Dado una cita pasada, terminal o ajena, cuando USER intenta cancelarla, entonces no cambia su estado ni disponibilidad.
### CA-03 — Trazabilidad
Dado una cancelación, cuando se revisa el historial, entonces existe el cambio de estado con actor/fuente aplicable.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Transición, liberación, ownership y auditoría tienen pruebas relevantes.
- [ ] La matriz de estados aprobada incluye la elegibilidad aplicada; trazabilidad actualizada.
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
- La definición exhaustiva de estado terminal requiere aprobación.
