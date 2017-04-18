DROP TABLE TENNIS_UNIVERSE.SERVICE;

CREATE TABLE TENNIS_UNIVERSE.SERVICE (
SERVICE_ID INT(03) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
SERVICE_CODE VARCHAR (02) NOT NULL,
SERVICE_DESC VARCHAR (35) NOT NULL ,
SERVICE_CHARGE DECIMAL (9,2) NOT NULL

) ENGINE=InnoDB;


INSERT INTO SERVICE VALUES (20, '01', 'PRIVATE LESSON', 60.00);
INSERT INTO SERVICE VALUES (30, '01', 'SEMI-PRIVATE LESSON', 30.00);
INSERT INTO SERVICE VALUES (40, '01', 'CLINIC', 15.00);
INSERT INTO SERVICE VALUES (50, '02', 'RACQUET RE-STRINGING', 18.00);
INSERT INTO SERVICE VALUES (60, '02', 'HOURLY COURT FEE', 10.00);
