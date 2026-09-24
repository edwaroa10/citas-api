---
id: HU-008
tipo: historia-de-usuario
titulo: Gestión de profesionales
estado: Pendiente de aprobación
epica: "[[EP-002-perfiles-y-catalogos]]"
esfuerzo: Alto
sprint_sugerido: S2 — Administración de oferta
dependencias: ["[[HU-002-catalogos-fijos]]", "[[HU-007-catalogos-configurables]]"]
relacionadas: ["[[HU-009-bloques-de-disponibilidad]]"]
fuentes: ["PRD RF-07", "PRD RN-07", "PRD RN-08"]
---
# HU-008 — Gestión de profesionales
## Historia de usuario
**COMO** ADMIN **QUIERO** crear y configurar profesionales ficticios con especialidades y sedes **PARA** habilitar la oferta reservable.
## Alcance
- Usuario PROFESSIONAL, código/matrícula ficticia, especialidades N:M con primaria, sedes asignadas y activación.
## Fuera de alcance
- Auto-registro profesional y gestión de bloques.
## Reglas de negocio
- Profesional puede tener varias especialidades y una primaria; una o ambas sedes; datos sintéticos.
## Dependencias y relaciones
- Épica: [[EP-002-perfiles-y-catalogos]]; depende de [[HU-002-catalogos-fijos]], [[HU-007-catalogos-configurables]]; relacionada: [[HU-009-bloques-de-disponibilidad]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** relación N:M, primariedad, sedes, activación y seguridad administrativa.
## Tareas de desarrollo
- [ ] **T-01 — Modelar configuraciones profesional-especialidad/sede.** Dificultad: Alto.
- [ ] **T-02 — Implementar administración autorizada y activación.** Dificultad: Alto.
- [ ] **T-03 — Probar combinaciones, primaria y datos sintéticos.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Alta y configuración
Dado un ADMIN, cuando crea un PROFESSIONAL con datos ficticios válidos, entonces puede asignarle una o más especialidades, una primaria y una o ambas sedes.
### CA-02 — Habilitación controlada
Dado un profesional configurado, cuando ADMIN lo activa o desactiva, entonces la condición se refleja en su disponibilidad futura sin eliminar su trazabilidad.
### CA-03 — Restricción de sede/especialidad
Dado un profesional, cuando se intenta usar una sede o especialidad no asignada/activa, entonces se impide la operación dependiente.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Relaciones N:M, primaria, activación y autorización cuentan con pruebas.
- [ ] Cambios de esquema tienen Flyway y justificación 3FN.
- [ ] Trazabilidad Scrum actualizada.
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
- No se define formato de código/matrícula más allá de ser ficticios.
