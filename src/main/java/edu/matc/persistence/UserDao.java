package edu.matc.persistence;

import edu.matc.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * The User dao.
 *
 * @author O Collins 4/22/2017
 */
public class UserDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Gets User.
     *
     * @param UserId the User id
     * @return the User
     */
    public User getUser(int UserId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        User User = null;

        try {
            User = (User) session.get(User.class, UserId);
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return User;
    }

    /**
     * add a user
     * @param user
     * @return the id of the inserted record
     */
    public int addUser(User user) throws HibernateException{
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(user);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.info("Error adding a new user " + ex);
        } finally {
            session.close();
        }

        return id;
    }


}