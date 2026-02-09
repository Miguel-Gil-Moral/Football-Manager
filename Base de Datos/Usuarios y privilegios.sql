use football_manager;

create user 'superadministrador'@'localhost' identified by 'superadmin'
password expire interval 30 day
password history 2;

create user 'adminEquips'@'localhost' identified by 'adminEquips'
password expire;

create user 'adminLligues'@'localhost';

create user 'periodistaSport'@'localhost';
create user 'periodistaAS'@'localhost';
create user 'periodistaMundo'@'localhost';