

-- Inserción en la tabla categories
INSERT IGNORE INTO categories (code, name) VALUES
('PED01', 'Plataformas de Entretenimiento Digital'),
('STR02', 'Gaming y Entretenimiento'),
('SFT02', 'Software y Herramientas de Productividad'),
('EDU03', 'Educación y Formación Online'),
('ECM04', 'E-commerce'),
('NOT05', 'Periódico Digital y Noticias'),
('SAL06', 'Salud y Bienestar'),
('CIT07', 'Citas y Redes Sociales Premium'),
('LIT08', 'Contenido Literario'),
('HBY09', 'Hobbies y Creatividad'),
('SUB10', 'Cajas de Suscripción'),
('SEC11', 'Seguridad Digital y Servicios de Tecnología');

-- Inserción en la tabla services
INSERT IGNORE INTO services (id, name, description, category_id) VALUES
(1, 'Control de suscripciones', 'Gestiona y controla todas tus suscripciones a servicios de entretenimiento, plataformas digitales, etc.', 1),
(2, 'Notificaciones de próximos pagos', 'Recibe alertas y recordatorios sobre tus próximos pagos de suscripciones y servicios.', 1),
(3, 'Ver transacciones cargadas', 'Consulta las transacciones realizadas en tus servicios de suscripción y entretenimiento.', 1),
(4, 'Analizar suscripciones', 'Obtén un análisis detallado de todas tus suscripciones activas y sus costos.', 1),
(5, 'Resumen de resultados', 'Genera un resumen de todas las actividades realizadas en tus servicios de suscripción.', 1),
(6, 'Exportar resultados', 'Exporta los resultados y análisis de tus suscripciones a un formato de archivo compatible.', 1),
(7, 'Opciones de configuración', 'Accede a las configuraciones de tus servicios de suscripción, como pagos y renovaciones automáticas.', 1),
(8, 'Recomendaciones de suscripción', 'Recibe sugerencias personalizadas de servicios según tu historial y preferencias.', 1),
(9, 'Gestión de pagos recurrentes', 'Configura pagos automáticos o manuales para tus servicios de suscripción.', 1),
(10, 'Historial de pagos', 'Consulta el historial completo de pagos realizados a los servicios de suscripción.', 1),
(11, 'Soporte técnico', 'Accede a asistencia técnica para resolver problemas relacionados con tus servicios de suscripción.', 2),
(12, 'Actualizaciones de software', 'Recibe notificaciones sobre nuevas actualizaciones de tus herramientas y programas.', 2),
(13, 'Backup y recuperación de datos', 'Realiza copias de seguridad de tus datos importantes y recupéralos cuando sea necesario.', 2),
(14, 'Control parental', 'Configura controles parentales para restringir contenido inapropiado en plataformas de entretenimiento.', 2),
(15, 'Monitoreo de productividad', 'Mide y controla tu productividad a través de herramientas integradas en el software.', 2),
(16, 'Planificación de tareas', 'Organiza y gestiona tus tareas y proyectos con herramientas avanzadas de planificación.', 2),
(17, 'Configuración de privacidad', 'Ajusta y personaliza las opciones de privacidad en tus herramientas de productividad.', 2),
(18, 'Análisis de rendimiento', 'Obtén un análisis detallado del rendimiento de tus herramientas y servicios de software.', 2),
(19, 'Gestión de cuentas y usuarios', 'Administra cuentas de usuarios y accesos a servicios dentro de tu software de productividad.', 2),
(20, 'Curso de formación online', 'Accede a cursos online de formación en diversas áreas y especialidades.', 3),
(21, 'Examen y evaluación de cursos', 'Realiza exámenes y evaluaciones en línea para certificar tu conocimiento en diversos temas.', 3);

-- Insertar datos de ejemplo para 'roles'
INSERT IGNORE INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MANAGER'),
(3, 'ROLE_USER');


-- Insertar datos de ejemplo para 'users'. La contraseña de cada usuario es password
INSERT IGNORE INTO users (id, username, password, enabled, first_name, last_name, image, created_date, last_modified_date, last_password_change_date) VALUES
(1, 'admin', '$2b$12$FVRijCavVZ7Qt15.CQssHe9m/6eLAdjAv0PiOKFIjMU161wApxzye', true, 'Admin', 'User', '/images/admin.jpg', NOW(), NOW(), NOW()),
(2, 'manager', '$2b$12$FVRijCavVZ7Qt15.CQssHe9m/6eLAdjAv0PiOKFIjMU161wApxzye', true, 'Manager', 'User', '/images/manager.jpg', NOW(), NOW(), NOW()),
(3, 'normal', '$2b$12$FVRijCavVZ7Qt15.CQssHe9m/6eLAdjAv0PiOKFIjMU161wApxzye', true, 'Manager', 'User', '/images/user.jpg', NOW(), NOW(), NOW()),
(4, 'Naty-91', 'prueba1', true, 'Regular', 'User', '/images/user.jpg', NOW(), NOW(), NOW());


-- Asignar el rol de administrador al usuario con id 1
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(1, 1);
-- Asignar el rol de gestor al usuario con id 2
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(2, 2);
-- Asignar el rol de usuario normal al usuario con id 3
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(3, 3);

-- Asignar el rol de usuario normal al usuario con id 3
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(4, 2);
