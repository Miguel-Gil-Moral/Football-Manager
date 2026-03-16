use football_manager;

/*(MARIOOOOOOOO_____!!!!!!!!!!!!!!!!!!____________________!!!!!!!!!!!!!!!!!!!_____________________!!!!!!!!!!!!!!!!!!!!!!)1- Donat el nom d'una lliga i la temporada. 
Fer que tots els partits de la jornada 5 acabin en 0-0.*/

/*(MARIOOOOOOOO_____!!!!!!!!!!!!!!!!!!____________________!!!!!!!!!!!!!!!!!!!_____________________!!!!!!!!!!!!!!!!!!!!!!)2- La nostra base de dades està creixent molt i hem d'alliberar una mica d'espai. 
Esborra totes les ciutats en les quals no hi hagi cap equip.*/

/*(MARIOOOOOOOO_____!!!!!!!!!!!!!!!!!!____________________!!!!!!!!!!!!!!!!!!!_____________________!!!!!!!!!!!!!!!!!!!!!!)3- Baixa un 10% la motivació d'aquells jugadors que cobren menys de 5 milions i tenen 35 anys o més.*/

/*(MARIOOOOOOOO_____!!!!!!!!!!!!!!!!!!____________________!!!!!!!!!!!!!!!!!!!_____________________!!!!!!!!!!!!!!!!!!!!!!)4- S'ha celebrat un entrenament especial de porters dels primers equips. 
Puja en 3 punts la qualitat de tots els porters de la base de dades que no pertanyin a un equip filial.*/

/*✅ 5- Afegeix un camp a la taula "Jugadors" per indicar si el jugador està disponible per a ser fitxat. 
De moment posarem com a disponibles tots aquells jugadors que hagin estat contractats durant el primer trimestre del 2024. 
La resta de jugadors de la base de dades hauran d'aparèixer com a no disponibles per a ser fitxats.*/

alter table jugadors
add disponible tinyint not null;

update jugadors 
inner join jugadors_equips on jugadors.persones_id = jugadors_equips.jugadors_id
set disponible = 1
where jugadors_equips.data_fitxatge between '2024-01-01' and '2024-03-31';

select * from persones;

select * from jugadors;

/*6- Estem a punt de crear una nova lliga. 
Hi participaran 6 equips al llarg de 6 jornades.
Realitza els INSERT necessaris.*/



/*7- Crea els INSERT necessaris per poder afegir una nova jornada de lliga amb un partit en què hi hagi algun gol.*/

-- insert into jornada;-- 

select * from jornades;

/*✅ 8- Crea els INSERT necessaris per poder donar un nou equip d'altra a la nostra base de dades, incloent el seu estadi, l'entrenador i 2 jugadors.*/

insert into persones values (199, 'Miguel', 'Gil', '2005-02-17', 90, 10000000, 'jugador');
insert into persones values (200, 'Darrell', 'Zapata', '2004-01-05', 89, 1, 'jugador');
insert into persones values (201, 'David', 'Garcia', '2003-12-16', 88, 0, 'entrenador');
insert into entrenadors values (201, 0, 0);
insert into jugadors values (199, 13, 90, 3);
insert into jugadors values (200, 12, 89, 2);
insert into estadis values (34, 'En algun lugar de aqui', 1);
insert into equips values (38, 'Equipo nuevo', 2026, 'Pepito los palotes', 1, 34, null);
insert into jugadors_equips values ('2024-02-17', 199, 38, null);
insert into jugadors_equips values ('2024-01-05', 200, 38, null);
insert into entrenar_equips values ('2024-03-16', 201, 38, null);