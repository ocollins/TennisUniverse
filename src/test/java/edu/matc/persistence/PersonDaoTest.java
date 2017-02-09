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

        Person newPerson = new Person(888888888, "Maria", "Sharapova", dob, 02, "mshar@gmail.com",
                "3 Main st", "", "Sun Prairie", "WI", "53590", "4.0");
        int id = dao.addPerson(newPerson);
        logger.info("New person ID " + id);

        //Check if a new person with new id exists
        assertNotNull("Person was not inserted ", dao.getPerson(id) );
        //add assert get first name
        //add assert get last name
    }

//    @Test
//    public void deletePerson() throws Exception {
//        dao.deletePerson(107);
//
//        assertNull("Person 102 is still there ", dao.getPerson(102));
//
//    }

//    @Test
//    public void testUpdatePerson() throws Exception {
//        dateConverter = new LocalDateAttributeConverter();
//        LocalDate dob = LocalDate.parse("1969-08-30");
//        dateConverter.convertToDatabaseColumn(dob);
//
//        //Create person information to update in the DB
//        //Person personToUpdate = new Person();
//        Person personToUpdate = new Person(104, 888888888, "Maria", "Smith", dob, 02, "mshar@gmail.com",
//                "3 Main st", "", "Sun Prairie", "WI", "53590", "4.0");
////        personToUpdate.setPersonid(6);
////        personToUpdate.setFirstName("Dawn");
////        personToUpdate.setLastName("Smith");
////        personToUpdate.setDateOfBirth(dob);
//
//        //Update person
//        dao.updatePerson(personToUpdate);
//
//        //Test the update
//        assertEquals("Last Name was not Smith", "Smith", dao.getPerson(104).getLastName());
//
//    }

}