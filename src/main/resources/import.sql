/* Carga de datos */
use db;
insert into avion (capacidadTripulantes) values (4);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (8);

insert into planDeVuelo (descripcion, avion_id, fecha) values ("BCFT", 1,'2019-10-10 06:00:00');
insert into planDeVuelo (descripcion, avion_id, fecha) values ("CFTB", 2,'2019-10-10 13:00:00');
insert into planDeVuelo (descripcion, avion_id, fecha) values ("FTBC", 3,'2019-10-10 21:00:00');
insert into planDeVuelo (descripcion, avion_id, fecha) values ("TBCF", 4,'2019-10-10 08:00:00');

insert into vuelo (origen, destino,duracion, duracionString, estado) values ("Salta", "Tucuman",'1970-01-01 01:00:00', "01:00:00",b'1');
insert into vuelo (origen, destino,duracion, duracionString, estado) values ("Tucuman", "Jujuy",'1970-01-01 01:15:00', "01:15:00",b'1');
insert into vuelo (origen, destino,duracion, duracionString, estado) values ("Jujuy", "Formosa",'1970-01-01 01:30:00', "01:30:00",b'1');
insert into vuelo (origen, destino,duracion, duracionString, estado) values ("Formosa", "Salta",'1970-01-01 02:10:00', "02:10:00",b'1');

insert into itinerario (despegueEstimado, despegueReal, aterrizajeEstimado, aterrizajeReal, plandevuelo_id) values('2010-01-01 06:45:00', '2010-01-01 06:50:00', '2010-01-01 09:00:00', '2010-01-01 09:05:00', 1);
insert into itinerario (despegueEstimado, despegueReal, aterrizajeEstimado, aterrizajeReal, plandevuelo_id) values('2010-01-01 10:00:00', '2010-01-01 10:05:00', '2010-01-01 12:30:00', '2010-01-01 12:35:00', 1);
insert into itinerario (despegueEstimado, despegueReal, aterrizajeEstimado, aterrizajeReal, plandevuelo_id) values('2010-01-01 13:25:00', '2010-01-01 13:30:00', '2010-01-01 17:50:00', '2010-01-01 17:55:00', 2);
insert into itinerario (despegueEstimado, despegueReal, aterrizajeEstimado, aterrizajeReal, plandevuelo_id) values('2010-01-01 22:10:00', '2010-01-01 22:15:00', '2010-01-02 01:00:00', '2010-01-02 01:05:00', 2);

insert into itinerario_vuelo (itinerarios_id, vuelos_id) values (1,1);
insert into itinerario_vuelo (itinerarios_id, vuelos_id) values (2,1);
insert into itinerario_vuelo (itinerarios_id, vuelos_id) values (3,2);
insert into itinerario_vuelo (itinerarios_id, vuelos_id) values (4,2);

insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Admin", "Admin", 11111111, "admin@admin.com", "admin", 1,b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Emiliano", "Ovejero", 10000000, "emiovejero@gmail.com", "123", 0,b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Sebastian", "Dominikow", 20000000, "sebadominikow@gmail.com", "123", 0,b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Rodrigo", "Sosa", 30000000, "rodrisosa@gmail.com", "123", 0,b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Lionel", "Messi", 40000000, "leomessi@gmail.com", "123", 0,b'1');
insert into tripulante (nombre, apellido, dni, email, password, esAdmin, estado) values ("Cristiano", "Ronaldo", 50000000, "cr7@gmail.com", "123", 0,b'1');

insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (1,1);
insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (2,1);
insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (3,2);
insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (4,3);
insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (5,3);
insert into tripulante_plandevuelo(tripulantes_id, planesDeVuelo_id) values (6,4);
/* ============================== */