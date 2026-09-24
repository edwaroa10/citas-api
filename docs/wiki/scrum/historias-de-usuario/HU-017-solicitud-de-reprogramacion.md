---
id: HU-017
tipo: historia-de-usuario
titulo: Solicitud de reprogramación
estado: Pendiente de aprobación
epica: "[[EP-005-reprogramacion-y-operacion-administrativa]]"
esfuerzo: Alto
sprint_sugerido: S5 — Reprogramación y gestión
dependencias: ["[[HU-014-mis-citas]]", "[[HU-011-consulta-de-disponibilidad]]"]
relacionadas: ["[[HU-018-decision-de-reprogramacion]]"]
fuentes: ["PRD RF-15", "PRD RN-10"]
---
# HU-017 — Solicitud de reprogramación
## Historia de usuario
**COMO** USER **QUIERO** solicitar otra fecha/hora para una cita aprobada y futura **PARA** intentar reprogramarla sin perder la cita original.
## Alcance
- Nueva franja disponible, conserva profesional/especialidad, solicitud PENDING y retención provisional.
## Fuera de alcance
- Cambiar profesional; eso es una nueva cita.
## Reglas de negocio
- Original conserva su franja hasta la decisión ADMIN; solo APPROVED y futura.
## Dependencias y relaciones
- Épica: [[EP-005-reprogramacion-y-operacion-administrativa]]; depende de [[HU-014-mis-citas]], [[HU-011-consulta-de-disponibilidad]]; relacionada: [[HU-018-decision-de-reprogramacion]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** dos franjas con retención y estados que deben preservar datos.
## Tareas de desarrollo
- [ ] **T-01 — Definir elegibilidad y entidad de solicitud.** Dificultad: Alto.
- [ ] **T-02 — Implementar retención de nueva franja sin tocar original.** Dificultad: Alto.
- [ ] **T-03 — Probar conservación, profesional/especialidad y conflictos.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Solicitud válida
Dado una cita propia APPROVED y futura, cuando USER elige una nueva franja completa del mismo profesional/especialidad, entonces se crea reprogramación PENDING y se retiene la nueva franja.
### CA-02 — Original preservada
Dado una reprogramación PENDING, cuando se consulta la cita original, entonces conserva su franja original hasta una decisión ADMIN.
### CA-03 — Restricciones
Dado cita no aprobada/no futura, cambio de profesional o nueva franja no disponible, cuando USER intenta solicitar, entonces no se crea una reprogramación válida.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Retención y concurrencia obedecen decisión aprobada y tienen pruebas.
- [ ] Modelo/Flyway preserva cita original y trazabilidad actualizada.
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
- Vencimiento de retención provisional pendiente.
