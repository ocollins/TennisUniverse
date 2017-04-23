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

}