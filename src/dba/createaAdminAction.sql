DROP TABLE TENNIS_UNIVERSE.ADMIN_ACTION;

CREATE TABLE TENNIS_UNIVERSE.ADMIN_ACTION
(
  ADMIN_ACTION_ID INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ACTION_DESC VARCHAR(30),
  ACTION_SERVLET_NAME VARCHAR(30)
)ENGINE=InnoDB;

INSERT INTO ADMIN_ACTION VALUES (10, 'ADD A NEW MEMBER', '/add_member.jsp');
INSERT INTO ADMIN_ACTION VALUES (20, 'UPDATE MEMBER INFORMATION', '/update_member.jsp');
INSERT INTO ADMIN_ACTION VALUES (30, 'ADD MEMBER SERVICES', '/add_memeber_serv.jsp');
INSERT INTO ADMIN_ACTION VALUES (40, 'PRINT MONTHLY MEMBER STATEMENT', '/print_statement.jsp');
