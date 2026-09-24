---
id: EP-001
tipo: epica
titulo: Fundación y acceso seguro
estado: Borrador
historias: ["[[HU-001-diseno-de-datos-3fn]]", "[[HU-002-catalogos-fijos]]", "[[HU-003-registro-de-usuario]]", "[[HU-004-sesion-jwt]]"]
dependencias: []
---
# EP-001 — Fundación y acceso seguro
## Objetivo
Permitir crear cuentas ficticias y acceder de forma autorizada sobre una base normalizada y catálogos comunes.
## Valor esperado
Una base verificable para capacidades posteriores sin datos reales ni secretos versionados.
## Actores
- USER; ADMIN; PROFESSIONAL.
## Alcance
- Modelo 3FN, catálogos fijos, registro y sesión access/refresh.
## Fuera de alcance
- Recuperación y perfil.
## Reglas de negocio
- Email y documento únicos; passwords no se almacenan en texto plano; roles participan en autorización.
## Dependencias
- Ninguna externa al mapa.
## Historias de usuario
- [[HU-001-diseno-de-datos-3fn]]
- [[HU-002-catalogos-fijos]]
- [[HU-003-registro-de-usuario]]
- [[HU-004-sesion-jwt]]
## Criterio de completitud de la épica
- [ ] Sus cuatro HU obligatorias están `Completada` y sus CA/DoD tienen evidencia.
## Riesgos e incógnitas
- Falta acordar detalles de contrato REST sin presuponer endpoints.
