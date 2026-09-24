---
id: HU-010
tipo: historia-de-usuario
titulo: Duración por especialidad
estado: Pendiente de aprobación
epica: "[[EP-003-disponibilidad-profesional]]"
esfuerzo: Medio
sprint_sugerido: S3 — Disponibilidad y cita general
dependencias: ["[[HU-007-catalogos-configurables]]", "[[HU-009-bloques-de-disponibilidad]]"]
relacionadas: ["[[HU-011-consulta-de-disponibilidad]]"]
fuentes: ["PRD RF-09", "PRD RN-05"]
---
# HU-010 — Duración por especialidad
## Historia de usuario
**COMO** ADMIN **QUIERO** definir si una especialidad dura 30 o 60 minutos **PARA** que las reservas requieran la franja correcta.
## Alcance
- Duración de 30/60 min y conversión a uno/dos slots consecutivos; profesional no la sobrescribe.
## Fuera de alcance
- Duraciones distintas de 30/60 minutos.
## Reglas de negocio
- 30 min = un slot; 60 min = dos slots consecutivos disponibles.
## Dependencias y relaciones
- Épica: [[EP-003-disponibilidad-profesional]]; depende de [[HU-007-catalogos-configurables]], [[HU-009-bloques-de-disponibilidad]]; relacionada: [[HU-011-consulta-de-disponibilidad]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** regla precisa que afecta disponibilidad y reserva.
## Tareas de desarrollo
- [ ] **T-01 — Incorporar duración en especialidad.** Dificultad: Medio.
- [ ] **T-02 — Aplicar conversión a slots en reglas de agenda.** Dificultad: Alto.
- [ ] **T-03 — Probar 30 min, 60 min y no sobrescritura profesional.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Configuración válida
Dado una especialidad, cuando ADMIN configura su duración, entonces solo puede seleccionar 30 o 60 minutos.
### CA-02 — Slots requeridos
Dado una especialidad de 30 o 60 minutos, cuando se calcula disponibilidad/reserva, entonces exige respectivamente uno o dos slots consecutivos.
### CA-03 — Fuente única
Dado un PROFESSIONAL asociado a una especialidad, cuando publica o atiende, entonces no puede sobrescribir su duración.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Regla usada consistentemente por disponibilidad y reserva.
- [ ] Migración Flyway/pruebas aplicables y trazabilidad actualizada.
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
- Ninguna adicional.
