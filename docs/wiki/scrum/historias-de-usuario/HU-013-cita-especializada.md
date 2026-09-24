---
id: HU-013
tipo: historia-de-usuario
titulo: Solicitud de cita especializada
estado: Pendiente de aprobación
epica: "[[EP-004-ciclo-de-citas]]"
esfuerzo: Alto
sprint_sugerido: S4 — Cita especializada
dependencias: ["[[HU-011-consulta-de-disponibilidad]]", "[[HU-012-cita-general]]"]
relacionadas: ["[[HU-019-bandeja-administrativa]]", "[[HU-016-auditoria-de-estados]]"]
fuentes: ["PRD RF-12", "PRD RN-01", "PRD RN-03", "PRD RN-04"]
---
# HU-013 — Solicitud de cita especializada
## Historia de usuario
**COMO** USER **QUIERO** solicitar una cita especializada con sede, profesional y horario **PARA** que ADMIN decida su aprobación.
## Alcance
- Solicitud REQUESTED y retención de horario; aprobación/rechazo posterior por ADMIN.
## Fuera de alcance
- Bandeja y decisión UI de ADMIN ([[HU-019-bandeja-administrativa]]).
## Reglas de negocio
- Inicia REQUESTED, retiene slots; aprobar→APPROVED; rechazar exige motivo y libera slots.
## Dependencias y relaciones
- Épica: [[EP-004-ciclo-de-citas]]; depende de [[HU-011-consulta-de-disponibilidad]], [[HU-012-cita-general]]; relacionadas: [[HU-019-bandeja-administrativa]], [[HU-016-auditoria-de-estados]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** reserva retenida, estados y operación administrativa.
## Tareas de desarrollo
- [ ] **T-01 — Definir solicitud especializada y retención.** Dificultad: Alto.
- [ ] **T-02 — Implementar validación de oferta, slots y estado REQUESTED.** Dificultad: Alto.
- [ ] **T-03 — Probar conflicto, retención y motivo de rechazo.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Solicitud válida
Dado especialidad activa, sede, profesional y franja completa disponibles, cuando USER solicita, entonces se crea REQUESTED y se retienen sus slots.
### CA-02 — Decisión administrativa
Dado una solicitud REQUESTED, cuando ADMIN aprueba, entonces queda APPROVED; cuando rechaza con motivo, queda REJECTED y libera slots.
### CA-03 — Rechazo protegido
Dado una solicitud inválida o franja ya retenida/reservada, cuando USER la envía, entonces no se crea una solicitud conflictiva.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas de dominio, aplicación y REST/persistencia aplicables.
- [ ] Retención/concurrencia aprobada y auditada; motivo de rechazo se conserva.
- [ ] UI/contrato cubren éxito, conflicto y error; trazabilidad actualizada.
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
- El vencimiento de una retención está pendiente.
