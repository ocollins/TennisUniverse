package edu.matc.persistence;

import edu.matc.entity.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by student on 4/23/17.
 */
public class UserDaoTest {
    UserDao dao;
    int userId;
    String userName;
    String userPassword;
    User user;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void init() {
        dao = new UserDao();
        userId = 01;
        userName = "coco";
        userPassword = "coco";
        user = new User(userName, userPassword);
    }

    @Test
    public void getUserTest() throws Exception {
        logger.info("Searching for user 01 " + dao.getUser(userId).getUserName() + dao.getUser(userId).getUserPass());
        assertEquals("Invalid user name ", "admin", dao.getUser(userId).getUserName() );
    }

    @Test
    public void addUserTest() throws Exception{
        int result = dao.addUser(user);

        logger.info("Adding user with new id " + result);
        //assertEquals("Unsuccessfule adding a new user ", 3, result);

    }

}