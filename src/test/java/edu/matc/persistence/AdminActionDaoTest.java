package edu.matc.persistence;

import edu.matc.entity.AdminAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.log4j.*;

import static org.junit.Assert.*;

/**
 * Test class for AdminAction Entity
 * @author O Collins
 */
public class AdminActionDaoTest {

    AdminActionDao dao;
    private final Logger logger= Logger.getLogger(String.valueOf(this.getClass()));
    String memberActionType;
    String adminActionType;

    @Before
    public void setup() {
        memberActionType = "M";
        adminActionType = "A";
        dao = new AdminActionDao();
    }

    @Test
    public void getAllAdminActions() throws Exception {
        List<AdminAction> adminActions = dao.getAllAdminActions();
        for (AdminAction adminAction : adminActions) {
            logger.info("Action desc: " + adminAction.getActionDesc() + " " + adminAction.getActionJSPName());
        }

        assertTrue("No Admin Actions returned ", adminActions.size() > 0);

    }

    @Test
    public void getActionListTest() throws Exception {
        List<AdminAction> actionList = dao.getActionList(memberActionType);
        assertEquals("Invalid number of member actions ", 1, actionList.size());
        for (AdminAction action : actionList) {
            logger.info("Member action " + action.getActionDesc());
        }

        assertEquals("Invalid member action", "BOOK SERVICES", actionList.get(0).getActionDesc());

        actionList = dao.getActionList(adminActionType);
        assertEquals("Invalid number of admin actions ", 5, actionList.size());
        for (AdminAction action : actionList) {
            logger.info("Member action " + action.getActionDesc());
        }
    }

}