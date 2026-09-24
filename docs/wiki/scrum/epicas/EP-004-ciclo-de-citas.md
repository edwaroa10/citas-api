---
id: EP-004
tipo: epica
titulo: Ciclo de citas
estado: Borrador
historias: ["[[HU-012-cita-general]]", "[[HU-013-cita-especializada]]", "[[HU-014-mis-citas]]", "[[HU-015-cancelacion-de-cita]]"]
dependencias: ["[[EP-003-disponibilidad-profesional]]"]
---
# EP-004 — Ciclo de citas
## Objetivo
Permitir reservar, consultar y cancelar citas respetando tipos y estados.
## Valor esperado
El usuario gestiona sus citas sin dobles reservas.
## Actores
- USER; ADMIN.
## Alcance
- Cita general, especializada, mis citas y cancelación.
## Fuera de alcance
- Reprogramación y cierre de atención.
## Reglas de negocio
- General se aprueba automáticamente; especializada inicia solicitada; cancelación futura no terminal libera slots.
## Dependencias
- [[EP-003-disponibilidad-profesional]]
## Historias de usuario
- [[HU-012-cita-general]]
- [[HU-013-cita-especializada]]
- [[HU-014-mis-citas]]
- [[HU-015-cancelacion-de-cita]]
## Criterio de completitud de la épica
- [ ] Sus HU completadas y auditoría enlazada a [[HU-016-auditoria-de-estados]].
## Riesgos e incógnitas
- Las transiciones terminales completas requieren decisión documental.
