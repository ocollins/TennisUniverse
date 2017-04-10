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

/**
 * Update member info action selection servlet
 *
 * @author Olena Collins
 */
@WebServlet(
        name = "updateMemberActionServlet",
        urlPatterns = { "/updateMemberActionServlet" }
)
public class UpdateMemberActionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    HttpSession session;
    /**
     * The Dao.
     */
    PersonDao dao;
    /**
     * The New person.
     */
    Person updatePerson;
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

        //Get session container
        session = request.getSession(true);

        //Remove old message
        session.removeAttribute("resultMessage");
        session.setAttribute("resultMessage", storeMemberInfo(request));

        String url = "/update_member.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Store member info int.
     * @param request the request
     * @return the int
     */
    public boolean storeMemberInfo(HttpServletRequest request) {
        dao = new PersonDao();
        boolean successUpdate = false;

        LocalDateAttributeConverter dateConverter = new LocalDateAttributeConverter();
        LocalDate dob = LocalDate.parse(request.getParameter("birth_date"));
        dateConverter.convertToDatabaseColumn(dob);
        int personId = Integer.parseInt((String)session.getAttribute("searchID"));

        updatePerson = new Person(personId, Integer.parseInt(request.getParameter("ssn")), request.getParameter("fname"),
                request.getParameter("lname"), dob, 1, request.getParameter("email"),
                request.getParameter("address_line1"), request.getParameter("address_line2"),
                request.getParameter("city"), request.getParameter("state"),
                request.getParameter("zip"), request.getParameter("phone"));

        try {
            dao.updatePerson(updatePerson);
            logger.info("In Add member servlet added a new person " + successUpdate);
            logger.info("updated first name " + request.getParameter("fname"));
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return successUpdate;


    }
}