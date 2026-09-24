---
id: HU-024
tipo: historia-de-usuario
titulo: Notificaciones de cambio de estado
estado: Pendiente de aprobación
epica: "[[EP-007-automatizaciones-posteriores]]"
esfuerzo: Medio
sprint_sugerido: S7 — Automatización posterior
dependencias: ["[[HU-016-auditoria-de-estados]]", "[[HU-022-contrato-rest-directo]]"]
relacionadas: ["[[HU-023-recordatorios-de-citas]]"]
fuentes: ["PRD §10", "RESTRICCIONES_TECNICAS §n8n"]
---
# HU-024 — Notificaciones de cambio de estado
## Historia de usuario
**COMO** USER **QUIERO** recibir una notificación cuando cambie el estado de mi cita **PARA** conocer decisiones relevantes.
## Alcance
- Workflow webhook + Gmail posterior a cambios de estado.
## Fuera de alcance
- Crear estados o modificar auditoría del núcleo.
## Reglas de negocio
- Datos mínimos y sintéticos; JSON del workflow sin credenciales.
## Dependencias y relaciones
- Épica: [[EP-007-automatizaciones-posteriores]]; depende de [[HU-016-auditoria-de-estados]], [[HU-022-contrato-rest-directo]]; relacionada: [[HU-023-recordatorios-de-citas]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** enlaza evento auditable, webhook y correo.
## Tareas de desarrollo
- [ ] **T-01 — Definir eventos y contenido permitido.** Dificultad: Medio.
- [ ] **T-02 — Configurar/exportar workflow webhook-Gmail sin secretos.** Dificultad: Medio.
- [ ] **T-03 — Probar trazabilidad evento→notificación con datos sintéticos.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Evento notificable
Dado un cambio de estado definido, cuando queda auditado, entonces el workflow recibe el evento conforme al contrato aprobado.
### CA-02 — Notificación segura
Dado un evento válido, cuando se procesa, entonces se genera una notificación sin incluir tokens, passwords o PII innecesaria.
### CA-03 — Artefacto versionable
Dado el workflow, cuando se revisa el artefacto versionado, entonces es JSON sin credenciales.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Contrato de webhook y eventos enlazado a [[HU-016-auditoria-de-estados]].
- [ ] JSON y evidencia sintética disponibles; trazabilidad actualizada.
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
- Pendiente: eventos, destinatarios y contenido permitido.
