package edu.matc.persistence;

import edu.matc.entity.PersonService;
import edu.matc.entity.Service;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Service dao.
 *
 * @author O Collins 2/12/2017
 */
public class PersonServiceDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    /** Return a list of all services per all members
     * @return All services
     */
    public List<PersonService> getAllServices() {
        List<PersonService> services = new ArrayList<PersonService>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            services = session.createCriteria(PersonService.class).list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return services;
    }

    /**
     * retrieve all services for the person for current month
     *
     * @param id the person's id
     * @param startDate Start date for the monthly statement
     * @param endDate End date for the monthly statement
     * @return person
     */
    public List<PersonService> getPersonService(int id, Date startDate, Date endDate) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List personServiceList = null;

        Criteria criteria = session.createCriteria(PersonService.class);
        criteria.add(Restrictions.eq("personId", id));
        criteria.add(Restrictions.ge("serviceDate", startDate));
        criteria.add(Restrictions.le("serviceDate", endDate));
        criteria.addOrder(Order.asc("serviceDate"));

        try {
            personServiceList = criteria.list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return personServiceList;
    }

    /**
     * Add person int.
     *
     * @param personService the person service
     * @return the int
     * @throws HibernateException the hibernate exception
     */
    public int addPersonService(PersonService personService) throws HibernateException{
        int id = 0;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(personService);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.info("Hibernate error adding a new person " + ex);
        } finally {

            session.close();
        }

        return id;
    }


}