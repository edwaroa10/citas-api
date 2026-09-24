---
id: HU-001
tipo: historia-de-usuario
titulo: Diseño de datos normalizado
estado: Pendiente de aprobación
epica: "[[EP-001-fundacion-y-acceso]]"
esfuerzo: Alto
sprint_sugerido: S1 — Fundación segura
dependencias: []
relacionadas: ["[[HU-002-catalogos-fijos]]"]
fuentes: ["PRD §7", "RESTRICCIONES_TECNICAS §Base de datos", "REQUISITOS_NORMALIZACION_3FN"]
---
# HU-001 — Diseño de datos normalizado
## Historia de usuario
**COMO** equipo del producto **QUIERO** definir y justificar un modelo relacional hasta 3FN **PARA** soportar citas y sus reglas sin duplicación ni pérdida de trazabilidad.
## Alcance
- Entregables de modelo: ER, claves, cardinalidades, dependencias funcionales y explicación 1FN→3FN.
- Datos requeridos para usuarios/roles, oferta, agenda, citas, estados, reprogramación y tokens compatibles con el PRD.
## Fuera de alcance
- Imponer tablas, motor distinto de MySQL 8.4 o usar el SQL de referencia del trainer como fuente.
## Reglas de negocio
- Relaciones N:M se resuelven; no se repiten nombres de catálogos; se justifican doble reserva, 60 min, reprogramación y auditoría.
## Dependencias y relaciones
- Épica: [[EP-001-fundacion-y-acceso]]; relacionada: [[HU-002-catalogos-fijos]].
## Esfuerzo
**Nivel:** Alto. **Justificación:** atraviesa todas las capacidades y exige justificar integridad sin decidir una implementación no especificada.
## Tareas de desarrollo
- [ ] **T-01 — Modelar entidades y cardinalidades.** Dificultad: Alto. Representar solo capacidades exigidas.
- [ ] **T-02 — Documentar dependencias funcionales y normalización.** Dificultad: Medio. Demostrar 1FN, 2FN y 3FN.
- [ ] **T-03 — Definir claves, restricciones e índices candidatos.** Dificultad: Alto. Incluir soporte para agenda y evitar doble reserva.
## Criterios de aceptación
### CA-01 — Cobertura del dominio
Dado el PRD, cuando se revise el modelo, entonces representa todas las capacidades listadas por el requisito de 3FN sin listas en columnas.
### CA-02 — Normalización justificable
Dado cada relación y atributo no clave, cuando se analice la normalización, entonces se documentan dependencias y no existen dependencias parciales o transitivas injustificadas.
### CA-03 — Integridad de agenda
Dado citas de 30/60 minutos y una reprogramación pendiente, cuando se revise el diseño, entonces explica cómo evita doble reserva y conserva la cita original.
## Definition of Done
- [ ] CA-01 a CA-03 tienen evidencia de revisión.
- [ ] Existe diagrama ER y justificación de claves, cardinalidades, catálogos e índices candidatos.
- [ ] El diseño declara la estrategia aún pendiente para concurrencia/retención si no fue aprobada.
- [ ] Trazabilidad actualizada en `docs/wiki/scrum/`.
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
- No se fija una tabla ni un algoritmo de concurrencia antes de aprobación.
