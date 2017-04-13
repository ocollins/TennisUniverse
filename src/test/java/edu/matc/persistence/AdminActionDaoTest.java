package edu.matc.persistence;

import edu.matc.entity.AdminAction;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setup() {
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
    public void getAdminAction() throws Exception {

    }

    @Test
    public void addAdminAction() throws Exception {

    }

    @Test
    public void deleteAdminAction() throws Exception {

    }

    @Test
    public void updateAdminAction() throws Exception {

    }

    @Test
    public void getMaxAdminActionId() throws Exception {

    }

}