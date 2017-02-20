CREATE TABLE TENNIS_UNIVERSE.PERSON_SERVICE
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
