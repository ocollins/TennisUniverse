package edu.matc.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.*;

/**
 * This file provides a SessionFactory for use with DAOS using Hibernate
 *
 * @author O Collins
 * @version 1.0 02/04/17.
 */
public class SessionFactoryProvider {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static SessionFactory sessionFactory;

    /**
     * Call method creating SessionFactory.
     */
    public static void createSessionFactory() {
    SessionFactoryProvider provider = new SessionFactoryProvider();

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            provider.logger.info("Initial SessionFactory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Create session factory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;

    }
}
