---
id: HU-019
tipo: historia-de-usuario
titulo: Bandeja administrativa
estado: Pendiente de aprobación
epica: "[[EP-005-reprogramacion-y-operacion-administrativa]]"
esfuerzo: Medio
sprint_sugerido: S5 — Reprogramación y gestión
dependencias: ["[[HU-013-cita-especializada]]", "[[HU-018-decision-de-reprogramacion]]"]
relacionadas: []
fuentes: ["PRD RF-18"]
---
# HU-019 — Bandeja administrativa
## Historia de usuario
**COMO** ADMIN **QUIERO** consultar solicitudes especializadas y reprogramaciones pendientes con filtros **PARA** tomar decisiones operativas.
## Alcance
- REQUESTED y PENDING; filtros sede, profesional, especialidad y fecha.
## Fuera de alcance
- Decidir solicitudes fuera de sus flujos [[HU-013-cita-especializada]] y [[HU-018-decision-de-reprogramacion]].
## Reglas de negocio
- Solo ADMIN; se muestran los estados pendientes especificados.
## Dependencias y relaciones
- Épica: [[EP-005-reprogramacion-y-operacion-administrativa]]; depende de [[HU-013-cita-especializada]], [[HU-018-decision-de-reprogramacion]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** consulta autorizada con dos tipos de pendientes y filtros.
## Tareas de desarrollo
- [ ] **T-01 — Definir representación común y filtros.** Dificultad: Medio.
- [ ] **T-02 — Implementar consulta exclusiva de ADMIN.** Dificultad: Alto.
- [ ] **T-03 — Probar filtros, estados y acceso no autorizado.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Pendientes visibles
Dado un ADMIN, cuando abre la bandeja, entonces ve citas especializadas REQUESTED y reprogramaciones PENDING.
### CA-02 — Filtros
Dado solicitudes con sedes, profesionales, especialidades y fechas distintas, cuando filtra, entonces solo ve los elementos coincidentes.
### CA-03 — Protección
Dado USER o PROFESSIONAL, cuando intenta acceder, entonces no obtiene la bandeja administrativa.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Autorización y filtros cuentan con pruebas aplicables.
- [ ] UI muestra resultados, vacío y error; trazabilidad actualizada.
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
