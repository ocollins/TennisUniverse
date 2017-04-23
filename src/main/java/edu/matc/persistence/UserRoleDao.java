package edu.matc.persistence;

import edu.matc.entity.User;
import edu.matc.entity.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The User Role dao.
 *
 * @author O Collins 4/22/2017
 */
public class UserRoleDao {

    private final Logger logger = Logger.getLogger(this.getClass());
    /**
     * add a userRole role
     * @param userRole role
     * @return the id of the inserted record
     */
    public int addUserRole(UserRole userRole) throws HibernateException{
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(userRole);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.info("Error adding a new user role " + ex);
        } finally {
            session.close();
        }

        return id;
    }



}