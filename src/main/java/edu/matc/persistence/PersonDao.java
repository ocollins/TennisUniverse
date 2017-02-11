package edu.matc.persistence;

import edu.matc.entity.Person;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

/**
 * @author O Collins 2/4/2017
 */
public class PersonDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    /** Return a list of all persons
     *
     * @return All persons
     */
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            persons = session.createCriteria(Person.class).list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return persons;
    }

    /**
     * retrieve a person given their id
     *
     * @param id the person's id
     * @return person
     */
    public Person getPerson(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Person person = null;

        try {
            person = (Person) session.get(Person.class, id);
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return person;
    }

    /**
     * Gets person by last name.
     *
     * @param lastName the last name
     * @return the person by last name
     */
    public List<Person> getPersonByLastName(String lastName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = null;
        List<Person> myPerson = null;

        try {
            criteria = session.createCriteria(Person.class);
            criteria.add(Restrictions.eq("lastName", lastName));
            myPerson = criteria.list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return myPerson;
    }

    /**
     * add a person
     *
     * @param person
     * @return the id of the inserted record
     */
    public int addPerson(Person person) {
        int id = 0;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            id = (int)session.save(person);
            transaction.commit();

        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * delete a person by id
     * @param id the person's id
     */
    public void deletePerson(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Person person = (Person)session.get(Person.class, id);
            session.delete(person);
            transaction.commit();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Update the person
     * @param person
     */

    public void updatePerson(Person person) {
        // Create session
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            //Get data from the table for the person by ID
            logger.info("Inside dao " + person.getPersonId());

            session.saveOrUpdate(person);
            transaction.commit();

        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Gets max person id .
     *
     * @return the max person id
     */
    public int getMaxPersonId() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer maxPersonId = 0;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            criteria.setProjection(Projections.max("personId"));
            maxPersonId = (Integer)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException he){
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return maxPersonId;
    }



}