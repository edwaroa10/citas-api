---
id: HU-020
tipo: historia-de-usuario
titulo: Agenda visible al profesional
estado: Pendiente de aprobación
epica: "[[EP-006-operacion-profesional-y-trazabilidad]]"
esfuerzo: Medio
sprint_sugerido: S6 — Operación y contrato
dependencias: ["[[HU-012-cita-general]]", "[[HU-013-cita-especializada]]"]
relacionadas: ["[[HU-021-cierre-de-atencion]]"]
fuentes: ["PRD RF-16"]
---
# HU-020 — Agenda visible al profesional
## Historia de usuario
**COMO** PROFESSIONAL **QUIERO** consultar mis citas APPROVED por día/semana y sede **PARA** organizar mi atención.
## Alcance
- Agenda de propias citas APPROVED, filtros día/semana y sede.
## Fuera de alcance
- Datos de usuarios ajenos o gestión de sus citas.
## Reglas de negocio
- Profesional no ve datos de usuarios fuera de sus propias citas.
## Dependencias y relaciones
- Épica: [[EP-006-operacion-profesional-y-trazabilidad]]; depende de [[HU-012-cita-general]], [[HU-013-cita-especializada]]; relacionada: [[HU-021-cierre-de-atencion]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** vistas temporales con una restricción estricta de ownership.
## Tareas de desarrollo
- [ ] **T-01 — Definir vista día/semana/sede y campos permitidos.** Dificultad: Medio.
- [ ] **T-02 — Implementar consulta por ownership.** Dificultad: Alto.
- [ ] **T-03 — Probar aislamiento, estado APPROVED y filtros.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Agenda propia
Dado un PROFESSIONAL autenticado, cuando consulta su agenda, entonces ve solo sus citas APPROVED.
### CA-02 — Filtros temporales
Dado citas en días/semanas/sedes diferentes, cuando elige día, semana o sede, entonces el resultado respeta el filtro.
### CA-03 — Privacidad
Dado un profesional, cuando intenta acceder a datos de citas ajenas, entonces no se le muestran.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Ownership y minimización de datos probados.
- [ ] UI/REST muestran estados aplicables; trazabilidad actualizada.
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
- Campos mínimos visibles al profesional no se detallan en fuentes.
