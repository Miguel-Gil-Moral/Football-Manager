/*✅ Donat el nom de la lliga i la temporada. 
Es vol realitzar una consulta que retorni el nom de l'equip, l'any de fundació , 
el nom del president, el nom de la ciutat de l'equip, el nom de l'estadi i el nombre d'espectadors que tinguin un estadi entre 3.000 i 5.000 espectadors. 
Ordenar pel nombre d'espectadors de major a menor. Utilitzar alies per identificar millor les dades retornades.*/

select lligues.nom 'Liga', lligues.temporada 'Temporada', equips.nom 'Equipo', equips.any_fundacio 'Año_fundacion', equips.nom_president 'Presidente',
 ciutats.nom 'Ciudad', estadis.nom 'Estadio', estadis.num_espectadors 'Cantidad_espectadores'
from lligues
join participar_lligues on lligues.id = participar_lligues.lligues_id
join equips on participar_lligues.equips_id = equips.id
join ciutats on equips.ciutats_id = ciutats.id
join estadis on equips.estadis_id = estadis.id
where estadis.num_espectadors between 3000 and 5000
order by estadis.num_espectadors desc;

/*✅ Mostrar la ciutat, el nom de l'equip i el nom i cognom de l'entrenadors. 
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

/*Donat el nom de la lliga i la temporada. 
Mostrar la classificació de la lliga amb el nom de l'equip i la puntuació total. 
Ordenar els equips pel nombre de punts de major a menor.*/

select lligues.nom 'Liga', lligues.temporada 'Temporada', equips.nom 'Equipo', sum(partits.punts_local + partits.punts_visitant) 'Puntos_total'
from lligues
join participar_lligues on lligues.id = participar_lligues.lligues_id
join equips on participar_lligues.equips_id = equips.id
join partits on equips.id = partits.equips_id_local
and equips.id = partits.equips_id_visitant
group by lligues.nom, lligues.temporada, equips.nom
order by Puntos_total desc;

/*Mostrar l'entrenador i els jugadors d'un equip donat. 
S'ha de mostrar el nom de l'equip, el tipus de persona, el nom i el cognoms de l'entrenador o jugador concatenats i amb un espai al mig.*/

select entrenadors.*, jugadors.*, equips.nom, persones.tipus_persona, concat(persones.nom, persones.cognoms) 'Nombre_completo'
from persones
join entrenadors on persones.id = entrenadors.persones_id
join entrenar_equips on entrenadors.persones_id = entrenar_equips.entrenadors_id
join equips on entrenar_equips.equips_id = equips.id
join jugadors on persones.id = jugadors.persones_id;


/*Donat un nom de lliga i una temporada, comptar el nombre de jugadors per cada posició. 
Mostrar la posició i el nombre de jugadors. 
Ordenar per la posició en ordre alfabètic. 
Només s'han de mostrar els jugadors que estiguin d'alta.*/

select lligues.nom, lligues.temporada, persones.nom, posicions.posicio
from lligues
join jornades on lligues.id = jornades.lligues_id
join partits on jornades.id = partits.jornades.id
join partits_gols on partits.id = partits_gols.partits_id