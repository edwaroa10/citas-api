---
id: EP-005
tipo: epica
titulo: Reprogramación y operación administrativa
estado: Borrador
historias: ["[[HU-017-solicitud-de-reprogramacion]]", "[[HU-018-decision-de-reprogramacion]]", "[[HU-019-bandeja-administrativa]]"]
dependencias: ["[[EP-004-ciclo-de-citas]]"]
---
# EP-005 — Reprogramación y operación administrativa
## Objetivo
Preservar la cita original mientras se decide una reprogramación y centralizar decisiones administrativas.
## Valor esperado
Cambios de agenda trazables sin pérdida de la reserva original.
## Actores
- USER; ADMIN.
## Alcance
- Solicitud, decisión y bandeja administrativa.
## Fuera de alcance
- Cambio de profesional durante reprogramación, que es nueva cita.
## Reglas de negocio
- Solo aprobada y futura; conserva profesional/especialidad; retiene nueva franja; rechazo conserva original y libera provisional.
## Dependencias
- [[EP-004-ciclo-de-citas]]; [[HU-016-auditoria-de-estados]].
## Historias de usuario
- [[HU-017-solicitud-de-reprogramacion]]
- [[HU-018-decision-de-reprogramacion]]
- [[HU-019-bandeja-administrativa]]
## Criterio de completitud de la épica
- [ ] Ninguna decisión pierde la cita original de forma no autorizada.
## Riesgos e incógnitas
- Resuelto (2026-09-28): retención provisional usa restricción única en BD y expira a los 30 minutos. Pendiente: qué ocurre con el estado `PENDING` de la solicitud de reprogramación cuando esa retención expira sin decisión ADMIN.
