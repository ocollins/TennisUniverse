package edu.matc.persistence;

import edu.matc.util.LocalDateAttributeConverter;
import edu.matc.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.*;
import java.time.LocalDate;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 02/04/17.
 * @author O Collins
 */
public class PersonDaoTest {

    PersonDao dao;
    LocalDateAttributeConverter dateConverter;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {

        dao = new PersonDao();
    }

    @Test
    public void getAllPersons() throws Exception {
        List<Person> persons = dao.getAllPersons();
        logger.info("Total persons " + persons.size());
        assertTrue(persons.size() > 0);
    }

    @Test
    public void getPerson() throws Exception {
        Person aPerson = dao.getPerson(101);

        logger.info("Person with index 101 " + aPerson.getFirstName());
        assertEquals("First name is not Joe ", "JOE", aPerson.getFirstName());
    }

    @Test
    public void testGetPersonByLastName() throws Exception {
        List<Person> persons = dao.getPersonByLastName("COYNE");
        //Just testing stuff
        logger.info("Number of Coyne persons " + persons.size());
        for (Person aPerson : persons) {
            logger.info("First name " + aPerson.getFirstName());

        }
        assertEquals("First name is not JOE ", "JOE", persons.get(0).getFirstName());
    }

    @Test
    public void testAddPerson() throws Exception {
        dateConverter = new LocalDateAttributeConverter();
        LocalDate dob = LocalDate.parse("1989-12-12");
        dateConverter.convertToDatabaseColumn(dob);

        Person newPerson = new Person(888888888, "MARIA", "SHARAPOVA", dob, 02, "MSHARAP@GMAIL.COM",
                "3 MAIN ST.", "", "SUN PRAIRIE", "WI", "53590", "4.0");
        int id = dao.addPerson(newPerson);
        logger.info("New person ID " + id);
        logger.info("Max person ID now " + dao.getMaxPersonId());

        //Check if a new person with new id exists
        assertNotNull("Person was not inserted ", dao.getPerson(id));
        assertEquals("Inserted person's last name is not SHARAPOVA ", "SHARAPOVA", dao.getPerson(id).getLastName());
        assertEquals("Inserted person's last name is not MARIA ", "MARIA", dao.getPerson(id).getFirstName());

    }

    @Test
    public void deletePerson() throws Exception {
        //Delete the last person in the table
        int personToDelete = dao.getMaxPersonId();
        logger.info("Deleting person " + personToDelete);
        dao.deletePerson(personToDelete);

        assertNull("Person " + personToDelete + " is still there ", dao.getPerson(personToDelete));

    }

    @Test
    public void testUpdatePerson() throws Exception {
        dateConverter = new LocalDateAttributeConverter();
        LocalDate dob = LocalDate.parse("1969-08-30");
        dateConverter.convertToDatabaseColumn(dob);
        //Find the latest inserted person
        int personId = dao.getMaxPersonId();
        logger.info("UPdate person ID " + personId);

        //Create person information to update in the DB
        Person personToUpdate = new Person(personId, 888888888, "Maria", "Smith", dob, 02, "mshar@gmail.com",
                "3 Main st", "", "Sun Prairie", "WI", "53590", "4.0");
        //personToUpdate.setFirstName("Dawn");

        //Update person
        dao.updatePerson(personToUpdate);

        //Test the update
        assertEquals("Last Name was not Smith", "Smith", dao.getPerson(personId).getLastName());
        assertEquals("dob is not 1969-08-30", dob, dao.getPerson(personId).getBirthDt());

    }

}