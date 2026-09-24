---
id: HU-022
tipo: historia-de-usuario
titulo: Contrato REST directo
estado: Pendiente de aprobación
epica: "[[EP-006-operacion-profesional-y-trazabilidad]]"
esfuerzo: Alto
sprint_sugerido: S6 — Operación y contrato
dependencias: ["[[HU-004-sesion-jwt]]", "[[HU-021-cierre-de-atencion]]"]
relacionadas: ["[[HU-003-registro-de-usuario]]", "[[HU-011-consulta-de-disponibilidad]]"]
fuentes: ["PRD RF-20", "RESTRICCIONES_TECNICAS §Frontend, §Backend, §Documentación"]
---
# HU-022 — Contrato REST directo
## Historia de usuario
**COMO** equipo frontend y backend **QUIERO** documentar y validar el contrato REST JSON directo **PARA** integrar las capacidades sin Express ni BFF.
## Alcance
- Contratos de capacidades aprobadas, validación/errores, autorización y compatibilidad/versionado decididos.
## Fuera de alcance
- Inventar rutas, DTO, códigos o versionado antes de su decisión.
## Reglas de negocio
- Frontend consume `citas-api` directamente; REST JSON; URL backend configurable por environment; CORS explícito.
## Dependencias y relaciones
- Épica: [[EP-006-operacion-profesional-y-trazabilidad]]; depende de [[HU-004-sesion-jwt]], [[HU-021-cierre-de-atencion]]; relacionadas: [[HU-003-registro-de-usuario]], [[HU-011-consulta-de-disponibilidad]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** afecta ambos repositorios y requiere una decisión de compatibilidad.
## Tareas de desarrollo
- [ ] **T-01 — Documentar recursos, solicitudes, respuestas, errores y autorización.** Dificultad: Alto.
- [ ] **T-02 — Acordar versionado/compatibilidad y CORS.** Dificultad: Medio.
- [ ] **T-03 — Verificar cliente directo, backend y pruebas cross-repo.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Contrato documentado
Dado cada capacidad integrada, cuando se revisa su contrato, entonces define entradas, salidas, errores, autorización y validaciones observables.
### CA-02 — Integración directa
Dado el frontend, cuando consume la API, entonces lo hace directamente a `citas-api` sin Express ni BFF y con URL configurable por environment.
### CA-03 — Compatibilidad
Dado un cambio de contrato, cuando se propone, entonces hay una decisión documentada de compatibilidad o versionado y evidencia en ambos repositorios.
## Definition of Done
- [ ] CA-01 a CA-03 validados para las HU incluidas.
- [ ] Documentación enlazada desde wiki/contratos/decisiones aprobadas.
- [ ] Backend endpoint/validación/pruebas y cliente/estados/build-typecheck-pruebas aplicables tienen evidencia.
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
- HU cross-repo: antes de implementar debe enumerar repositorios, contrato, archivos, pruebas y evidencia.
