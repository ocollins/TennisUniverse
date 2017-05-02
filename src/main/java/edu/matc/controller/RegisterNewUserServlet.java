package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.entity.UserRole;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.UserDao;
import edu.matc.persistence.UserRoleDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;


/**
 * Servlet to cleanup form data from the screen
 * @author Olena Collins
 */
@WebServlet(
        name = "registerNewUserServlet",
        urlPatterns = { "/registerNewUserServlet" }
)
public class RegisterNewUserServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Create context container
        HttpSession session = request.getSession(true);

        ServletContext context = getServletContext();
        Properties properties = (Properties) context.getAttribute("applicationProperties");
        String responseUrl = null;
        String alreadyRegisteredMessage = "You already have user name and password";;
        boolean successInsertUser = false;
        boolean successInsertUserRole = false;
        boolean foundRegistration = false;


        //Get new user info
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");

        session.removeAttribute("alreadyRegisteredMessage");
        //In case email function was done first, remove the message so it is not displayed
        session.removeAttribute("sendEmailMessage");

        //Get personId that the user is registering for
        String personIdString = String.valueOf(session.getAttribute("personId"));
        int personId = Integer.parseInt(personIdString);
        logger.info("@@@@@@@@@@@@@@@@@@@inserting member id " + personId);

        //Check if the person had already registered and has user name and id
        //if so, send error message back
        try {
            foundRegistration = checkForExistingRegistration(personId);
        } catch (Exception ex) {
            responseUrl = properties.getProperty("processingErrorJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
            dispatcher.forward(request, response);
            logger.info("Error finding existing user");

        }

        if(foundRegistration) {
            session.setAttribute("alreadyRegisteredMessage", alreadyRegisteredMessage);
            responseUrl = properties.getProperty("loginJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
            dispatcher.forward(request, response);
        } else {
            try {
                successInsertUser = insertUser(personId, userName, password);
                successInsertUserRole = insertUserRole(userName, personId);
            } catch (Exception ex) {
                logger.info("Error inserting into USER or USER_ROLE table");
                responseUrl = properties.getProperty("processingErrorJsp.name");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
                dispatcher.forward(request, response);
            }

        }

        if (successInsertUser && successInsertUserRole) {
            responseUrl = properties.getProperty("adminDisplayServlet.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
            dispatcher.forward(request, response);
        } else {
            responseUrl = properties.getProperty("processingErrorJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
            dispatcher.forward(request, response);
        }


    }

    /**
     * Check for existing registration boolean.
     *
     * @param personId the person id
     * @return the boolean
     */
    public boolean checkForExistingRegistration(int personId) throws Exception{
        UserDao userDao = new UserDao();
        boolean foundRegistration = false;
        try {
            User user = userDao.getUserByPersonId(personId);
            if (user.getUserId() > 0) {
                foundRegistration = true;
            }
        } catch (Exception ex) {
            logger.info("Hibernate error finding user by person id");
        }
        return foundRegistration;
    }

    /**
     * Insert new user row
     *
     * @param userName the user name
     * @param password the password
     * @return the boolean
     */
    public boolean insertUser(int personId, String userName, String password) throws Exception{
        User user = new User(personId, userName, password);
        UserDao userDao = new UserDao();

        try {
            int result = userDao.addUser(user);
            logger.info("new user id " + result);
            return true;
        } catch (Exception ex) {
            logger.info("Error adding new user into the USER table " + ex);
            return false;
        }
    }

    /**
     * Insert user role boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public boolean insertUserRole(String userName, int personId) throws Exception{
        PersonDao personDao = new PersonDao();
        UserRoleDao userRoleDao = new UserRoleDao();

        //Get user role name from Person table
        String roleName = personDao.getPerson(personId).getRoleName();
        logger.info("User role name from Person " + roleName);

        //Create new userRole object
        UserRole userRole = new UserRole(userName, roleName);

        //Insert new User Role into the thable
        try {
            int result = userRoleDao.addUserRole(userRole);
            return true;
        } catch (Exception ex) {
            logger.info("Failed to insert a row into USER_ROLE table " + ex);
            return false;
        }


    }


}