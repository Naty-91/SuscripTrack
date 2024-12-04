INSERT INTO categories (code name) VALUES
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


INSERT INTO services (name, description, categories_id) VALUES
('Control de suscripciones', 'Gestiona y controla todas tus suscripciones a servicios de entretenimiento, plataformas digitales, etc.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Notificaciones de próximos pagos', 'Recibe alertas y recordatorios sobre tus próximos pagos de suscripciones y servicios.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Ver transacciones cargadas', 'Consulta las transacciones realizadas en tus servicios de suscripción y entretenimiento.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Analizar suscripciones', 'Obtén un análisis detallado de todas tus suscripciones activas y sus costos.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Resumen de resultados', 'Genera un resumen de todas las actividades realizadas en tus servicios de suscripción.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Exportar resultados', 'Exporta los resultados y análisis de tus suscripciones a un formato de archivo compatible.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Opciones de configuración', 'Accede a las configuraciones de tus servicios de suscripción, como pagos y renovaciones automáticas.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Recomendaciones de suscripción', 'Recibe sugerencias personalizadas de servicios según tu historial y preferencias.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Gestión de pagos recurrentes', 'Configura pagos automáticos o manuales para tus servicios de suscripción.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Historial de pagos', 'Consulta el historial completo de pagos realizados a los servicios de suscripción.', 1),  -- Categoria: Plataformas de Entretenimiento Digital
('Soporte técnico', 'Accede a asistencia técnica para resolver problemas relacionados con tus servicios de suscripción.', 2),  -- Categoria: Software y Herramientas de Productividad
('Actualizaciones de software', 'Recibe notificaciones sobre nuevas actualizaciones de tus herramientas y programas.', 2),  -- Categoria: Software y Herramientas de Productividad
('Backup y recuperación de datos', 'Realiza copias de seguridad de tus datos importantes y recupéralos cuando sea necesario.', 2),  -- Categoria: Software y Herramientas de Productividad
('Control parental', 'Configura controles parentales para restringir contenido inapropiado en plataformas de entretenimiento.', 2),  -- Categoria: Software y Herramientas de Productividad
('Monitoreo de productividad', 'Mide y controla tu productividad a través de herramientas integradas en el software.', 2),  -- Categoria: Software y Herramientas de Productividad
('Planificación de tareas', 'Organiza y gestiona tus tareas y proyectos con herramientas avanzadas de planificación.', 2),  -- Categoria: Software y Herramientas de Productividad
('Configuración de privacidad', 'Ajusta y personaliza las opciones de privacidad en tus herramientas de productividad.', 2),  -- Categoria: Software y Herramientas de Productividad
('Análisis de rendimiento', 'Obtén un análisis detallado del rendimiento de tus herramientas y servicios de software.', 2),  -- Categoria: Software y Herramientas de Productividad
('Gestión de cuentas y usuarios', 'Administra cuentas de usuarios y accesos a servicios dentro de tu software de productividad.', 2),  -- Categoria: Software y Herramientas de Productividad
('Curso de formación online', 'Accede a cursos online de formación en diversas áreas y especialidades.', 3),  -- Categoria: Educación y Formación Online
('Examen y evaluación de cursos', 'Realiza exámenes y evaluaciones en línea para certificar tu conocimiento en diversos temas.', 3);  -- Categoria: Educación y Formación Online
