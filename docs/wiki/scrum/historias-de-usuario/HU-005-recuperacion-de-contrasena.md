---
id: HU-005
tipo: historia-de-usuario
titulo: Recuperación de contraseña
estado: Aprobada
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
- El endpoint de solicitud devuelve el token temporal en la respuesta únicamente cuando el perfil/flag de entorno indica modo desarrollo; en cualquier otro perfil (incluida producción) nunca se expone en la respuesta ni en logs. Cambiar password consume/invalida el token.
## Dependencias y relaciones
- Épica: [[EP-002-perfiles-y-catalogos]]; depende de [[HU-004-sesion-jwt]]; relacionada: [[HU-003-registro-de-usuario]].
## Esfuerzo
**Nivel:** Medio. **Justificación:** flujo acotado con requisitos de seguridad y una decisión pendiente.
## Tareas de desarrollo
- [ ] **T-01 — Implementar exposición del token condicionada al perfil de entorno de desarrollo.** Dificultad: Medio.
- [ ] **T-02 — Implementar emisión, uso único y cambio de contraseña.** Dificultad: Alto.
- [ ] **T-03 — Probar expiración, reutilización y no divulgación fuera de desarrollo.** Dificultad: Medio.
## Criterios de aceptación
### CA-01 — Solicitud
Dado un email de cuenta y perfil de entorno de desarrollo, cuando solicita recuperación, entonces se genera un token temporal de un solo uso y la respuesta de la API lo incluye solo en ese perfil; en cualquier otro perfil la respuesta no lo expone.
### CA-02 — Cambio válido
Dado un token temporal válido y una nueva contraseña válida, cuando confirma el cambio, entonces la contraseña queda actualizada con hash adaptativo y el token se consume.
### CA-03 — Token no válido
Dado un token usado, inválido o vencido, cuando intenta cambiar la contraseña, entonces se rechaza y la contraseña no cambia.
## Definition of Done
- [ ] CA-01 a CA-03 validados.
- [ ] Token temporal no queda en logs ni repositorio; contraseña no queda en texto plano.
- [ ] La entrega condicionada por perfil de entorno está implementada y probada en ambos perfiles.
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
- 2026-09-28 — Mecanismo de entrega del token aprobado por el usuario (ver Notas y decisiones).
- 2026-09-28 — HU aprobada por el usuario (`Aprobada`).
## Notas y decisiones
- Aprobado (2026-09-28): respuesta de API controlada por perfil de entorno de desarrollo; nunca en producción ni en logs.
