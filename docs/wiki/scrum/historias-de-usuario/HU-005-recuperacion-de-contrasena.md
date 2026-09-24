---
id: HU-005
tipo: historia-de-usuario
titulo: Recuperación de contraseña
estado: Pendiente de aprobación
epica: "[[EP-002-perfiles-y-catalogos]]"
esfuerzo: Medio
sprint_sugerido: S2 — Administración de oferta
dependencias: ["[[HU-004-sesion-jwt]]"]
relacionadas: ["[[HU-003-registro-de-usuario]]"]
fuentes: ["PRD RF-03", "PRD §8"]
---
# HU-005 — Recuperación de contraseña
## Historia de usuario
**COMO** usuario registrado **QUIERO** recuperar y cambiar mi contraseña con un token temporal de un solo uso **PARA** restablecer el acceso de forma segura.
## Alcance
- Solicitud por email, token temporal/único, cambio de password e invalidación/consumo del token.
## Fuera de alcance
- SMTP obligatorio; el envío real puede ser opcional en desarrollo.
## Reglas de negocio
- El mecanismo de exposición controlada en desarrollo debe ser seguro y decidido; cambiar password consume/invalida el token.
## Dependencias y relaciones
- Épica: [[EP-002-perfiles-y-catalogos]]; depende de [[HU-004-sesion-jwt]]; relacionada: [[HU-003-registro-de-usuario]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** flujo acotado con requisitos de seguridad y una decisión pendiente.
## Tareas de desarrollo
- [ ] **T-01 — Acordar mecanismo seguro de entrega/desarrollo.** Dificultad: Medio.
- [ ] **T-02 — Implementar emisión, uso único y cambio de contraseña.** Dificultad: Alto.
- [ ] **T-03 — Probar expiración, reutilización y no divulgación.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Solicitud
Dado un email de cuenta, cuando solicita recuperación, entonces se genera un token temporal de un solo uso mediante el mecanismo aprobado sin exponerlo inseguramente.
### CA-02 — Cambio válido
Dado un token temporal válido y una nueva contraseña válida, cuando confirma el cambio, entonces la contraseña queda actualizada con hash adaptativo y el token se consume.
### CA-03 — Token no válido
Dado un token usado, inválido o vencido, cuando intenta cambiar la contraseña, entonces se rechaza y la contraseña no cambia.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Token temporal no queda en logs ni repositorio; contraseña no queda en texto plano.
- [ ] La decisión de entrega controlada está documentada antes de implementar.
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
- Pendiente: mecanismo exacto de entrega controlada de token en desarrollo.
