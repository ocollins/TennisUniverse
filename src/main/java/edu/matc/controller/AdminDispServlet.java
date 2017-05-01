package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.entity.AdminAction;
import edu.matc.persistence.AdminActionDao;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;

/**
 * The type Log in display servlet.
 */
@WebServlet(
        name = "adminDispServlet",
        urlPatterns = { "/adminDispServlet" }
)
public class AdminDispServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processActions(request, response);

    }

    /**
     * Handles HTTP post requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processActions(request, response);

    }

    /**
     * Process actions.
     *
     * @param request  the request
     * @param response the response
     */
    public void processActions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get session object
        HttpSession session = request.getSession(true);

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = null;

        String userName = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        logger.info("&&&&&&&&&&&&&&&&&&7 Inside the admin display ");

        for(int i=0;i<cookies.length;i++) {
            logger.info("all cookies " + cookies[i].getName() + " " + cookies[i].getValue());
            if (cookies[i].getName().equals("loginUserName")) {
                logger.info("Found user name cookie");
                userName = cookies[i].getValue();

            }

            if (cookies[i].getName().equals("loginPassword")) {
                logger.info("Found password cookie");
                password = cookies[i].getValue();
            }
        }

        //If could not get user name or password redirect to the error page
//        if (userName.isEmpty() || password.isEmpty()) {
//            logger.info("User name or password is missing");
//            url = properties.getProperty("processingErrorJsp.name");
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//            dispatcher.forward(request, response);
//
//        }

        logger.info("User name from login screen " + userName);
        logger.info("Password from login screen " + password);
        //session.invalidate();

        //Create a new session
        //session = request.getSession(true);

        //Create AdminActions instance
        AdminActionDao dao = new AdminActionDao();
        List<AdminAction> adminActionList = null;


        //Get a list of admin pages depending on if it is an admin or a member
        try {
            adminActionList = dao.getActionList(getActionType(session, userName, password));
            //Store list of admin actions it in the session
            session.setAttribute("adminActionsList", adminActionList);
        } catch (Exception ex) {
            //In case of an error redirect to error page
            logger.info("Error getting list of admin actions" + ex);
            url = properties.getProperty("processingErrorJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

        url = properties.getProperty("adminOptionsJsp.name");

        //String url = "/adminoptions.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Gets action type.
     *
     * @param session the session
     * @return the action type
     */
    public String getActionType(HttpSession session, String userName, String password) throws Exception{
        String personRole = null;

        UserDao dao = new UserDao();

        //Get person ID of the person that logged in
        //Get person role so the appropriate options are displayed
        PersonDao personDao = new PersonDao();
        try {
            int personId = dao.getPersonId(userName, password);
            personRole = personDao.getPerson(personId).getRoleName();
            String firstName = personDao.getPerson(personId).getFirstName();
            session.setAttribute("personFirstName", firstName);
        } catch (Exception ex) {
            logger.info("Problem getting person ID by user name and password");
        }

        return personRole;
    }
}