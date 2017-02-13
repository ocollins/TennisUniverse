package edu.matc.persistence;

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
public class ServiceDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    /** Return a list of all services
     * @return All services
     */
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<Service>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            services = session.createCriteria(Service.class).list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return services;
    }


    /**
     * Gets service.
     *
     * @param serviceId the service id
     * @return the service
     */
    public Service getService(int serviceId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Service service = null;

        try {
            service = (Service) session.get(Service.class, serviceId);
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return service;
    }

    /**
     * Gets service by desc.
     *
     * @param serviceDesc the service desc
     * @return the service by desc
     */

    public Service getServiceByDesc(String serviceDesc) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = null;

        Service service = null;
        Transaction transaction = null;

        try {
            criteria = session.createCriteria(Service.class);
            criteria.add(Restrictions.eq("serviceDesc", serviceDesc));
            service = (Service) criteria.uniqueResult();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return service;
    }

    public int addService(Service service) {
        int id = 0;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            id = (int)session.save(service);
            transaction.commit();

        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * delete a service by id
     * @param id the service's id
     */
    public void deleteService(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Service service = (Service)session.get(Service.class, id);
            session.delete(service);
            transaction.commit();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Update the service
     * @param service
     */

    public void updateService(Service service) {
        // Create session
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(service);
            transaction.commit();

        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Gets max service id .
     *
     * @return the max service id
     */
    public int getMaxServiceId() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer maxServiceId = 0;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Service.class);
            criteria.setProjection(Projections.max("serviceId"));
            maxServiceId = (Integer)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException he){
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return maxServiceId;
    }



}