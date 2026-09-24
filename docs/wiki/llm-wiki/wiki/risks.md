# Riesgos y preguntas abiertas

1. La raíz tiene metadatos Git activos además de los dos repositorios hijos; no se decide aquí si es plantilla del trainer o cómo se preserva la regla de dos repositorios.
2. `citas-api` y `citas-web` no tienen commits base ni `develop`.
3. No hay contrato REST aprobado.
4. El PRD no fija estrategia transaccional/índices/expiración para impedir doble reserva.
5. No está definida la máquina completa de estados ni la matriz de transiciones.
6. No está definido si existen varias afiliaciones activas por usuario.
7. No está definido si `Medicina General` es catálogo fijo o especialidad configurable.
8. El framework frontend se decide después de Stitch/AI Studio.
9. El modelo de referencia de `database/reference/` no debe ingerirse antes de la autorización didáctica del trainer.
