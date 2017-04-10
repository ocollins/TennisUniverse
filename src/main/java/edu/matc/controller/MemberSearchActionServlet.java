package edu.matc.controller;

import edu.matc.entity.Person;
import edu.matc.persistence.PersonDao;
import edu.matc.util.LocalDateAttributeConverter;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Member search action selection servlet
 *
 * @author Olena Collins
 */
@WebServlet(
        name = "memberSearchActionServlet",
        urlPatterns = { "/memberSearchActionServlet" }
)
public class MemberSearchActionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    HttpSession session;
    /**
     * The Dao.
     */
    PersonDao dao;
    /**
     * The A person.
     */
    Person aPerson;
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

        //Create a session
        session = request.getSession(true);
        ArrayList<Person> memberList = new ArrayList<>();

        //Store person in the session container
        session.setAttribute("aPerson", getMemberInfo(request));

        String url = "/update_member.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Gets member info.
     *
     * @param request the request
     * @return the member info
     */
    public Person getMemberInfo(HttpServletRequest request) {
        dao = new PersonDao();
        int personId = Integer.parseInt(request.getParameter("searchID"));
        session.setAttribute("searchID", personId);

        try {
            aPerson = dao.getPerson(personId);
            logger.info("In search member servlet person " + personId + aPerson.getFirstName());
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return aPerson;


    }
}