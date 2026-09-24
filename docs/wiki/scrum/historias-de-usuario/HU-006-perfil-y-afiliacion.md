---
id: HU-006
tipo: historia-de-usuario
titulo: Perfil y afiliación
estado: Pendiente de aprobación
epica: "[[EP-002-perfiles-y-catalogos]]"
esfuerzo: Medio
sprint_sugerido: S2 — Administración de oferta
dependencias: ["[[HU-003-registro-de-usuario]]", "[[HU-002-catalogos-fijos]]"]
relacionadas: ["[[HU-007-catalogos-configurables]]"]
fuentes: ["PRD RF-04", "REQUISITOS_NORMALIZACION_3FN"]
---
# HU-006 — Perfil y afiliación
## Historia de usuario
**COMO** USER **QUIERO** consultar y actualizar mis datos permitidos y mi afiliación **PARA** mantener mi información de atención vigente.
## Alcance
- Consulta/actualización de perfil y asociación de EPS, plan y régimen mediante afiliación.
## Fuera de alcance
- Definir unilateralmente qué campos personales son editables.
## Reglas de negocio
- No duplicar EPS, régimen y plan dentro de la afiliación del usuario; ownership obligatorio.
## Dependencias y relaciones
- Épica: [[EP-002-perfiles-y-catalogos]]; depende de [[HU-003-registro-de-usuario]], [[HU-002-catalogos-fijos]]; relacionada: [[HU-007-catalogos-configurables]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** combina ownership, datos normalizados y decisión pendiente de campos editables.
## Tareas de desarrollo
- [ ] **T-01 — Acordar campos permitidos.** Dificultad: Bajo.
- [ ] **T-02 — Implementar lectura/edición con ownership y afiliación normalizada.** Dificultad: Alto.
- [ ] **T-03 — Probar duplicación, autorización y validaciones.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Consulta propia
Dado un USER autenticado, cuando consulta su perfil, entonces visualiza únicamente sus datos y su afiliación.
### CA-02 — Actualización permitida
Dado campos expresamente permitidos y valores válidos, cuando actualiza su perfil/afiliación, entonces los cambios se conservan sin afectar otro usuario.
### CA-03 — Afiliación sin duplicados
Dado una afiliación ya equivalente para el usuario, cuando intenta repetir EPS, régimen y plan, entonces el sistema evita la duplicación.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Ownership y validación server-side cubiertos por pruebas aplicables.
- [ ] Si cambia esquema, migración Flyway y coherencia 3FN verificadas.
- [ ] Campos editables aprobados y trazabilidad actualizada.
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
- Pendiente: lista de campos editables.
