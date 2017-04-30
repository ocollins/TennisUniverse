DROP TABLE IF EXISTS TENNIS_UNIVERSE.ADMIN_ACTION;
DROP TABLE IF EXISTS TENNIS_UNIVERSE.PERSON_SERVICE;
DROP TABLE IF EXISTS TENNIS_UNIVERSE.PERSON;
DROP TABLE IF EXISTS TENNIS_UNIVERSE.SERVICE;
DROP TABLE IF EXISTS TENNIS_UNIVERSE.USER;
DROP TABLE IF EXISTS TENNIS_UNIVERSE.USER_ROLE;

CREATE TABLE TENNIS_UNIVERSE.ADMIN_ACTION
(
  ADMIN_ACTION_ID INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ACTION_DESC VARCHAR(30),
  ACTION_JSP_NAME VARCHAR(30),
  ACTION_TYPE VARCHAR(20),
  SEARCH_MEMBER_SW VARCHAR(01)

)ENGINE=InnoDB;

INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (10, 'ADD A NEW MEMBER', '/add_member.jsp', 'administrator', 'N');
INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (20, 'UPDATE MEMBER INFORMATION', '/update_member.jsp', 'administrator', 'Y');
INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (30, 'DELETE MEMBER', '/delete_member.jsp', 'administrator', 'Y');
INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (40, 'ADD MEMBER SERVICES', '/add_member_serv.jsp', 'administrator', 'Y');
INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (50, 'PRINT MONTHLY MEMBER STATEMENT', '/print_statement.jsp', 'administrator', 'Y');
INSERT INTO TENNIS_UNIVERSE.ADMIN_ACTION VALUES (60, 'BOOK SERVICES', '/book_service.jsp', 'member', 'N');


CREATE TABLE TENNIS_UNIVERSE.SERVICE (
  SERVICE_ID INT(03) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  SERVICE_CODE VARCHAR (02) NOT NULL,
  SERVICE_DESC VARCHAR (35) NOT NULL ,
  SERVICE_CHARGE DECIMAL (9,2) NOT NULL

) ENGINE=InnoDB;


INSERT INTO TENNIS_UNIVERSE.SERVICE VALUES (20, '01', 'PRIVATE LESSON', 60.00);
INSERT INTO TENNIS_UNIVERSE.SERVICE VALUES (30, '01', 'SEMI-PRIVATE LESSON', 30.00);
INSERT INTO TENNIS_UNIVERSE.SERVICE VALUES (40, '01', 'CLINIC', 15.00);
INSERT INTO TENNIS_UNIVERSE.SERVICE VALUES (50, '02', 'RACQUET RE-STRINGING', 18.00);
INSERT INTO TENNIS_UNIVERSE.SERVICE VALUES (60, '02', 'HOURLY COURT FEE', 10.00);

reate table TENNIS_UNIVERSE.USER (
USER_ID         INT(02) NOT NULL PRIMARY KEY AUTO_INCREMENT,
PERSON_ID       INT (11) NOT NULL UNIQUE,
USER_NAME       varchar(15) NOT NULL UNIQUE,
USER_PASS       varchar(15) NOT NULL UNIQUE
)ENGINE=InnoDB;

create table TENNIS_UNIVERSE.USER_ROLE (
  USER_ROLE_ID INT (02) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_NAME         varchar(15) NOT NULL UNIQUE,
  ROLE_NAME         varchar(15) not null
)ENGINE=InnoDB;

DROP USER 'tomcat'@'localhost';

CREATE USER 'tomcat'@'localhost' IDENTIFIED BY 'tomcat';

GRANT SELECT ON TENNIS_UNIVERSE.* TO 'tomcat'@'localhost';

insert into TENNIS_UNIVERSE.USER values (01, 111, 'admin', 'Admin1');
insert into TENNIS_UNIVERSE.USER values (02, 101, 'member', 'Member1');

insert into TENNIS_UNIVERSE.USER_ROLE values (01, 'admin', 'administrator');
insert into TENNIS_UNIVERSE.USER_ROLE values (02, 'member', 'member');

create table IF NOT EXISTS TENNIS_UNIVERSE.PERSON (
  PERSON_ID       int(11) NOT NULL auto_increment PRIMARY KEY ,
  SSN_NR          INT(09),
  FIRST_NAME      varchar(35) NOT NULL,
  LAST_NAME       varchar(35) NOT NULL ,
  BIRTH_DT        DATE,
  ROLE_NAME       VARCHAR(20) NOT NULL,
  EMAIL_ADDR      varchar(45),
  ADDRESS_LINE1   varchar(45),
  ADDRESS_LINE2   varchar(45),
  CITY            varchar(45),
  STATE           varchar(02),
  ZIP             varchar(05),
  PHONE           VARCHAR(10),
  LAST_UPDATED    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

) ENGINE=InnoDB;

INSERT INTO PERSON VALUES (101, 999999999, "JOE", "COYNE", "1969-03-04", "member", "OLENACOLLINS1333@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6089997755", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (102, 999999999, "LENA", "COYNE", "1973-03-04", "member", "LCOYNE@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083454400", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (103, 999999999, "KRISTI", "COYNE", "1993-03-04", "member", "KCOYNE@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083456868", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (104, 999999999, "SAMANTHA", "SMITH", "1945-03-04", "member", "SSMITH@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6089997755", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (105, 999999999, "SAM", "FROLEY", "1999-03-04", "member", "SFROLEY@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083454400", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (106, 999999999, "DAVE", "DAVIDSON", "1993-03-04", "member", "DAVIDSON@GMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083456868", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (107, 999999999, "SAMIR", "BENOJEU", "1977-12-13", "admin", "SDENOJEY@HOTMAIL.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083456868", CURRENT_TIMESTAMP);
INSERT INTO PERSON VALUES (108, 999999999, "DEB", "JOHNSON", "1945-03-04", "admin", "DJOHNSON@TDS.COM", "1 MAIN ST","" , "MADISON", "WI", "53555", "6083456868", CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS TENNIS_UNIVERSE.PERSON_SERVICE
(
  PERSON_SERVICE_ID INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PERSON_ID INT (11) NOT NULL ,
  FOREIGN KEY (PERSON_ID)
  REFERENCES PERSON(PERSON_ID)
    ON DELETE CASCADE,
  SERVICE_ID INT(3) NOT NULL ,
  FOREIGN KEY (SERVICE_ID)
  REFERENCES SERVICE(SERVICE_ID)
    ON DELETE CASCADE,
  SERVICE_DATE DATE NOT NULL ,
  NOTES VARCHAR(100)
)ENGINE=InnoDB;

INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(109, 101, 60, '2017-02-10', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(110, 101, 60, '2017-03-18', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(111, 101, 60, '2017-01-23', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(112, 101, 20, '2017-04-02', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(101, 101, 30, '2017-04-12', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(102, 101, 40, '2017-04-16', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(103, 101, 20, '2017-04-17', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(104, 101, 60, '2017-04-17', "");

INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(105, 102, 30, '2017-04-12', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(106, 102, 40, '2017-04-16', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(107, 102, 20, '2017-04-17', "");
INSERT INTO TENNIS_UNIVERSE.PERSON_SERVICE VALUES(108, 102, 60, '2017-04-17', "");




