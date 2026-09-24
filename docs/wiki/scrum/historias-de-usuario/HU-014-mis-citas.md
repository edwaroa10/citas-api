---
id: HU-014
tipo: historia-de-usuario
titulo: Mis citas
estado: Pendiente de aprobación
epica: "[[EP-004-ciclo-de-citas]]"
esfuerzo: Medio
sprint_sugerido: S4 — Cita especializada
dependencias: ["[[HU-012-cita-general]]", "[[HU-013-cita-especializada]]"]
relacionadas: ["[[HU-015-cancelacion-de-cita]]", "[[HU-017-solicitud-de-reprogramacion]]"]
fuentes: ["PRD RF-13"]
---
# HU-014 — Mis citas
## Historia de usuario
**COMO** USER **QUIERO** consultar y filtrar mis citas **PARA** conocer su estado y gestionar las que correspondan.
## Alcance
- Filtros por estado/fecha; sede, profesional, especialidad, fecha/hora, duración, estado y motivo de rechazo.
## Fuera de alcance
- Consultar citas de otros usuarios.
## Reglas de negocio
- Ownership; motivo solo cuando exista.
## Dependencias y relaciones
- Épica: [[EP-004-ciclo-de-citas]]; depende de [[HU-012-cita-general]], [[HU-013-cita-especializada]]; relacionadas: [[HU-015-cancelacion-de-cita]], [[HU-017-solicitud-de-reprogramacion]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** consulta autorizada con filtros y datos compuestos.
## Tareas de desarrollo
- [ ] **T-01 — Definir filtros y representación de detalle.** Dificultad: Medio.
- [ ] **T-02 — Implementar consulta con ownership.** Dificultad: Alto.
- [ ] **T-03 — Probar filtros, campos mínimos y aislamiento entre usuarios.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Consulta propia
Dado un USER autenticado, cuando consulta sus citas, entonces ve solo las propias con todos los campos mínimos requeridos.
### CA-02 — Filtros
Dado citas con distintos estados/fechas, cuando aplica filtros, entonces el resultado corresponde a ambos criterios seleccionados.
### CA-03 — Rechazo visible
Dado una cita REJECTED con motivo, cuando consulta el detalle/listado, entonces visualiza dicho motivo.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Ownership, filtros y presentación de vacío/error cubiertos por pruebas aplicables.
- [ ] Contrato REST/UI coherente y trazabilidad actualizada.
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
