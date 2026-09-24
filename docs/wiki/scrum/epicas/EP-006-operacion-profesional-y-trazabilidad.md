---
id: EP-006
tipo: epica
titulo: Operación profesional y trazabilidad
estado: Borrador
historias: ["[[HU-016-auditoria-de-estados]]", "[[HU-020-agenda-del-profesional]]", "[[HU-021-cierre-de-atencion]]", "[[HU-022-contrato-rest-directo]]"]
dependencias: ["[[EP-004-ciclo-de-citas]]"]
---
# EP-006 — Operación profesional y trazabilidad
## Objetivo
Permitir la operación segura del profesional y mantener estados auditables con contrato REST documentado.
## Valor esperado
Información restringida por ownership y cambios de estado verificables entre frontend y backend.
## Actores
- PROFESSIONAL; USER; ADMIN.
## Alcance
- Auditoría, agenda visible, cierre de atención y contrato REST.
## Fuera de alcance
- Automatizaciones n8n.
## Reglas de negocio
- Auditoría inmutable; profesional solo ve sus citas; estados de cierre se registran.
## Dependencias
- [[EP-004-ciclo-de-citas]].
## Historias de usuario
- [[HU-016-auditoria-de-estados]]
- [[HU-020-agenda-del-profesional]]
- [[HU-021-cierre-de-atencion]]
- [[HU-022-contrato-rest-directo]]
## Criterio de completitud de la épica
- [ ] Operación y contrato cuentan con evidencia por HU.
## Riesgos e incógnitas
- No se han decidido rutas/DTO/versionado REST ni la regla exacta de aplicabilidad del cierre.
