package edu.matc.controller;

import edu.matc.entity.Person;
import edu.matc.entity.PersonService;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.PersonServiceDao;
import edu.matc.persistence.ServiceDao;
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
import java.util.Set;

/**
 * Add member service action servlet
 * Driver to add services for members
 * !! Still under construction
 * @author Olena Collins
 */
@WebServlet(
        name = "addMemberServActionServlet",
        urlPatterns = { "/addMemberServActionServlet" }
)
public class AddMemberServActionServlet extends HttpServlet {
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
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        //Add member service jsp path
        String url = properties.getProperty("addMemberServiceJsp.name");

        PersonServiceDao dao = new PersonServiceDao();
        Person person = (Person)session.getAttribute("aPerson");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Store member service string.
     *
     * @param request the request
     * @param person  the person
     * @param dao     the dao
     * @return the string
     */
    public String storeMemberService(HttpServletRequest request, Person person, PersonServiceDao dao) {
        int newPersonServiceId = 0;
        String feedbackMessage = null;
        PersonService personService = null;

        LocalDateAttributeConverter dateConverter = new LocalDateAttributeConverter();
        LocalDate serviceDate = LocalDate.parse(request.getParameter("service_date"));
        dateConverter.convertToDatabaseColumn(serviceDate);

        try {
            newPersonServiceId = dao.addPersonService(personService);
            feedbackMessage = "Person was added successfully";
            logger.info("In Add member servlet added a new person " + newPersonServiceId);
        } catch (HibernateException he) {
            feedbackMessage = "Error adding a new person";
            logger.info("Hibernate Exception " + he);
        }
        return feedbackMessage;


    }
}