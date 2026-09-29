# Log de la LLM Wiki

Registro append-only. No contiene conversaciones completas ni secretos.

| Fecha | Operación | Alcance | Resultado |
|---|---|---|---|
| 2026-09-17 | INGEST inicial | Estructura y procedencia de fuentes normativas | Wiki inicial creada; fuentes canónicas registradas, pendientes de snapshots RAW completos |
| 2026-09-28 | INGEST | Decisiones de concurrencia, retención, cierre de atención, recuperación de contraseña y framework frontend aprobadas por el usuario | `decisions.md` actualizado; 8 HU pasan a `Aprobada` en `docs/wiki/scrum` |
| 2026-09-29 | INGEST | Base de datos creada (Flyway V1-V3 sobre MySQL); frontend importado y reconciliado (`citas-web`) | `decisions.md` actualizado; sin cambios de contrato todavía |
| 2026-09-29 | INGEST | Contrato REST de auth y transporte JWT aprobados; HU-003/HU-004 aprobadas para GOAL_01_GUIADO_SIMPLE | `contracts.md` y `decisions.md` actualizados |
| 2026-09-29 | QUERY/LEARN | Implementación de HU-003/HU-004 en `citas-api` (hexagonal: dominio/aplicación/adaptadores REST+JPA+JWT) validada con `mvn test` | 23/23 pruebas verdes; HU-003 y HU-004 pasan a `Completada` con matriz de evidencia en `docs/wiki/scrum` |
