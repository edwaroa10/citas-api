---
id: HU-007
tipo: historia-de-usuario
titulo: Catálogos configurables
estado: Pendiente de aprobación
epica: "[[EP-002-perfiles-y-catalogos]]"
esfuerzo: Alto
sprint_sugerido: S2 — Administración de oferta
dependencias: ["[[HU-001-diseno-de-datos-3fn]]", "[[HU-004-sesion-jwt]]"]
relacionadas: ["[[HU-002-catalogos-fijos]]", "[[HU-008-profesionales]]"]
fuentes: ["PRD RF-06", "REQUISITOS_NORMALIZACION_3FN"]
---
# HU-007 — Catálogos configurables
## Historia de usuario
**COMO** ADMIN **QUIERO** gestionar EPS, sus planes y especialidades **PARA** mantener disponible la oferta de atención.
## Alcance
- CRUD autorizado de EPS, planes de EPS y especialidades; activación/desactivación cuando exista referencia transaccional.
## Fuera de alcance
- Borrado físico de catálogos referenciados.
## Reglas de negocio
- No se borra físicamente un catálogo referenciado; especialidad activa es requisito de reserva.
## Dependencias y relaciones
- Épica: [[EP-002-perfiles-y-catalogos]]; depende de [[HU-001-diseno-de-datos-3fn]], [[HU-004-sesion-jwt]]; relacionadas: [[HU-002-catalogos-fijos]], [[HU-008-profesionales]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** tres catálogos relacionados, autorización y preservación de integridad histórica.
## Tareas de desarrollo
- [ ] **T-01 — Definir operaciones y estados de activación.** Dificultad: Medio.
- [ ] **T-02 — Implementar casos de uso autorizados e integridad referencial.** Dificultad: Alto.
- [ ] **T-03 — Integrar vistas y pruebas de CRUD/no borrado.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Gestión autorizada
Dado un ADMIN autenticado, cuando crea, consulta o actualiza EPS, planes o especialidades válidos, entonces el catálogo queda disponible con relaciones consistentes.
### CA-02 — Protección de referencias
Dado un catálogo ya referenciado por una transacción, cuando ADMIN intenta eliminarlo, entonces no se elimina físicamente y se permite gestionar su activación cuando aplique.
### CA-03 — Acceso restringido
Dado un actor que no es ADMIN, cuando intenta gestionar estos catálogos, entonces se rechaza sin alterarlos.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Autorización, validación y reglas de referencia tienen pruebas relevantes.
- [ ] Migración Flyway aplicable conserva 3FN e historial referenciado.
- [ ] Trazabilidad Scrum actualizada.
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
- El catálogo no define datos reales de FCV.
