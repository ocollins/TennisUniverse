package edu.matc.controller;

import edu.matc.entity.Person;
import edu.matc.persistence.PersonDao;
import edu.matc.util.LocalDateAttributeConverter;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Add member info servlet
 *
 * @author Olena Collins
 */
@WebServlet(
        name = "addMemberActionServlet",
        urlPatterns = { "/addMemberActionServlet" }
)
public class AddMemberActionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    /**
     * The Dao.
     */
    PersonDao dao;
    /**
     * The New person.
     */
    Person newPerson;
    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Remove the old session
        HttpSession session = request.getSession(true);

        //logger.info("OVC displaying url " + url);

        session.removeAttribute("feedbackMessage");
        session.setAttribute("feedbackMessage", storeMemberInfo(request));

        //String url = "/add_member.jsp";
        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("addMemberJsp.name");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Store member info int.
     *
     * @param request the request
     * @return the int
     */
    public String storeMemberInfo(HttpServletRequest request) {
        dao = new PersonDao();
        int newPersonId = 0;
        String feedbackMessage = null;

        LocalDateAttributeConverter dateConverter = new LocalDateAttributeConverter();
        LocalDate dob = LocalDate.parse(request.getParameter("birth_date"));
        dateConverter.convertToDatabaseColumn(dob);

        newPerson = new Person(Integer.parseInt(request.getParameter("ssn")), request.getParameter("fname"),
                request.getParameter("lname"), dob, request.getParameter("role_name"), request.getParameter("email"),
                request.getParameter("address_line1"), request.getParameter("address_line2"),
                request.getParameter("city"), request.getParameter("state"),
                request.getParameter("zip"), request.getParameter("phone"));

        try {
            newPersonId = dao.addPerson(newPerson);
            feedbackMessage = "Person was added successfully";
            logger.info("In Add member servlet added a new person " + newPersonId);
        } catch (HibernateException he) {
            feedbackMessage = "Error adding a new person";
            logger.info("Hibernate Exception " + he);
        }
        return feedbackMessage;


    }
}