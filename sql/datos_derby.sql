INSERT INTO USUARIO (EMAIL, NOMBRE, ROL) VALUES 
('pruebaparaingweb@gmail.com', 'Prueba', 'Administrador'),
('avilavt@gmail.com', 'Cristian Ávila', 'Administrador');

INSERT INTO USUARIO (EMAIL, NOMBRE, ROL) VALUES 
('senan996@gmail.com', 'Senan', 'Administrador'),
('alaeak.aa@gmail.com', 'Alaeak', 'Administrador');

INSERT INTO TABLON (FECHA_CREACION, INFORMACION) VALUES 
('11/15/2019', 'Tablon de prueba'),
('9/22/2019', 'Primavera 2019'),
('10/15/2019', 'Tablón con comentarios');

INSERT INTO COMENTARIO (ID_TABLON, FECHA_CREACION, CONTENIDO) VALUES 
(3, '11/15/2019','Este es un comentario de prueba'),
(3, '11/13/2019','Este comentario es anterior al comentario anterior, o no?'),
(4, '9/22/2019', 'En el solemne día presente en este último año de la segunda decada del primer siglo del tercer milenio comienza la primavera'),
(5, '10/15/2019', 'Primer comentario en este "Tablón con comentarios" que va ha contener comentarios que comentan cosas como este comentario'),
(5, '11/15/2019', 'Segundo comentario escrito un mes después del primer comentario');

INSERT INTO COMENTARIO_USUARIO (ID_COMENTARIO, ID_USUARIO) VALUES 
(1,4),
(2,4),
(3,4),
(4,4),
(5,4);
