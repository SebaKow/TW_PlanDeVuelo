/* Carga de datos */
insert into avion (capacidadTripulantes) values (4);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (8);

insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 06:45:00', '2010-01-01 09:00:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 10:00:00', '2010-01-01 12:30:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 13:25:00', '2010-01-01 17:50:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 22:10:00', '2010-01-02 01:00:00');

insert into planDeVuelo (avion_id, pvcontienev_id) values (1, 1);
insert into planDeVuelo (avion_id, pvcontienev_id) values (2, 1);
insert into planDeVuelo (avion_id, pvcontienev_id) values (3, 2);
insert into planDeVuelo (avion_id, pvcontienev_id) values (4, 2);

insert into vuelo (origen, destino, pvcontienev_id) values ("Salta", "Tucuman", 1);
insert into vuelo (origen, destino, pvcontienev_id) values ("Tucuman", "Jujuy", 1);
insert into vuelo (origen, destino, pvcontienev_id) values ("Jujuy", "Formosa", 2);
insert into vuelo (origen, destino, pvcontienev_id) values ("Formosa", "Salta", 2);

insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Admin", "Admin", 11111111, "admin@admin.com", "admin", 1, null);
insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Emiliano", "Ovejero", 10000000, "emiovejero@gmail.com", "123", 0, 1);
insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Sebastian", "Dominikow", 20000000, "sebadominikow@gmail.com", "123", 0, 2);
insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Rodrigo", "Sosa", 30000000, "rodrisosa@gmail.com", "123", 0, 2);
insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Lionel", "Messi", 40000000, "leomessi@gmail.com", "123", 0, 3);
insert into usuario (nombre, apellido, dni, email, password, esAdmin, plandevuelo_id) values ("Cristiano", "Ronaldo", 50000000, "cr7@gmail.com", "123", 0, 3);

/* ============================== */