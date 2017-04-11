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
    PersonDao dao;
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

        dao = new PersonDao();

        //Create a session container
        session = request.getSession(true);

        //Retreive search parameters
        String lastName = null;
        int personId = 0;

        if (request.getParameter("searchID")!= null && !request.getParameter("searchID").isEmpty() ) {
            //Get member's info for the ID
            personId = Integer.parseInt(request.getParameter("searchID"));
            aPerson = getMemberInfo(request, personId);
            //Store it in the session
            session.setAttribute("aPerson", getMemberInfo(request, personId));
        //Otherwise, try searching by last name
        } else if (!request.getParameter("searchLastName").isEmpty()){
            //Store list of members with the last name in session container
            lastName = request.getParameter("searchLastName");
            session.setAttribute("memberList", getMemberList(lastName));
        }

        String url = "/update_member.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Gets member info by ID.
     *
     * @param request  the request
     * @param personId the person id
     * @return the member info
     */
    public Person getMemberInfo(HttpServletRequest request, int personId) {
        session.setAttribute("searchID", personId);

        try {
            aPerson = dao.getPerson(personId);
            logger.info("In search member servlet person " + personId + aPerson.getFirstName());
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return aPerson;
    }

    /**
     * Gets member list.
     *
     * @param lastName the last name
     * @return the member list
     */
    public ArrayList<Person> getMemberList(String lastName) {
        ArrayList<Person> memberList = new ArrayList<>();
        try {
            memberList = (ArrayList<Person>) dao.getPersonByLastName(lastName);
            logger.info("In search member servlet person " + memberList.get(0).getFirstName());
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        }
        return memberList;


    }
}