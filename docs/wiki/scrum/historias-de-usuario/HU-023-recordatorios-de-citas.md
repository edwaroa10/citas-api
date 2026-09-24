---
id: HU-023
tipo: historia-de-usuario
titulo: Recordatorios de citas
estado: Pendiente de aprobación
epica: "[[EP-007-automatizaciones-posteriores]]"
esfuerzo: Medio
sprint_sugerido: S7 — Automatización posterior
dependencias: ["[[HU-022-contrato-rest-directo]]"]
relacionadas: ["[[HU-025-resumen-operativo-diario]]"]
fuentes: ["PRD §10", "RESTRICCIONES_TECNICAS §n8n"]
---
# HU-023 — Recordatorios de citas
## Historia de usuario
**COMO** USER **QUIERO** recibir un recordatorio de una cita próxima **PARA** prepararme para asistir.
## Alcance
- Workflow n8n posterior para recordatorios mediante Gmail.
## Fuera de alcance
- Cambiar reglas del núcleo de citas o versionar credenciales.
## Reglas de negocio
- Instancia central trainer; credenciales Google Cloud/Gmail del estudiante; JSON sin credenciales.
## Dependencias y relaciones
- Épica: [[EP-007-automatizaciones-posteriores]]; depende de [[HU-022-contrato-rest-directo]]; relacionada: [[HU-025-resumen-operativo-diario]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** integra n8n y correo sin alterar el núcleo.
## Tareas de desarrollo
- [ ] **T-01 — Definir disparador, destinatario y contenido aprobados.** Dificultad: Medio.
- [ ] **T-02 — Configurar/exportar workflow JSON sin secretos.** Dificultad: Medio.
- [ ] **T-03 — Probar con datos sintéticos y registrar evidencia.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Recordatorio
Dado una cita próxima que cumpla la condición aprobada, cuando ejecuta el workflow, entonces genera el recordatorio para su destinatario autorizado.
### CA-02 — Sin secretos
Dado el JSON versionado, cuando se revisa, entonces no contiene credenciales ni tokens.
### CA-03 — Núcleo intacto
Dado la automatización, cuando se ejecuta, entonces no modifica las reglas ni estados centrales de citas fuera del contrato aprobado.
## Definition of Done
- [ ] CA-01 a CA-03 validados con datos sintéticos.
- [ ] Workflow JSON está en `citas-api/automations/n8n/` sin credenciales.
- [ ] Evidencia de ejecución y trazabilidad actualizadas.
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
- Pendiente: condición de proximidad, destinatario y plantilla.
