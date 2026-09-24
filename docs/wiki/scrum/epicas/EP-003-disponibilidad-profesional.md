---
id: EP-003
tipo: epica
titulo: Disponibilidad profesional
estado: Borrador
historias: ["[[HU-009-bloques-de-disponibilidad]]", "[[HU-010-duracion-por-especialidad]]", "[[HU-011-consulta-de-disponibilidad]]"]
dependencias: ["[[EP-002-perfiles-y-catalogos]]"]
---
# EP-003 — Disponibilidad profesional
## Objetivo
Publicar y consultar franjas reservables coherentes con la oferta y duración de atención.
## Valor esperado
El usuario ve únicamente alternativas realmente reservables.
## Actores
- PROFESSIONAL; USER.
## Alcance
- Bloques por sede, discretización de slots, duración y filtros de disponibilidad.
## Fuera de alcance
- Confirmación de la cita.
## Reglas de negocio
- No pasado ni solapamientos; sede asignada; duración de 30/60 min; 60 min exige dos slots consecutivos.
## Dependencias
- [[EP-002-perfiles-y-catalogos]]
## Historias de usuario
- [[HU-009-bloques-de-disponibilidad]]
- [[HU-010-duracion-por-especialidad]]
- [[HU-011-consulta-de-disponibilidad]]
## Criterio de completitud de la épica
- [ ] Las franjas ofrecidas satisfacen todos los CA de sus HU.
## Riesgos e incógnitas
- Falta estrategia técnica aprobada para concurrencia y retenciones.
