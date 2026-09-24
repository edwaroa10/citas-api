---
id: EP-002
tipo: epica
titulo: Perfiles y catálogos de la oferta
estado: Borrador
historias: ["[[HU-005-recuperacion-de-contrasena]]", "[[HU-006-perfil-y-afiliacion]]", "[[HU-007-catalogos-configurables]]", "[[HU-008-profesionales]]"]
dependencias: ["[[EP-001-fundacion-y-acceso]]"]
---
# EP-002 — Perfiles y catálogos de la oferta
## Objetivo
Administrar identidades, afiliaciones y la oferta configurable de atención.
## Valor esperado
Usuarios controlan sus datos y ADMIN prepara profesionales y catálogos para agendar.
## Actores
- USER; ADMIN.
## Alcance
- Recuperación, perfil/afiliación, EPS/planes/especialidades y profesionales.
## Fuera de alcance
- Agenda y reserva.
## Reglas de negocio
- No duplicar EPS/régimen/plan de un usuario; no borrar físicamente catálogos referenciados; profesional habilitado solo en sedes asignadas.
## Dependencias
- [[EP-001-fundacion-y-acceso]]
## Historias de usuario
- [[HU-005-recuperacion-de-contrasena]]
- [[HU-006-perfil-y-afiliacion]]
- [[HU-007-catalogos-configurables]]
- [[HU-008-profesionales]]
## Criterio de completitud de la épica
- [ ] Todas las HU obligatorias completadas con datos sintéticos.
## Riesgos e incógnitas
- Las reglas exactas para cambiar datos de perfil deben concretarse en la aprobación de HU-006.
