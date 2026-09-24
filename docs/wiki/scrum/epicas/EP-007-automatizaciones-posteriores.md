---
id: EP-007
tipo: epica
titulo: Automatizaciones posteriores
estado: Borrador
historias: ["[[HU-023-recordatorios-de-citas]]", "[[HU-024-notificaciones-de-estado]]", "[[HU-025-resumen-operativo-diario]]"]
dependencias: ["[[EP-006-operacion-profesional-y-trazabilidad]]"]
---
# EP-007 — Automatizaciones posteriores
## Objetivo
Agregar automatizaciones n8n sin alterar el núcleo funcional de citas.
## Valor esperado
Comunicación y visibilidad operativa posterior a S5/S6.
## Actores
- USER; ADMIN; trainer.
## Alcance
- Recordatorios, notificación de estado y resumen por sede/estado.
## Fuera de alcance
- Cambios al núcleo de reglas de citas.
## Reglas de negocio
- Workflows JSON versionados sin credenciales; credenciales Google Cloud/Gmail las configura cada estudiante.
## Dependencias
- [[EP-006-operacion-profesional-y-trazabilidad]].
## Historias de usuario
- [[HU-023-recordatorios-de-citas]]
- [[HU-024-notificaciones-de-estado]]
- [[HU-025-resumen-operativo-diario]]
## Criterio de completitud de la épica
- [ ] Workflows y evidencias de cada HU están disponibles sin secretos.
## Riesgos e incógnitas
- Disponibilidad de instancia n8n/MCP y decisiones de destinatarios/plantillas.
