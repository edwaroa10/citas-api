---
id: HU-003
tipo: historia-de-usuario
titulo: Registro de usuario
estado: Pendiente de aprobación
epica: "[[EP-001-fundacion-y-acceso]]"
esfuerzo: Alto
sprint_sugerido: S1 — Fundación segura
dependencias: ["[[HU-001-diseno-de-datos-3fn]]", "[[HU-002-catalogos-fijos]]"]
relacionadas: ["[[HU-004-sesion-jwt]]"]
fuentes: ["PRD RF-01", "PRD §8", "RESTRICCIONES_TECNICAS §Backend"]
---
# HU-003 — Registro de usuario
## Historia de usuario
**COMO** visitante **QUIERO** crear una cuenta USER con mis datos mínimos **PARA** acceder a las capacidades de citas.
## Alcance
- Nombres, apellidos, tipo/número de documento, email, teléfono y contraseña; rol USER.
## Fuera de alcance
- Creación de PROFESSIONAL por ADMIN y recuperación de contraseña.
## Reglas de negocio
- Email y documento únicos; password con hash adaptativo; datos sintéticos; validación server-side.
## Dependencias y relaciones
- Épica: [[EP-001-fundacion-y-acceso]]; depende de [[HU-001-diseno-de-datos-3fn]] y [[HU-002-catalogos-fijos]]; relacionada: [[HU-004-sesion-jwt]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** combina persistencia, seguridad, validaciones y una experiencia frontend/backend coherente.
## Tareas de desarrollo
- [ ] **T-01 — Definir interacción de registro y validaciones.** Dificultad: Medio.
- [ ] **T-02 — Implementar caso de uso con unicidad y hash seguro.** Dificultad: Alto.
- [ ] **T-03 — Integrar contrato y pruebas de errores/éxito.** Dificultad: Alto.
## Criterios de aceptación
### CA-01 — Registro válido
Dado un visitante con todos los datos mínimos válidos, cuando confirma el registro, entonces se crea una cuenta con rol USER sin almacenar la contraseña en texto plano.
### CA-02 — Unicidad
Dado un email o documento ya registrado, cuando se intenta registrar otra cuenta, entonces se informa el conflicto y no se crea una cuenta duplicada.
### CA-03 — Datos inválidos
Dado un dato obligatorio o formato inválido, cuando se envía el registro, entonces se muestran/restituyen errores verificables y no se persiste una cuenta parcial.
## Definition of Done
- [ ] CA-01 a CA-03 validados con pruebas de dominio/aplicación y REST aplicables.
- [ ] Password usa hash adaptativo y no se registra ni expone en logs/respuestas.
- [ ] Validación server-side y autorización inicial coherentes con el contrato aprobado.
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
- No se especifican rutas ni payloads antes de [[HU-022-contrato-rest-directo]].
