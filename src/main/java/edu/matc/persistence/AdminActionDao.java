package edu.matc.persistence;

import edu.matc.entity.AdminAction;
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
 * Manipulates Admin Actions.
 * @author O Collins 2/25/2017
 */
public class AdminActionDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    /** Return a list of all admin actions
     * @return All admin actions
     */
    public List<AdminAction> getAllAdminActions() {
        List<AdminAction> adminActions = new ArrayList<AdminAction>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            adminActions = session.createCriteria(AdminAction.class).list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return adminActions;
    }


    /**
     * Gets admin actions.
     *
     * @param adminActionId the adminAction id
     * @return the adminAction
     */
    public AdminAction getAdminAction(int adminActionId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        AdminAction adminAction = null;

        try {
            adminAction = (AdminAction) session.get(AdminAction.class, adminActionId);
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return adminAction;
    }

    /**
     * Gets adminAction by desc.
     *
     * @param adminActionDesc the adminAction desc
     * @return the adminAction by desc
     */

    public AdminAction getAdminActionByJSPName(String adminActionDesc) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = null;

        AdminAction adminAction = null;
        Transaction transaction = null;

        try {
            criteria = session.createCriteria(AdminAction.class);
            criteria.add(Restrictions.eq("adminActionDesc", adminActionDesc));
            adminAction = (AdminAction) criteria.uniqueResult();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return adminAction;
    }

    /**
     * Gets action list.
     * @param actionType the action type
     * @return the action list
     */
    public List<AdminAction> getActionList(String actionType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = null;

        List <AdminAction> actionList = null;

        try {
            criteria = session.createCriteria(AdminAction.class);
            criteria.add(Restrictions.eq("searchMemberSw", actionType));
            actionList = criteria.list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return actionList;
    }


    /**
     * delete a adminAction by id
     * @param id the adminAction's id
     */
    public void deleteAdminAction(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            AdminAction adminAction = (AdminAction)session.get(AdminAction.class, id);
            session.delete(adminAction);
            transaction.commit();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Update the adminAction
     * @param adminAction
     */

    public void updateAdminAction(AdminAction adminAction) {
        // Create session
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(adminAction);
            transaction.commit();

        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
    }

    /**
     * Gets max adminAction id .
     *
     * @return the max adminAction id
     */
    public int getMaxAdminActionId() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer maxAdminActionId = 0;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(AdminAction.class);
            criteria.setProjection(Projections.max("adminActionId"));
            maxAdminActionId = (Integer)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException he){
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return maxAdminActionId;
    }



}