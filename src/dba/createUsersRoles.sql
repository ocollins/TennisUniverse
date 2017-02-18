DROP TABLE USER;
DROP TABLE USER_ROLE;

create table USER (
  USER_ID INT(03) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_NAME       varchar(15) not null,
  USER_PASS       varchar(15) not null
)ENGINE=InnoDB;

create table USER_ROLE (
  USER_ROLES_ID INT (03) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_NAME         varchar(15) not null,
  ROLE_NAME         varchar(15) not null
)ENGINE=InnoDB;

CREATE USER 'tomcat'@'localhost' IDENTIFIED BY 'tomcat';

GRANT SELECT ON TENNIS_UNIVERSE.* TO 'tomcat'@'localhost';

insert into USER values (01, 'admin', 'admin');
insert into USER values (02, 'member', 'member');

insert into USER_ROLE values (01, 'admin', 'administrator');
insert into USER_ROLE values (02, 'member', 'member');