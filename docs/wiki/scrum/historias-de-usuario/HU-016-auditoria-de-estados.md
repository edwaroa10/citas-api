---
id: HU-016
tipo: historia-de-usuario
titulo: Auditoría de estados
estado: Pendiente de aprobación
epica: "[[EP-006-operacion-profesional-y-trazabilidad]]"
esfuerzo: Alto
sprint_sugerido: S4 — Cita especializada
dependencias: ["[[HU-012-cita-general]]", "[[HU-013-cita-especializada]]"]
relacionadas: ["[[HU-015-cancelacion-de-cita]]", "[[HU-018-decision-de-reprogramacion]]", "[[HU-021-cierre-de-atencion]]"]
fuentes: ["PRD RF-19", "PRD RN-11", "PRD RN-12"]
---
# HU-016 — Auditoría de estados
## Historia de usuario
**COMO** ADMIN **QUIERO** consultar un historial inmutable de cambios de estado **PARA** verificar quién y cómo cambió cada cita.
## Alcance
- Cita, estado nuevo, actor si existe, fuente SYSTEM/USER/ADMIN, fecha/hora y motivo opcional.
## Fuera de alcance
- CRUD normal para editar/borrar auditoría.
## Reglas de negocio
- Toda transición es explícita y verificable; auditoría no se modifica como CRUD.
## Dependencias y relaciones
- Épica: [[EP-006-operacion-profesional-y-trazabilidad]]; depende de [[HU-012-cita-general]], [[HU-013-cita-especializada]]; relacionadas: [[HU-015-cancelacion-de-cita]], [[HU-018-decision-de-reprogramacion]], [[HU-021-cierre-de-atencion]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** componente transversal y garantía de inmutabilidad.
## Tareas de desarrollo
- [ ] **T-01 — Acordar matriz de transiciones y fuentes.** Dificultad: Alto.
- [ ] **T-02 — Registrar cada transición como historial inmutable.** Dificultad: Alto.
- [ ] **T-03 — Probar cobertura de cambios y prohibición de edición.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Registro completo
Dado un cambio de estado de cita, cuando ocurre, entonces registra cita, estado nuevo, actor cuando existe, fuente, fecha/hora y motivo opcional.
### CA-02 — Inmutabilidad
Dado un registro de historial, cuando se intenta modificarlo como CRUD normal, entonces no se altera.
### CA-03 — Cobertura
Dado una transición de creación, decisión, cancelación, reprogramación o cierre aplicable, cuando se verifica, entonces tiene historial correspondiente.
## Definition of Done
- [ ] CA-01 a CA-03 validados para los flujos implementados.
- [ ] Matriz de transiciones aprobada/documentada; persistencia 3FN y Flyway aplicables verificadas.
- [ ] Pruebas relevantes prueban inmutabilidad y completitud; trazabilidad actualizada.
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
- Pendiente: matriz completa de transiciones y distinción de SYSTEM/actor ausente.
