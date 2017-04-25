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

        //Get new user info
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        logger.info("user name from the form " + userName);
        logger.info("password from the form " + password);
        //Get userId that the user is registering for
        String userIdString = String.valueOf(session.getAttribute("newUserId"));
        int userId = Integer.parseInt(userIdString);

        boolean successInsertUser = insertUser(userName, password);
        boolean successInsertUserRole = insertUserRole(userName, userId);

        ServletContext context = getServletContext();
        Properties properties = (Properties) context.getAttribute("applicationProperties");
        String responseUrl = null;
        //String responceurl = "/fitness.jsp";
        if (successInsertUser && successInsertUserRole) {
            responseUrl = properties.getProperty("tempAdminOptions.name");
        } else {
            responseUrl = properties.getProperty("processingErrorJsp.name");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
        dispatcher.forward(request, response);

    }

    /**
     * Insert new user row
     *
     * @param userName the user name
     * @param password the password
     * @return the boolean
     */
    public boolean insertUser(String userName, String password) {
        User user = new User(userName, password);
        UserDao userDao = new UserDao();

        try {
            int result = userDao.addUser(user);
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
    public boolean insertUserRole(String userName, int userId) {
        PersonDao personDao = new PersonDao();
        UserRoleDao userRoleDao = new UserRoleDao();

        //Get user role name from Person table
        String roleName = personDao.getPerson(userId).getRoleName();
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