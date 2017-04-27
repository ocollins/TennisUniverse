DROP TABLE TENNIS_UNIVERSE.USER;
DROP TABLE TENNIS_UNIVERSE.USER_ROLE;

create table TENNIS_UNIVERSE.USER (
  USER_ID         INT(02) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  PERSON_ID       INT (11) NOT NULL,
  USER_NAME       varchar(15) not null,
  USER_PASS       varchar(15) not null
)ENGINE=InnoDB;

create table TENNIS_UNIVERSE.USER_ROLE (
  USER_ROLE_ID INT (02) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_NAME         varchar(15) not null,
  ROLE_NAME         varchar(15) not null
)ENGINE=InnoDB;

CREATE USER 'tomcat'@'localhost' IDENTIFIED BY 'tomcat';

GRANT SELECT ON TENNIS_UNIVERSE.* TO 'tomcat'@'localhost';

insert into TENNIS_UNIVERSE.USER values (01, 111, 'admin', 'Admin1');
insert into TENNIS_UNIVERSE.USER values (02, 101, 'member', 'Member1');

insert into TENNIS_UNIVERSE.USER_ROLE values (01, 'admin', 'administrator');
insert into TENNIS_UNIVERSE.USER_ROLE values (02, 'member', 'member');