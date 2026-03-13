use football_manager;

/*1- Usuari superadministrador. 
Aquest usuari ha de poder realitzar operacions de lectura, inserció, modificació i esborrat sobre tota la base de dades. 
Cal que la seva contrasenya es renovi cada mes i no pot fer servir les dues darreres contrasenyes.*/
create user 'superadministrador'@'localhost'
password expire interval 30 day
password history 2;

grant all privileges on *.* to 'superadministrador'@'localhost';

/*2- Usuari adminEquips. 
Aquest usuari he de poder consultar i modificar les dades dels equips, els seus estadis i les persones que en formen part. 
No ha de poder eliminar ni afegir-ne de nous. 
Cal forçar que l'usuari hagi de modificar la contrasenya la primera vegada que entri al sistema.*/

/*3- Usuari adminLligues. 
Aquest usuari s'encarregarà de la gestió completa (afegir, modificar i consultar) de la part de les lligues, que inclou les jornades, els partits i els gols. 
Per criteris de seguretat, només podrà realitzar dues modificacions a la base de dades cada hora. 
D'aquesta manera, si les credencials quedessin compromoses, evitem ensurts majors.*/

/*Rol periodista. 
L'accés a la informació de la base de dades es monetitza en part donant accés als periodistes esportius. 
Aquests tenen accés de consulta d'informació a tota la base de dades.*/

create role if not exists 'periodista';

/*4- Crea l'usuari periodistaSport, periodistaAS i periodistaMundo.*/

/*5- Els alts càrrecs del diari AS han aconseguit que els seus periodistes disposin d'accés preferent a les dades, que se'ls serviran processades per a un millor anàlisi. 
Necessiteu crear almenys 4 vistes que mostrin informació ja filtrada i processada (podeu inspirar-vos en algunes de les consultes que us demanem al repte). 
Per exemple: un informe dels millors llançadors de penaltis. Només l'usuari periodistaAS tindrà accés a aquests informes.*/