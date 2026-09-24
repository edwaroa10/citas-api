---
id: HU-002
tipo: historia-de-usuario
titulo: Catálogos fijos de referencia
estado: Pendiente de aprobación
epica: "[[EP-001-fundacion-y-acceso]]"
esfuerzo: Medio
sprint_sugerido: S1 — Fundación segura
dependencias: ["[[HU-001-diseno-de-datos-3fn]]"]
relacionadas: ["[[HU-007-catalogos-configurables]]"]
fuentes: ["PRD RF-05", "RESTRICCIONES_TECNICAS §Base de datos"]
---
# HU-002 — Catálogos fijos de referencia
## Historia de usuario
**COMO** usuario de la aplicación **QUIERO** que los catálogos fijos estén disponibles y sean consultables **PARA** usar valores consistentes en todo el sistema.
## Alcance
- Roles, estados de cita, estados de reprogramación, regímenes y las dos sedes fijas del laboratorio.
## Fuera de alcance
- CRUD administrativo de EPS, planes y especialidades.
## Reglas de negocio
- Son de solo lectura y se cargan por seed; las sedes son HIC e ICV definidas en el PRD.
## Dependencias y relaciones
- Épica: [[EP-001-fundacion-y-acceso]]; depende de [[HU-001-diseno-de-datos-3fn]]; relacionada: [[HU-007-catalogos-configurables]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** requiere datos consistentes e integración transversal, sin CRUD.
## Tareas de desarrollo
- [ ] **T-01 — Definir datos seed sintéticos/fijos.** Dificultad: Bajo. Incluir conjuntos exigidos.
- [ ] **T-02 — Exponer consulta de solo lectura según contrato aprobado.** Dificultad: Medio.
- [ ] **T-03 — Probar integridad y no modificación.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Conjuntos disponibles
Dado un consumidor autorizado, cuando consulta catálogos fijos, entonces obtiene roles, estados, regímenes y ambas sedes definidos.
### CA-02 — Solo lectura
Dado un intento de modificar un catálogo fijo mediante la interfaz o API, cuando se procesa, entonces no altera el catálogo.
### CA-03 — Consistencia
Dado una capacidad que refiere un catálogo fijo, cuando persiste o muestra su valor, entonces usa la referencia válida y no texto divergente.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Seed/migración Flyway aplicable y coherente con [[HU-001-diseno-de-datos-3fn]].
- [ ] Pruebas relevantes de consulta e inmutabilidad disponibles.
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
- La forma del contrato de consulta queda para [[HU-022-contrato-rest-directo]].
