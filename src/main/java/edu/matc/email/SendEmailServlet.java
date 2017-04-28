package edu.matc.email;

//**

import edu.matc.persistence.PersonDao;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * The type Send email servlet.
 */
/* Member search action selection servlet
        *
        * @author Olena Collins
        */
@WebServlet(
        name = "sendEmailServlet",
        urlPatterns = { "/sendEmailServlet" }
)
public class SendEmailServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    HttpSession session;
    UserDao userDao;
    PersonDao personDao;

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

        userDao = new UserDao();
        personDao = new PersonDao();
        SendEmail sendEmail = new SendEmail();
        String personEmail = null;
        String userName = null;
        String password = null;
        int personId = 0;
        String sendEmailMessage = "Email was sent successfully";

        //Access application properties to get login jsp name
        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("loginJsp.name");

        HttpSession session = request.getSession(true);

        //Remove existing attributes
        session.removeAttribute("validPerson");
        session.removeAttribute("sendEmailMessage");

        //Get person id from the screen
        if (request.getParameter("person_id")!= null && !request.getParameter("person_id").isEmpty()) {
            personId = Integer.parseInt(request.getParameter("person_id"));
        }

        try {
            personEmail = getPersonEmail(personId);
        } catch (Exception ex) {
            session.setAttribute("validPerson", false);
            logger.info("Invalid person id in Send email process");
        }

        try {
            userName = getUserName(personId);
        } catch (Exception ex) {
            session.setAttribute("validPerson", false);
            logger.info("Invalid person id in Send email process");
        }

        try {
            password = getPassword(personId);
        } catch (Exception ex) {
            session.setAttribute("validPerson", false);
            logger.info("Invalid person id in Send email process");
        }

        try {
            sendEmail.sendEmail(userName, password, personEmail);
            //If email was sent successfully, display message on the screen
            session.setAttribute("sendEmailMessage", sendEmailMessage);
        } catch (MessagingException mex) {
            logger.info("Error sending email ");
            url = properties.getProperty("processingErrorJsp.name");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Gets person email.
     *
     * @param personId the person id
     * @return the person email
     * @throws Exception the exception
     */
    public String getPersonEmail(int personId) throws Exception{
        String personEmail = null;
        personEmail = personDao.getPerson(personId).getEmailAddr();
        return personEmail;

    }

    /**
     * Gets user name.
     *
     * @param personId the person id
     * @return the user name
     * @throws Exception the exception
     */
    public String getUserName(int personId) throws Exception{
        String userName = null;
        userName = userDao.getUserByPersonId(personId).getUserName();

        return userName;
    }

    /**
     * Gets password.
     *
     * @param personId the person id
     * @return the password
     * @throws Exception the exception
     */
    public String getPassword(int personId) throws Exception{
        String password = null;
        password = userDao.getUserByPersonId(personId).getUserPass();

        return password;
    }
}
