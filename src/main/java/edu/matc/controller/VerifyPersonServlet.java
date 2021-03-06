package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.persistence.PersonDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Verify person is on the system before registering.
 */
@WebServlet(
        name = "verifyPersonServlet",
        urlPatterns = { "/verifyPersonServlet" }
)
public class VerifyPersonServlet extends HttpServlet {
    private final Logger logger= Logger.getLogger(String.valueOf(this.getClass()));
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
        int personId = 0;
        if (request.getParameter("account_id")!= null && !request.getParameter("account_id").isEmpty()) {
            personId = Integer.parseInt(request.getParameter("account_id"));

            logger.info("person id from the form " + personId);
        }

        //Remove the old attribute
        HttpSession session = request.getSession(true);
        session.removeAttribute("validPerson");
        session.removeAttribute("personId");
        //In case email function was done first, remove the message so it is not displayed
        session.removeAttribute("sendEmailMessage");

        //Create Person DAO instance
        PersonDao dao = new PersonDao();

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("loginJsp.name");
        String errorUrl = properties.getProperty("processingErrorJsp.name");

        //If found person, registration form will be displayed
        if (dao.getPerson(personId) != null) {
            session.setAttribute("validPerson", true);
            session.setAttribute("personId", personId);
            //If not a valid member ID, error message will be displayed
        } else {
            session.setAttribute("validPerson", false);
            //String url = "/login.jsp";

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}