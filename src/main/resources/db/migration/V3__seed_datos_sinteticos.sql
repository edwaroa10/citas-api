-- ============================================================
-- V3 — Datos sintéticos de entrenamiento (derivado de database/reference/db.sql)
-- EPS/planes demo, usuarios, profesionales, disponibilidad, citas y
-- reprogramaciones de ejemplo. TODO es ficticio; no representa
-- pacientes, profesionales ni procesos reales de FCV.
-- Password de laboratorio para TODOS estos usuarios: Demo1234*
-- ============================================================

INSERT INTO eps (id, code, name, active) VALUES
(1, 'EPS_DEMO_A', 'EPS Demo Salud', TRUE),
(2, 'EPS_DEMO_B', 'EPS Demo Familiar', TRUE),
(3, 'PARTICULAR_DEMO', 'Atención Particular Demo', TRUE)
ON DUPLICATE KEY UPDATE name = VALUES(name), active = VALUES(active);

INSERT INTO eps_plans (id, eps_id, regime_id, code, name, active) VALUES
(1, 1, 1, 'A-CONTRIB', 'Plan Contributivo Demo', TRUE),
(2, 1, 2, 'A-SUBS', 'Plan Subsidiado Demo', TRUE),
(3, 2, 1, 'B-CONTRIB', 'Plan Contributivo Familiar Demo', TRUE),
(4, 2, 3, 'B-ESPECIAL', 'Plan Especial Demo', TRUE),
(5, 3, 5, 'PARTICULAR', 'Particular / pago directo', TRUE)
ON DUPLICATE KEY UPDATE name = VALUES(name), active = VALUES(active);

-- Hash BCrypt generado únicamente para el entorno de entrenamiento.
INSERT INTO users
(id, first_name, last_name, document_type, document_number, email, phone, password_hash, active, email_verified)
VALUES
(1, 'Admin', 'Laboratorio', 'CC', '900000001', 'admin@demo.invalid', '3000000001', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),

(10, 'Andrea', 'Ruiz', 'CC', '910000010', 'andrea.ruiz@demo.invalid', '3100000010', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(11, 'Carlos', 'Mejía', 'CC', '910000011', 'carlos.mejia@demo.invalid', '3100000011', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(12, 'Diana', 'Torres', 'CC', '910000012', 'diana.torres@demo.invalid', '3100000012', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(13, 'Felipe', 'Rojas', 'CC', '910000013', 'felipe.rojas@demo.invalid', '3100000013', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(14, 'Laura', 'Mendoza', 'CC', '910000014', 'laura.mendoza@demo.invalid', '3100000014', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(15, 'Mateo', 'García', 'CC', '910000015', 'mateo.garcia@demo.invalid', '3100000015', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(16, 'Natalia', 'Vargas', 'CC', '910000016', 'natalia.vargas@demo.invalid', '3100000016', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(17, 'Sergio', 'Castro', 'CC', '910000017', 'sergio.castro@demo.invalid', '3100000017', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),

(100, 'Paciente', 'Uno', 'CC', '920000100', 'paciente1@demo.invalid', '3200000100', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(101, 'Paciente', 'Dos', 'CC', '920000101', 'paciente2@demo.invalid', '3200000101', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(102, 'Paciente', 'Tres', 'CC', '920000102', 'paciente3@demo.invalid', '3200000102', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(103, 'Paciente', 'Cuatro', 'CC', '920000103', 'paciente4@demo.invalid', '3200000103', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(104, 'Paciente', 'Cinco', 'CC', '920000104', 'paciente5@demo.invalid', '3200000104', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE),
(105, 'Paciente', 'Seis', 'CC', '920000105', 'paciente6@demo.invalid', '3200000105', '$2y$10$QAPT/bPvvEILB0ovqykfTuwSBznwY2p0rJhZJguneKjk2dr7VQFeG', TRUE, TRUE)
ON DUPLICATE KEY UPDATE
  first_name = VALUES(first_name),
  last_name = VALUES(last_name),
  phone = VALUES(phone),
  active = VALUES(active);

INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(1, 3),
(10, 2), (11, 2), (12, 2), (13, 2),
(14, 2), (15, 2), (16, 2), (17, 2),
(100, 1), (101, 1), (102, 1), (103, 1), (104, 1), (105, 1);

INSERT INTO professionals
(id, user_id, professional_code, license_number, active)
VALUES
(1, 10, 'PROF-001', 'RM-DEMO-0001', TRUE),
(2, 11, 'PROF-002', 'RM-DEMO-0002', TRUE),
(3, 12, 'PROF-003', 'RM-DEMO-0003', TRUE),
(4, 13, 'PROF-004', 'RM-DEMO-0004', TRUE),
(5, 14, 'PROF-005', 'RM-DEMO-0005', TRUE),
(6, 15, 'PROF-006', 'RM-DEMO-0006', TRUE),
(7, 16, 'PROF-007', 'RM-DEMO-0007', TRUE),
(8, 17, 'PROF-008', 'RM-DEMO-0008', TRUE)
ON DUPLICATE KEY UPDATE active = VALUES(active);

-- Profesionales totalmente ficticios; las asignaciones sirven solo al laboratorio.
INSERT IGNORE INTO professional_specialties
(professional_id, specialty_id, is_primary, active)
VALUES
(1, 1, TRUE, TRUE),
(2, 1, TRUE, TRUE),
(3, 2, TRUE, TRUE),
(3, 4, FALSE, TRUE),
(4, 3, TRUE, TRUE),
(4, 5, FALSE, TRUE),
(5, 6, TRUE, TRUE),
(5, 7, FALSE, TRUE),
(6, 8, TRUE, TRUE),
(6, 9, FALSE, TRUE),
(7, 11, TRUE, TRUE),
(8, 12, TRUE, TRUE),
(8, 10, FALSE, TRUE);

INSERT IGNORE INTO professional_locations
(professional_id, location_id, active)
VALUES
(1,1,TRUE),(1,2,TRUE),
(2,1,TRUE),(2,2,TRUE),
(3,1,TRUE),(3,2,TRUE),
(4,1,TRUE),(4,2,TRUE),
(5,1,TRUE),(5,2,TRUE),
(6,1,TRUE),(6,2,TRUE),
(7,1,TRUE),(7,2,TRUE),
(8,1,TRUE),(8,2,TRUE);

INSERT INTO user_insurance_affiliations
(id, user_id, plan_id, membership_number, is_current, valid_from)
VALUES
(1, 100, 1, 'AF-DEMO-100', TRUE, CURDATE()),
(2, 101, 2, 'AF-DEMO-101', TRUE, CURDATE()),
(3, 102, 3, 'AF-DEMO-102', TRUE, CURDATE()),
(4, 103, 4, 'AF-DEMO-103', TRUE, CURDATE()),
(5, 104, 5, 'AF-DEMO-104', TRUE, CURDATE()),
(6, 105, 1, 'AF-DEMO-105', TRUE, CURDATE())
ON DUPLICATE KEY UPDATE
  is_current = VALUES(is_current),
  valid_from = VALUES(valid_from);

-- Disponibilidad dinámica: fechas relativas a la fecha de aplicación de esta migración.
-- Cada profesional recibe varios bloques en ambas sedes.
INSERT INTO availability_blocks
(id, professional_id, location_id, available_date, start_time, end_time, active)
VALUES
(1,1,1,DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:00:00','12:00:00',TRUE),
(2,1,1,DATE_ADD(CURDATE(),INTERVAL 1 DAY),'14:00:00','17:00:00',TRUE),
(3,1,2,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'08:00:00','12:00:00',TRUE),
(4,1,2,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'14:00:00','17:00:00',TRUE),

(5,2,2,DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:00:00','12:00:00',TRUE),
(6,2,2,DATE_ADD(CURDATE(),INTERVAL 1 DAY),'14:00:00','17:00:00',TRUE),
(7,2,1,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'08:00:00','12:00:00',TRUE),
(8,2,1,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'14:00:00','17:00:00',TRUE),

(9,3,1,DATE_ADD(CURDATE(),INTERVAL 2 DAY),'08:00:00','12:00:00',TRUE),
(10,3,1,DATE_ADD(CURDATE(),INTERVAL 2 DAY),'14:00:00','17:00:00',TRUE),
(11,3,2,DATE_ADD(CURDATE(),INTERVAL 5 DAY),'08:00:00','12:00:00',TRUE),
(12,3,2,DATE_ADD(CURDATE(),INTERVAL 5 DAY),'14:00:00','17:00:00',TRUE),

(13,4,2,DATE_ADD(CURDATE(),INTERVAL 2 DAY),'08:00:00','12:00:00',TRUE),
(14,4,2,DATE_ADD(CURDATE(),INTERVAL 2 DAY),'14:00:00','17:00:00',TRUE),
(15,4,1,DATE_ADD(CURDATE(),INTERVAL 6 DAY),'08:00:00','12:00:00',TRUE),
(16,4,1,DATE_ADD(CURDATE(),INTERVAL 6 DAY),'14:00:00','17:00:00',TRUE),

(17,5,1,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'08:00:00','12:00:00',TRUE),
(18,5,1,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'14:00:00','17:00:00',TRUE),
(19,5,2,DATE_ADD(CURDATE(),INTERVAL 7 DAY),'08:00:00','12:00:00',TRUE),
(20,5,2,DATE_ADD(CURDATE(),INTERVAL 7 DAY),'14:00:00','17:00:00',TRUE),

(21,6,2,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'08:00:00','12:00:00',TRUE),
(22,6,2,DATE_ADD(CURDATE(),INTERVAL 3 DAY),'14:00:00','17:00:00',TRUE),
(23,6,1,DATE_ADD(CURDATE(),INTERVAL 8 DAY),'08:00:00','12:00:00',TRUE),
(24,6,1,DATE_ADD(CURDATE(),INTERVAL 8 DAY),'14:00:00','17:00:00',TRUE),

(25,7,1,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'08:00:00','12:00:00',TRUE),
(26,7,1,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'14:00:00','17:00:00',TRUE),
(27,7,2,DATE_ADD(CURDATE(),INTERVAL 9 DAY),'08:00:00','12:00:00',TRUE),
(28,7,2,DATE_ADD(CURDATE(),INTERVAL 9 DAY),'14:00:00','17:00:00',TRUE),

(29,8,2,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'08:00:00','12:00:00',TRUE),
(30,8,2,DATE_ADD(CURDATE(),INTERVAL 4 DAY),'14:00:00','17:00:00',TRUE),
(31,8,1,DATE_ADD(CURDATE(),INTERVAL 10 DAY),'08:00:00','12:00:00',TRUE),
(32,8,1,DATE_ADD(CURDATE(),INTERVAL 10 DAY),'14:00:00','17:00:00',TRUE)
ON DUPLICATE KEY UPDATE active = VALUES(active);

-- Generar slots atómicos de 30 min a partir de los bloques.
INSERT IGNORE INTO professional_slots
(availability_block_id, start_at, end_at)
SELECT
    ab.id,
    TIMESTAMP(
        ab.available_date,
        ADDTIME(ab.start_time, SEC_TO_TIME(seq.n * 1800))
    ) AS start_at,
    TIMESTAMP(
        ab.available_date,
        ADDTIME(ab.start_time, SEC_TO_TIME((seq.n + 1) * 1800))
    ) AS end_at
FROM availability_blocks ab
JOIN (
    SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7
    UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
    UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
    UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19
) seq
WHERE ab.active = TRUE
  AND ADDTIME(ab.start_time, SEC_TO_TIME((seq.n + 1) * 1800)) <= ab.end_time;

-- Citas sintéticas de ejemplo.
-- Cita general: APPROVED automáticamente.
INSERT INTO appointments
(id, patient_user_id, professional_id, location_id, specialty_id,
 insurance_affiliation_id, status_id, reason,
 scheduled_start_at, scheduled_end_at, created_by_user_id,
 approved_by_user_id, approved_at)
VALUES
(
  1, 100, 1, 1, 1, 1, 2,
  'Consulta general de demostración',
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:00:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:30:00'),
  100, NULL, NOW()
),
(
  2, 101, 3, 1, 2, 2, 1,
  'Valoración especializada de demostración',
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 2 DAY),'09:00:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 2 DAY),'09:30:00'),
  101, NULL, NULL
),
(
  3, 102, 7, 1, 11, 3, 2,
  'Consulta de ortopedia de demostración',
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 4 DAY),'10:00:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 4 DAY),'11:00:00'),
  102, 1, NOW()
)
ON DUPLICATE KEY UPDATE reason = VALUES(reason);

-- Reservar los slots asociados a esas citas.
UPDATE professional_slots ps
JOIN availability_blocks ab ON ab.id = ps.availability_block_id
SET ps.appointment_id = 1
WHERE ab.professional_id = 1
  AND ab.location_id = 1
  AND ps.start_at >= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:00:00')
  AND ps.end_at <= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:30:00');

UPDATE professional_slots ps
JOIN availability_blocks ab ON ab.id = ps.availability_block_id
SET ps.appointment_id = 2
WHERE ab.professional_id = 3
  AND ab.location_id = 1
  AND ps.start_at >= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 2 DAY),'09:00:00')
  AND ps.end_at <= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 2 DAY),'09:30:00');

UPDATE professional_slots ps
JOIN availability_blocks ab ON ab.id = ps.availability_block_id
SET ps.appointment_id = 3
WHERE ab.professional_id = 7
  AND ab.location_id = 1
  AND ps.start_at >= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 4 DAY),'10:00:00')
  AND ps.end_at <= TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 4 DAY),'11:00:00');

INSERT INTO appointment_status_history
(appointment_id, status_id, changed_by_user_id, change_source, reason)
VALUES
(1, 2, NULL, 'SYSTEM', 'Aprobación automática por tratarse de cita de Medicina General'),
(2, 1, 101, 'USER', 'Solicitud especializada pendiente de aprobación administrativa'),
(3, 1, 102, 'USER', 'Solicitud especializada creada'),
(3, 2, 1, 'ADMIN', 'Aprobación administrativa de laboratorio');

INSERT INTO reschedule_requests
(id, appointment_id, requested_by_user_id, requested_location_id, status_id,
 previous_start_at, previous_end_at, requested_start_at, requested_end_at)
VALUES
(
  1, 1, 100, 2, 1,
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:00:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'08:30:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 3 DAY),'09:00:00'),
  TIMESTAMP(DATE_ADD(CURDATE(),INTERVAL 3 DAY),'09:30:00')
)
ON DUPLICATE KEY UPDATE status_id = VALUES(status_id);
