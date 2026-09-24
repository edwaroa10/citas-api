# Dominio

Fuente principal: [PRD](../../../../../PRD.md) y [requisitos 3FN](../../../../../database/REQUISITOS_NORMALIZACION_3FN.md).

## Actores

`USER`, `PROFESSIONAL` y `ADMIN`. Los profesionales son usuarios ficticios administrados por ADMIN.

## Capacidades

Registro/login/JWT, perfil y afiliación, catálogos, profesionales, disponibilidad en bloques de 30 minutos, citas generales y especializadas, cancelación, reprogramación, agenda profesional, cierre de atención, bandeja administrativa y auditoría.

## Reglas críticas

No doble reserva; duración de especialidad de 30/60 minutos; slots consecutivos; no pasado; sede y especialidad habilitadas; generales auto-aprobadas; especializadas requieren ADMIN; rechazo requiere motivo; reprogramación conserva cita original hasta decisión.
