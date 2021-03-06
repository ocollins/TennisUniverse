DROP TABLE TENNIS_UNIVERSE.ADMIN_ACTION;

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

