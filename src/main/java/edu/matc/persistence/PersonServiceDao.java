package edu.matc.persistence;

import edu.matc.entity.PersonService;
import edu.matc.entity.Service;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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


}