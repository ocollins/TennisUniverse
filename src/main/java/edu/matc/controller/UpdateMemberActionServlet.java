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
    PersonDao dao;
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
        String message = null;

        //Remove old message
        session.removeAttribute("resultMessage");

        //Try to update member information and send result on the screen
        boolean updateSuccess = updateMemberInfo(request);
        if (updateSuccess) {
            message = "Member information was updated successfully";
        } else {
            message = "There was an error updating member information. Please contact Help Desk";
        }
        session.setAttribute("resultMessage", message);
        session.removeAttribute("aPerson");

        //String url = "/update_member.jsp";
        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("updateMemberJsp.name");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Store updated member info.
     * @param request the request
     * @return the boolean
     */
    public boolean updateMemberInfo(HttpServletRequest request) {
        dao = new PersonDao();
        boolean updateSuccess = false;

        LocalDateAttributeConverter dateConverter = new LocalDateAttributeConverter();
        LocalDate dob = LocalDate.parse(request.getParameter("birth_date"));
        dateConverter.convertToDatabaseColumn(dob);
        int personId = Integer.parseInt(request.getParameter("searchID"));

        updatePerson = new Person(personId, Integer.parseInt(request.getParameter("ssn")), request.getParameter("fname"),
                request.getParameter("lname"), dob, 1, request.getParameter("email"),
                request.getParameter("address_line1"), request.getParameter("address_line2"),
                request.getParameter("city"), request.getParameter("state"),
                request.getParameter("zip"), request.getParameter("phone"));

        try {
            dao.updatePerson(updatePerson);
            updateSuccess = true;
            logger.info("##############In update member servlet ###############" + updateSuccess);
            logger.info("updated first name " + request.getParameter("fname"));
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return updateSuccess;


    }
}