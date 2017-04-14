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
        name = "deleteMemberActionServlet",
        urlPatterns = { "/deleteMemberActionServlet" }
)
public class DeleteMemberActionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    HttpSession session;
    PersonDao dao;
    Person deletePerson;
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

        //Try to delete member information and send result on the screen
        boolean deleteSuccess = deleteMemberInfo(request);
        if (deleteSuccess) {
            message = "Member information was removed successfully";
        } else {
            message = "There was an error removing member information. Please contact Help Desk";
        }
        session.setAttribute("resultMessage", message);
        session.removeAttribute("aPerson");

        String url = "/delete_member.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Delete member information.
     * @param request the request
     * @return the boolean
     */
    public boolean deleteMemberInfo(HttpServletRequest request) {
        dao = new PersonDao();
        boolean deleteSuccess = false;

        int personId = Integer.parseInt(request.getParameter("searchID"));

        try {
            dao.deletePerson(personId);
            deleteSuccess = true;
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return deleteSuccess;


    }
}