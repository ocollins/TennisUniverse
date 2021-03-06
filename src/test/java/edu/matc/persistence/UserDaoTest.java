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
    int personId;
    User user;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void init() {
        dao = new UserDao();
        userId = 01;
        personId = 101;
        userName = "coco";
        userPassword = "Cocosha1";
        user = new User(personId, userName, userPassword);
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
        assertTrue("Failed to add a new user ", result > 0);

    }

    @Test
    public void getUserByPersonIdTest() throws Exception {
        user = dao.getUserByPersonId(personId);
        logger.info("found a user by person id " + user.getUserName());
        assertEquals("Error finding a user by person id ", "member", user.getUserName() );
    }

    @Test
    public void getPersonIdTest() throws Exception {
        logger.info("Person id " + dao.getPersonId("admin", "Admin1"));
        assertEquals("Invalid person ID ", 111, dao.getPersonId("admin", "Admin1"));
    }

}