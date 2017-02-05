package edu.matc.persistence;

import edu.matc.entity.Person;
//import edu.matc.util.LocalDateAttributeConverter;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

import java.util.List;

import static org.junit.Assert.*;
import org.apache.log4j.*;


/**
 * Created on 02/04/17.
 * @author O Collins
 */
public class PersonDaoTest {

    PersonDao dao;
    //LocalDateAttributeConverter dateConverter;
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
        Person aPerson = dao.getPerson(1);
        logger.info("Person with index 1 " + aPerson.getFirstName());
        assertEquals("First name is not Joe ", "Joe", aPerson.getFirstName());
    }

    @Test
    public void testGetPersonByLastName() throws Exception {
        List<Person> persons = dao.getPersonByLastName("Curry");
        //Just testing stuff
        logger.info("Number of Curry persons " + persons.size());
        for (Person aPerson : persons) {
            logger.info("First name " + aPerson.getFirstName());

        }
        assertEquals("First name is not Barney ", "Barney", persons.get(0).getFirstName());
    }

//    @Test
//    public void testAddPerson() throws Exception {
//        dateConverter = new LocalDateAttributeConverter();
//        LocalDate dob = LocalDate.parse("1989-12-12");
//        dateConverter.convertToDatabaseColumn(dob);
//
//        Person newPerson = new Person("Maria", "Sharapova", dob);
//        int id = dao.addPerson(newPerson);
//        logger.info("New person ID " + id);
//
//        //Check if a new person with new id exists
//        assertNotNull("Person was not inserted ", dao.getPerson(id) );
//    }

    @Test
    public void deletePerson() throws Exception {
        dao.deletePerson(7);

        assertNull("Person 7 is still there ", dao.getPerson(7));

    }

//    @Test
//    public void testUpdatePerson() throws Exception {
//        dateConverter = new LocalDateAttributeConverter();
//        LocalDate dob = LocalDate.parse("1969-08-30");
//        dateConverter.convertToDatabaseColumn(dob);
//
//        //Create person information to update in the DB
//        Person personToUpdate = new Person();
//        personToUpdate.setPersonid(6);
//        personToUpdate.setFirstName("Dawn");
//        personToUpdate.setLastName("Smith");
//        personToUpdate.setDateOfBirth(dob);
//
//        //Update person
//        dao.updatePerson(personToUpdate);
//
//        //Test the update
//        assertEquals("Last Name was not Smith", "Smith", dao.getPerson(6).getLastName());
//
//    }

}