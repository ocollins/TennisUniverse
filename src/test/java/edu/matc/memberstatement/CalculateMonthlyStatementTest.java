package edu.matc.memberstatement;

import edu.matc.entity.PersonService;
import edu.matc.entity.Service;
import edu.matc.persistence.PersonServiceDao;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class will test methods used to produce a monthly statement for a member
 *
 */
public class CalculateMonthlyStatementTest {
    private final Logger logger = Logger.getLogger(this.getClass());
    CalculateMonthlyStatement calculateMonthlyStatement;

    int id;
    Date startDate;
    Date endDate;

    @Before
    public void setup() {
        id = 101;
        startDate = Date.valueOf("2017-04-01");
        endDate = Date.valueOf("2017-04-30");
        calculateMonthlyStatement = new CalculateMonthlyStatement();
    }

    @Test
    public void listServicesTest() throws Exception {
        List<PersonService> personServicesList =
                calculateMonthlyStatement.listServices(id, startDate, endDate);
        logger.info("In test Calculate services Number of services for person 101 for April " + personServicesList.size());

        for(PersonService personService : personServicesList) {
            Service service = personService.getService();
            logger.info(service.getServiceId() + " " + personService.getServiceDate() +
                    " " + personService.getService().getServiceDesc() +
                    " " + personService.getService().getServiceCharge());
        }
        assertTrue(personServicesList.size() == 5);
        assertEquals("Incorrect service ", 20, personServicesList.get(0).getService().getServiceId() );
    }

    @Test
    public void calculateTotalDueTest () {
        double totalDue = calculateMonthlyStatement.calculateTotalDue(id, startDate, endDate);
        assertEquals("Incorrect total due ", 215.00, totalDue, 0);

    }

}