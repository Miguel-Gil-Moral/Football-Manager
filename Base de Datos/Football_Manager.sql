drop database football_manager;
create database football_manager;

use football_manager;

create table posicions (
	id int auto_increment,
    posicio varchar(45) not null,
    constraint pk_posicions primary key (id)
);

create table persones (
	id int auto_increment,
    nom varchar(45) not null,
    data_naixament date not null,
    nivell_motivacio int not null,
    sou float not null,
    tipus_persona varchar(45) not null,
    constraint pk_persones primary key (id)
);

create table entrenadors (
	persones_id int not null,
    num_tornejos int not null,
    es_seleccionador tinyint not null,
    constraint pk_entrenadors primary key (persones_id),
    constraint fk_persones_entrenadors foreign key (persones_id) references persones(id)
);

create table estadis (
	id int auto_increment,
    nom varchar(45) not null,
    num_espectadors int not null,
    constraint pk_estadis primary key (id)
);

create table ciutats (
	id int auto_increment,
    nom varchar(45) not null,
    constraint pk_ciutats primary key (id)
);

create table equips (
	id int auto_increment,
    nom varchar(45) not null,
    any_fundacio int not null,
    nom_president varchar(45),
    ciutats_id int not null,
    estadis_id int not null,
    filial_equips_id int,
    constraint pk_equips primary key (id),
    constraint fk_ciutats_equips foreign key (ciutats_id) references ciutats(id),
    constraint fk_estadis_equips foreign key (estadis_id) references estadis(id),
    constraint fk_equips_equips foreign key (filial_equips_id) references equips (id)
);

create table entrenar_equips (
	data_fitxatge date not null,
    entrenadors_id int not null,
    equips_id int not null,
    data_baixa date,
    constraint pk_entrenar_equips primary key (data_fitxatge, entrenadors_id),
    constraint fk_entrenadors foreign key (entrenadors_id) references entrenadors(persones_id),
    constraint fk_equips_entrenar_equips foreign key (equips_id) references equips(id)
);

create table lligues (
	id int auto_increment,
    nom varchar(45) not null,
    temporada year not null,
    constraint pk_lligues primary key (id)
);

create table participar_lligues (
	equips_id int not null,
    lligues_id int not null,
    constraint pk_participar_lligues primary key (equips_id, lligues_id),
    constraint fk_equips_participar_lligues foreign key (equips_id) references equips(id),
    constraint fk_lligues_participar_lligues foreign key (lligues_id) references lligues(id)
);

create table jornades (
	id int auto_increment,
    jornada int not null,
    data date not null,
    lligues_id int not null,
    constraint pk_jornades primary key (id),
    constraint fk_lligues_jornades foreign key (lligues_id) references lligues(id)
);

create table jugadors (
	persones_id int not null,
    dorsal int not null,
    qualitat int not null,
    posicions_id int not null,
    constraint pk_jugadors primary key (persones_id),
    constraint fk_persones_jugadors foreign key (persones_id) references persones(id),
    constraint fk_posicions_jugadors foreign key (posicions_id) references posicions(id)
);

create table jugadors_equips (
	data_fitxatge date,
    jugadors_id int not null,
    equips_id int not null,
    data_baixa date,
    constraint pk_jugadors_equips primary key (data_fitxatge, jugadors_id),
    constraint fk_jugadors_jugadors_equips foreign key (jugadors_id) references jugadors(persones_id),
    constraint fk_equips_jugadors_equips foreign key (equips_id) references equips(id)
);

create table partits (
	id int auto_increment,
    gols_local int not null,
    gols_visitant int not null,
    punts_local int not null,
    punts_visitant int not null,
    jornades_id int not null,
    equips_id_local int not null,
    equips_id_visitant int not null,
    constraint pk_partits primary key (id),
    constraint fk_jornades_partits foreign key (jornades_id) references jornades(id),
    constraint fk_equips_local_partits foreign key (equips_id_local) references equips(id),
    constraint fk_equips_visitant_partits foreign key (equips_id_visitant) references equips(id)
);

create table partits_gols (
	partits_id int not null,
    jugadors_id int not null,
    minut int auto_increment,
    es_penal tinyint not null,
    constraint pk_partits_gols primary key (minut, partits_id, jugadors_id),
    constraint fk_partits_partits_gols foreign key (partits_id) references partits(id),
    constraint fk_jugadors_partits_gols foreign key (jugadors_id) references jugadors(persones_id)
);