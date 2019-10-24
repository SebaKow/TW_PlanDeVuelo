/* Carga de datos */
use db;
insert into avion (capacidadTripulantes) values (4);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (8);

insert into planDeVuelo (descripcion, fecha, avion_id) values ("BCFT", '2019-10-10 06:00:00', 1);
insert into planDeVuelo (descripcion, fecha, avion_id) values ("CFTB", '2019-10-10 13:00:00', 2);
insert into planDeVuelo (descripcion, fecha, avion_id) values ("FTBC", '2019-10-10 21:00:00', 3);
insert into planDeVuelo (descripcion, fecha, avion_id) values ("TBCF", '2019-10-10 08:00:00', 4);

insert into vuelo (origen, destino, duracion, duracionString, estado) values ("Salta", "Tucuman", '1970-01-01 01:00:00', "01:00:00", b'1');
insert into vuelo (origen, destino, duracion, duracionString, estado) values ("Tucuman", "Jujuy", '1970-01-01 01:15:00', "01:15:00", b'1');
insert into vuelo (origen, destino, duracion, duracionString, estado) values ("Jujuy", "Formosa", '1970-01-01 01:30:00', "01:30:00", b'1');
insert into vuelo (origen, destino, duracion, duracionString, estado) values ("Formosa", "Salta", '1970-01-01 02:10:00', "02:10:00", b'1');


insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Admin", "Admin", 11111111, "admin@admin.com", "admin", 1, b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Emiliano", "Ovejero", 10000000, "emiovejero@gmail.com", "123", 0, b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Sebastian", "Dominikow", 20000000, "sebadominikow@gmail.com", "123", 0, b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Rodrigo", "Sosa", 30000000, "rodrisosa@gmail.com", "123", 0, b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Lionel", "Messi", 40000000, "leomessi@gmail.com", "123", 0, b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Cristiano", "Ronaldo", 50000000, "cr7@gmail.com", "123", 0, b'1');

insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (1, 1);
insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (2, 1);
insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (3, 2);
insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (4, 3);
insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (5, 3);
insert into tripulante_plandevuelo (tripulantes_id, planesDeVuelo_id) values (6, 4);
/* ============================== */