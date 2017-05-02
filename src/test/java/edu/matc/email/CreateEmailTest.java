package edu.matc.email;

import edu.matc.pdfbox.CreateMemberStatement;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

/**
 * Class will test methods used to produce a monthly statement for a member
 *
 */
public class CreateEmailTest {
    private Logger logger = Logger.getLogger(this.getClass());

    String emailAddress;
    String userName;
    String password;
    UserDao dao;
    SendEmail sendEmail;

    @Before
    public void startup() {
        dao = new UserDao();
        sendEmail = new SendEmail();
        emailAddress="olenacollins1333@gmail.com";
        userName = "Coco";
        password = "Buddy1";
    }


    @Test
    public void createEmailTest() throws Exception {
        sendEmail.sendEmail(userName, password, emailAddress);

    }


}