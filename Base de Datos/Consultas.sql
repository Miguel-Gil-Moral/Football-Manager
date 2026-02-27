use football_manager;

/*✅ 1- Donat el nom de la lliga i la temporada. 
Es vol realitzar una consulta que retorni el nom de l'equip, l'any de fundació , 
el nom del president, el nom de la ciutat de l'equip, el nom de l'estadi i el nombre d'espectadors que tinguin un estadi entre 3.000 i 5.000 espectadors. 
Ordenar pel nombre d'espectadors de major a menor. Utilitzar alies per identificar millor les dades retornades.*/

set @nombre_liga = 'Liga F Moeve';
set @temporada_liga = 2024;

select *
from lligues;

select equips.nom 'Equipo', equips.any_fundacio 'Año_fundacion', equips.nom_president 'Presidente',
ciutats.nom 'Ciudad', estadis.nom 'Estadio', estadis.num_espectadors 'Cantidad_espectadores'
from equips
join participar_lligues on equips.id = participar_lligues.equips_id
join lligues on participar_lligues.lligues_id = lligues.id
join ciutats on equips.ciutats_id = ciutats.id
join estadis on equips.estadis_id = estadis.id
where estadis.num_espectadors between 3000 and 5000
and lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
order by estadis.num_espectadors desc;

/*✅ 2- Mostrar la ciutat, el nom de l'equip i el nom i cognom de l'entrenadors. 
Dels equips que siguin de 'Barcelona', 'Madrid' o 'Sevilla i que el nom del seu entrenador no comenci per 'F' i el seu cognom contingui la 'e'.*/

select ciutats.nom 'Ciudad', equips.nom 'Equipo', persones.nom 'Nombre', persones.cognoms 'Apellido'
from equips
join ciutats on equips.ciutats_id = ciutats.id
join entrenar_equips on equips.id = entrenar_equips.equips_id
join entrenadors on entrenar_equips.entrenadors_id = entrenadors.persones_id
join persones on entrenadors.persones_id = persones.id
where ciutats.nom in ('Barcelona', 'Madrid', 'Sevilla')
and persones.nom not like 'F%'
and persones.cognoms like '%e%';

/*✅ 3- Donat el nom de la lliga i la temporada. 
Mostrar la classificació de la lliga amb el nom de l'equip i la puntuació total. 
Ordenar els equips pel nombre de punts de major a menor.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;

select equips.nom 'Equipo', sum(partits.punts_local) 'Puntos_totales'
from equips
join partits on equips.id = partits.equips_id_local
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
group by equips.nom
union
select equips.nom 'Equipo', sum(partits.punts_visitant) 'Puntos_totales'
from equips
join partits on equips.id = partits.equips_id_visitant
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
group by equips.nom
order by Puntos_totales desc;

select nom
from equips;

/*✅ 4- Mostrar l'entrenador i els jugadors d'un equip donat. 
S'ha de mostrar el nom de l'equip, el tipus de persona, el nom i el cognoms de l'entrenador o jugador concatenats i amb un espai al mig.*/

set @nombre_equipo = 'FC Barcelona';

select equips.nom 'Nombre Equipo', persones.tipus_persona 'Tipo', concat(persones.nom, ' ', persones.cognoms) 'Nombre_completo'
from persones
join entrenadors on persones.id = entrenadors.persones_id
join entrenar_equips on entrenadors.persones_id = entrenar_equips.entrenadors_id
join equips on entrenar_equips.equips_id = equips.id
where equips.nom = @nombre_equipo
union
select equips.nom 'Nombre Equipo', persones.tipus_persona 'Tipo', concat(persones.nom, ' ', persones.cognoms) 'Nombre_completo'
from persones
join jugadors on persones.id = jugadors.persones_id
join jugadors_equips on jugadors.persones_id = jugadors_equips.jugadors_id
join equips on jugadors_equips.equips_id = equips.id
where equips.nom = @nombre_equipo;

/*✅ 5- Donat un nom de lliga i una temporada, comptar el nombre de jugadors per cada posició. 
Mostrar la posició i el nombre de jugadors. 
Ordenar per la posició en ordre alfabètic. 
Només s'han de mostrar els jugadors que estiguin d'alta.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;

select persones.nom 'Nombre_jugador', posicions.posicio 'Posición'
from persones
join jugadors on persones.id = jugadors.persones_id
join posicions on jugadors.posicions_id = posicions.id
join jugadors_equips on jugadors.persones_id = jugadors_equips.jugadors_id
join equips on jugadors_equips.equips_id = equips.id
join participar_lligues on equips.id = participar_lligues.equips_id
join lligues on participar_lligues.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
order by posicions.posicio asc;

/*✅ 6- Donat el nom de la lliga, la temporada i el nom d'un equip, seleccionar tots els partits jugats per aquest equip en la temporada. 
Mostrar la data de la jornada, la jornada, el nom de l'equip local, el gols de l'equip local. els gols de l'equip visitant, el nom de l'equip visitant. 
S'han d'ordenar per la data de la jornada de menor a major.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;
set @nombre_equipo = 'FC Barcelona';

select jornades.data, jornades.jornada 'Num_jornada', equips_local.nom 'Equipo_local', partits.gols_local 'Goles_local', partits.gols_visitant 'Goles_visitante', equips_visitant.nom 'Equipo_visitante'
from partits
join equips equips_local on partits.equips_id_local = equips_local.id
join equips equips_visitant on partits.equips_id_visitant = equips_visitant.id
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
and (equips_local.nom = @nombre_equipo or @nombre_equipo = equips_visitant.nom)
order by jornades.data asc;

/*7- Donada una lliga, una temporada, un equip local i un equip visitant, seleccionar els gols marcats en aquest partit. 
Mostrar la data i la jornada en la que van jugar, el nom de l'equip local, el nom de l'equip visitant, els gols de l'equip local, 
els gols de l'equip visitant, el minut del gol, el nom i cognoms del jugador que ha fet gol, l'equip al que pertany el jugador i si ha estat de penalti o no. 
Ordenar la informació pel minut del gol.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;
set @nombre_equipo_local = 'FC Barcelona';
set @nombre_equipo_visitante = 'Real Madrid CF';

select jornades.data, jornades.jornada 'Num_jornada', equips_local.nom 'Equipo_local', partits.gols_local 'Goles_local', partits.gols_visitant 'Goles_visitante', 
equips_visitant.nom 'Equipo_visitante', partits_gols.minut 'Minuto', persones.nom 'Nombre', persones.cognoms 'Apellido', partits_gols.es_penal 'Esta_penalti'
from partits
join partits_gols on partits.id = partits_gols.partits_id
join equips equips_local on partits.equips_id_local = equips_local.id
join equips equips_visitant on partits.equips_id_visitant = equips_visitant.id
join jugadors_equips on equips_local.id = jugadors_equips.jugadors_id
and equips_visitant.id = jugadors_equips.jugadors_id
join jugadors on jugadors_equips.jugadors_id = jugadors.persones_id
join persones on jugadors.persones_id = persones.id
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
and (equips_local.nom = @nombre_equipo_local and @nombre_equipo_visitante = equips_visitant.nom)
order by jornades.data asc;

/*✅ 8- Donada una lliga i una temporada, calcular els gols que ha marcat cada jugador. 
Mostrar els nom i cognoms del jugador i el nombre de gols. 
S'ha d'ordenar pel nombre de gols major a menor, i només s'han de mostrar el 10 màxims golejadors.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;

select persones.nom 'Nombre', persones.cognoms 'Apellido', count(partits_gols.jugadors_id) 'Goles_marcados'
from jugadors
join persones on jugadors.persones_id = persones.id
join partits_gols on jugadors.persones_id = partits_gols.jugadors_id
join partits on partits_gols.partits_id = partits.id
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
group by persones.nom, persones.cognoms
order by Goles_marcados desc limit 10;

/*✅ 9- Buscar els jugadors que cobrin entre 7.000.000 i 12.000.000, tinguin un nivell de motivació igual o superior a 85 
i l'any de la seva data de naixement sigui 1959 o 1985 o 1992. 
Ordenar pel sou de major a menor.*/

select persones.nom, persones.cognoms, persones.sou, persones.nivell_motivacio, persones.data_naixement
from persones
join jugadors on persones.id = jugadors.persones_id
where persones.sou between 7000000 and 12000000
and persones.nivell_motivacio >= 85
and year(persones.data_naixement) in (1959, 1985, 1992)
order by persones.sou desc;

select *
from equips;

/*✅ 10- Donat el nom d'una lliga i la temporada. 
Mostrar el noms dels equips i la mitja de qualitat del seus jugadors. 
Només mostrar els equips que tinguin una mitja superior a 80, amb dos decimals. 
Ordenar per la mitja de menor a major.*/

set @nombre_liga = 'Liga F Moeve';
set @temporada_liga = 2024;

select equips.nom 'Equipo', round(avg(jugadors.qualitat), 2) 'Media_calidad'
from equips
join jugadors_equips on equips.id = jugadors_equips.equips_id
join jugadors on jugadors_equips.jugadors_id = jugadors.persones_id
join participar_lligues on equips.id = participar_lligues.equips_id
join lligues on participar_lligues.lligues_id = lligues.id
where lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
group by equips.nom
having Media_calidad > 80
order by Media_calidad asc;

/*11- Mostar el nom de l'equip i el nom de l'equip filial, de tots els equips que tinguin filial.*/

select equips.nom 'Equipo', equips.nom 'Equipo filial'
from equips
where equips.filial_equips_id is not null;

select * from equips;

/*12- Quins equips tenen més de 3 jugadors amb una qualitat superior a 85?*/

select equips.nom, jugadors.qualitat 'Calidad'
from equips
join jugadors_equips on equips.id = jugadors_equips.equips_id
join jugadors on jugadors_equips.jugadors_id = jugadors.persones_id
where jugadors.qualitat > 85;

/*13- Quina és la mitjana d'edat, amb dos decimals, dels jugadors de cada equip? 
Ordénala de major a menor*/

select equips.nom 'Equipo', persones.nom 'Jugador', round(avg(year(current_date() - persones.data_naixement)), 2) 'Media_edad'
from equips
join jugadors_equips on equips.id = jugadors_equips.equips_id
join jugadors on jugadors_equips.jugadors_id = jugadors.persones_id
join persones on jugadors.persones_id = persones.id
group by equips.nom, persones.nom
order by Media_edad desc;

/*14- Donat el nom d'una lliga i la temporada. 
Mostrar el màxim golejador*/

/*15- Donada una lliga i una temporada. 
Mostrar el dorsal, el nom i cognoms del jugador, i el nom de l'equip on juga de tots els defenses que han marcat més de 5 gols*/

/*✅ 16- Donada una lliga i una temporada. 
Mostrar els gols marcats per l'equip amb nom 'Girona FC'. 
S'han de comptar tant de local com de visitant.*/

set @nombre_liga = 'La Liga EA Sports';
set @temporada_liga = 2024;

select equips.nom, sum(partits.gols_local + partits.gols_visitant) 'Goles_marcados'
from equips
join partits on equips.id = partits.equips_id_local
join jornades on partits.jornades_id = jornades.id
join lligues on jornades.lligues_id = lligues.id
where equips.nom = 'Girona FC'
and lligues.nom = @nombre_liga
and lligues.temporada = @temporada_liga
group by equips.nom;

/*17- Donada una lliga i una temporada. 
Mostrar el nom de l'equip i els gols marcats, de tots els equips que han marcat els mateixos o més gols que els marcats per l'equip amb nom 'Girona FC'. 
Ordenar el resultat descendentment per nombre total de gols.*/




select equips.nom, partits.gols_local
from equips
order by partits.gols_local desc;