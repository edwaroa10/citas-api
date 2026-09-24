---
tipo: indice-scrum
estado: Borrador
fuentes:
  - "../PRD.md"
  - "../RESTRICCIONES_TECNICAS.md"
  - "../database/REQUISITOS_NORMALIZACION_3FN.md"
---

# Mapa Scrum / Spec-Driven Development — Citas

## Objetivo y límites

Mapa trazable para construir el sistema ficticio de agendamiento de citas. Las historias son especificaciones de trabajo: no son una aprobación de implementación ni un contrato REST definitivo. Solo se usaron las tres fuentes declaradas en esta nota. Todos los datos de prueba deberán ser sintéticos.

## Arquitectura y stack condicionantes

- Backend: Java 21, Spring Boot 3.5.x, Maven, arquitectura hexagonal, Spring Data JPA, MySQL 8.4, Flyway y Spring Security con JWT access/refresh.
- Frontend: TypeScript, Node.js 24 LTS y React o Angular según la selección/exportación aprobada de Stitch + Google AI Studio. Consume REST directamente; no hay Express ni BFF.
- Datos: 3FN como mínimo; catálogos fijos por seed y catálogos configurables administrables.

## Estados de las historias

Todas las HU están **Pendiente de aprobación**. Ninguna puede pasar a `Aprobada` sin revisión explícita del usuario. La secuencia sugerida asume una sola persona y no expresa duración, capacidad, fechas ni puntos.

## Épicas

- [[EP-001-fundacion-y-acceso]]
- [[EP-002-perfiles-y-catalogos]]
- [[EP-003-disponibilidad-profesional]]
- [[EP-004-ciclo-de-citas]]
- [[EP-005-reprogramacion-y-operacion-administrativa]]
- [[EP-006-operacion-profesional-y-trazabilidad]]
- [[EP-007-automatizaciones-posteriores]]

## Incrementos / sprints sugeridos

| Incremento | Resultado funcional verificable | HU en orden |
|---|---|---|
| S1 — Fundación segura | Persistencia 3FN definida, catálogos base, registro y sesión utilizables. | [[HU-001-diseno-de-datos-3fn]], [[HU-002-catalogos-fijos]], [[HU-003-registro-de-usuario]], [[HU-004-sesion-jwt]] |
| S2 — Administración de oferta | Recuperación, perfil/afiliación y administración de la oferta de atención. | [[HU-005-recuperacion-de-contrasena]], [[HU-006-perfil-y-afiliacion]], [[HU-007-catalogos-configurables]], [[HU-008-profesionales]] |
| S3 — Disponibilidad y cita general | Agenda publicable, disponibilidad fiable y reserva general autoaprobada. | [[HU-009-bloques-de-disponibilidad]], [[HU-010-duracion-por-especialidad]], [[HU-011-consulta-de-disponibilidad]], [[HU-012-cita-general]] |
| S4 — Cita especializada | Solicitud especializada, consulta/cancelación y auditoría inicial de estados. | [[HU-013-cita-especializada]], [[HU-014-mis-citas]], [[HU-015-cancelacion-de-cita]], [[HU-016-auditoria-de-estados]] |
| S5 — Reprogramación y gestión | Reprogramación segura y decisiones administrativas en bandeja. | [[HU-017-solicitud-de-reprogramacion]], [[HU-018-decision-de-reprogramacion]], [[HU-019-bandeja-administrativa]] |
| S6 — Operación y contrato | Profesional consulta/cierra su agenda y el contrato REST queda documentado y validable. | [[HU-020-agenda-del-profesional]], [[HU-021-cierre-de-atencion]], [[HU-022-contrato-rest-directo]] |
| S7 — Automatización posterior | Workflows n8n versionados para recordatorios, cambios de estado y resumen operativo. | [[HU-023-recordatorios-de-citas]], [[HU-024-notificaciones-de-estado]], [[HU-025-resumen-operativo-diario]] |

## Decisiones e incógnitas que requieren aprobación

- Estrategia concreta de concurrencia y de retención para impedir doble reserva (incluidos casos simultáneos) no está definida por las fuentes.
- Matriz completa de transiciones de estados, vencimiento de retenciones y definición precisa de “pasada/aplicable” para cierre de atención requieren decisión.
- El framework elegido entre React y Angular, diseño Stitch/AI Studio, rutas, verbos, DTO, códigos HTTP, versionado y compatibilidad del contrato REST están pendientes.
- El mecanismo seguro de entrega del token de recuperación en desarrollo y las condiciones exactas de exposición controlada deben definirse antes de implementar [[HU-005-recuperacion-de-contrasena]].
- Las automatizaciones S7 dependen de la instancia central del trainer, credenciales configuradas por cada estudiante y de decisiones de notificación; no cambian el núcleo funcional.

## Trazabilidad de selección para S2, S3 y S4

Cada HU incluye fuente, dependencias, CA, DoD y estado. Para seleccionar una HU, S2/S3/S4 deben confirmar: estado `Aprobada`, sus dependencias `Completada` o explícitamente resueltas, y las decisiones pendientes que afecten sus CA. Los enlaces de cada épica y la columna de dependencia de cada HU permiten verificar la cadena sin inferir alcance.
