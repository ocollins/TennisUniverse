package edu.matc.persistence;

import edu.matc.entity.PersonService;
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
    @Before
    public void setup() {
        dao = new PersonServiceDao();


    }

    @Test
    public void getAllPersonsServices() throws Exception {
        List<PersonService> personServicesList = dao.getAllServices();
        logger.info("Total services " + personServicesList.size());
        assertTrue(personServicesList.size() > 0);
    }

    @Test
    public void getServicesForPerson() throws Exception {
        List<PersonService> personServicesList =
                dao.getPersonService(101, Date.valueOf("2017-04-01"), Date.valueOf("2017-04-31"));
        logger.info("inside the test method " + personServicesList.size());

        for(PersonService personService : personServicesList) {
            logger.info(personService.getPersonServiceId() + " " + personService.getServiceDate());
        }
        //assertTrue(personServicesList.size() > 0);
    }


}