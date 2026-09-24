---
id: HU-011
tipo: historia-de-usuario
titulo: Consulta de disponibilidad
estado: Pendiente de aprobación
epica: "[[EP-003-disponibilidad-profesional]]"
esfuerzo: Alto
sprint_sugerido: S3 — Disponibilidad y cita general
dependencias: ["[[HU-009-bloques-de-disponibilidad]]", "[[HU-010-duracion-por-especialidad]]"]
relacionadas: ["[[HU-012-cita-general]]", "[[HU-013-cita-especializada]]"]
fuentes: ["PRD RF-10", "PRD RN-01", "PRD RN-08"]
---
# HU-011 — Consulta de disponibilidad
## Historia de usuario
**COMO** USER **QUIERO** filtrar horarios disponibles **PARA** elegir una franja que pueda completar mi cita.
## Alcance
- Filtros por sede, tipo, especialidad, profesional y fecha; solo franjas con duración completa.
## Fuera de alcance
- Confirmar una reserva.
## Reglas de negocio
- Especialidad activa/asociada al profesional; no mostrar slots reservados o retenidos ni duración incompleta.
## Dependencias y relaciones
- Épica: [[EP-003-disponibilidad-profesional]]; depende de [[HU-009-bloques-de-disponibilidad]], [[HU-010-duracion-por-especialidad]]; relacionadas: [[HU-012-cita-general]], [[HU-013-cita-especializada]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** combina filtros, reglas de oferta, duración y estados de reserva.
## Tareas de desarrollo
- [ ] **T-01 — Definir consulta y estados vacíos/errores.** Dificultad: Medio.
- [ ] **T-02 — Implementar cálculo de franjas completas y filtros.** Dificultad: Alto.
- [ ] **T-03 — Probar exclusión de ocupados, retenidos e incompletos.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Filtros
Dado un USER, cuando filtra por los criterios soportados, entonces obtiene únicamente alternativas que corresponden a los filtros.
### CA-02 — Duración completa
Dado una especialidad de 60 minutos, cuando consulta horarios, entonces no se muestra una franja que no tenga dos slots consecutivos libres.
### CA-03 — Oferta válida
Dado un slot ocupado/retenido, una especialidad inactiva o un profesional no asociado, cuando consulta, entonces esa alternativa no se muestra como disponible.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas de cálculo y filtros.
- [ ] Frontend presenta resultado, vacío y error sin inventar disponibilidad.
- [ ] Contrato y autorización aplicables verificados; trazabilidad actualizada.
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
- Concurrencia en el momento de confirmar queda pendiente de decisión.
