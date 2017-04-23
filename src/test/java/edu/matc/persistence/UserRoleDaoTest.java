package edu.matc.persistence;

import edu.matc.entity.UserRole;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by student on 4/23/17.
 */
public class UserRoleDaoTest {
    private final Logger logger= Logger.getLogger(String.valueOf(this.getClass()));

    UserRoleDao dao;
    UserRole userRole;
    String userName;
    int userId;
    PersonDao personDao;

    @Before
    public void init() {
        dao = new UserRoleDao();
        userName = "serena";
        userId = 110;
        personDao = new PersonDao();
    }

    @Test
    public void addUserRoleTest() throws Exception {
        String roleName = personDao.getPerson(userId).getRoleName();
        logger.info("User role name from Person " + roleName);
        userRole = new UserRole(userName, roleName);

        int result = dao.addUserRole(userRole);
        logger.info("New ID from user role table " + result);

        assertEquals("Error loading a user role ", roleName, dao.getUserRole(result).getRoleName());

        //int result = dao.addUserRole(userName, userRole);

    }

}