package edu.matc.persistence;

import edu.matc.entity.PersonService;
import edu.matc.entity.Service;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 4/14/17.
 */
public class PersonServiceDaoTest {
    PersonServiceDao dao;
    private final Logger logger = Logger.getLogger(this.getClass());
    int id;
    Date startDate;
    Date endDate;

    @Before
    public void setup() {
        dao = new PersonServiceDao();
        id = 101;
        startDate = Date.valueOf("2017-04-01");
        endDate = Date.valueOf("2017-04-30");
    }

    @Test
    public void getAllPersonsServicesTest() throws Exception {
        List<PersonService> personServicesList = dao.getAllServices();
        logger.info("Total services " + personServicesList.size());
        logger.info("Service ID for the first person " + personServicesList.get(0).getService().getServiceDesc());
        assertTrue(personServicesList.size() > 0);

    }

    @Test
    public void getServicesForPersonTest() throws Exception {
        List<PersonService> personServicesList =
                dao.getPersonService(id, startDate, endDate);
        logger.info("Number of services for person 101 for April " + personServicesList.size());

        for(PersonService personService : personServicesList) {
            Service service = personService.getService();
            logger.info(service.getServiceId() + " " + personService.getServiceDate() +
                        " " + personService.getService().getServiceDesc() +
                        " " + personService.getService().getServiceCharge());
        }
        assertTrue(personServicesList.size() == 5);

    }


}