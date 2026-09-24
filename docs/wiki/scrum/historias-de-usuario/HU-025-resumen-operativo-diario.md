---
id: HU-025
tipo: historia-de-usuario
titulo: Resumen operativo diario
estado: Pendiente de aprobación
epica: "[[EP-007-automatizaciones-posteriores]]"
esfuerzo: Medio
sprint_sugerido: S7 — Automatización posterior
dependencias: ["[[HU-022-contrato-rest-directo]]", "[[HU-023-recordatorios-de-citas]]"]
relacionadas: ["[[HU-024-notificaciones-de-estado]]"]
fuentes: ["PRD §10", "RESTRICCIONES_TECNICAS §n8n"]
---
# HU-025 — Resumen operativo diario
## Historia de usuario
**COMO** ADMIN **QUIERO** recibir un resumen operativo diario por sede y estado **PARA** observar la operación del laboratorio.
## Alcance
- Caso adicional n8n de resumen por sede/estado.
## Fuera de alcance
- Modificar datos de citas o crear informes clínicos reales.
## Reglas de negocio
- Datos sintéticos; workflow sin credenciales; depende de la instancia central del trainer.
## Dependencias y relaciones
- Épica: [[EP-007-automatizaciones-posteriores]]; depende de [[HU-022-contrato-rest-directo]], [[HU-023-recordatorios-de-citas]]; relacionada: [[HU-024-notificaciones-de-estado]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** consulta agregada y automatización externa, sin cambio de núcleo.
## Tareas de desarrollo
- [ ] **T-01 — Definir alcance de conteos y destinatario autorizado.** Dificultad: Medio.
- [ ] **T-02 — Configurar/exportar workflow de consulta y resumen.** Dificultad: Medio.
- [ ] **T-03 — Probar agrupación sede/estado con datos sintéticos.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Agrupación
Dado citas de prueba en distintas sedes y estados, cuando ejecuta el workflow diario, entonces el resumen agrupa la información por sede y estado.
### CA-02 — Destino autorizado
Dado un resumen generado, cuando se entrega, entonces llega solo al destinatario definido en la decisión aprobada.
### CA-03 — Seguridad del artefacto
Dado el JSON versionado, cuando se inspecciona, entonces no contiene credenciales, tokens ni datos reales.
## Definition of Done
- [ ] CA-01 a CA-03 validados con datos sintéticos.
- [ ] Workflow JSON se versiona sin credenciales en `citas-api/automations/n8n/`.
- [ ] Evidencia del resumen y trazabilidad actualizadas.
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
- Pendiente: destinatario, canal y contenido exacto del resumen.
