/* Carga de datos */
insert into avion (capacidadTripulantes) values (6);
insert into avion (capacidadTripulantes) values (8);
insert into avion (capacidadTripulantes) values (4);
insert into avion (capacidadTripulantes) values (6);

insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 06:45:00', '2010-01-01 09:00:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 10:00:00', '2010-01-01 12:30:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 13:25:00', '2010-01-01 17:50:00');
insert into pVContieneV (horaSalida, horaLlegada) values('2010-01-01 22:10:00', '2010-01-02 01:00:00');

insert into planDeVuelo (pVContieneV, avion) values (1, 9);
insert into planDeVuelo (pVContieneV, avion) values (4, 8);
insert into planDeVuelo (pVContieneV, avion) values (2, 7);
insert into planDeVuelo (pVContieneV, avion) values (3, 9);

insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Leandro", "Paredes", 10000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Manuel", "Lanzini", 20000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Franco", "Armani", 30000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Nicolas", "Tagliafico", 40000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Lautaro", "Martinez", 50000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Nicolas", "Otamendi", 60000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Exequiel", "Palacios", 70000000);
insert into tripulante (nombre, apellido, dni, planDeVuelo) values ("Rodrigo", "De Paul", 80000000);

insert into vuelo (origen, destino,pVContieneV) values ("Salta", "Tucuman", 1);
insert into vuelo (origen, destino,pVContieneV) values ("Tucuman", "Jujuy", 2);
insert into vuelo (origen, destino,pVContieneV) values ("Jujuy", "Formosa", 3);
insert into vuelo (origen, destino,pVContieneV) values ("Formosa", "Salta", 4);

insert into usuario (dni,email, password) values (1,"admin@admin.com", "admin");