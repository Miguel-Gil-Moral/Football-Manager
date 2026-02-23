use football_manager;

create user 'superadministrador'@'localhost'
password expire interval 30 day
password history 2;

grant all privileges on *.* to 'superadministrador'@'localhost';

create role if not exists 'periodista';