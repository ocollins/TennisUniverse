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
import java.util.ArrayList;
import java.util.Properties;

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

        //Get a session container
        session = request.getSession(true);

        //Remove attributes from the prior session
        session.removeAttribute("aPerson");
        session.removeAttribute("searchID");
        session.removeAttribute("memberList");
        session.removeAttribute("foundMembers");
        //Remove old message
        session.removeAttribute("resultMessage");

        String resultMessage = "Member not found. Plese contact Help Desk to register.";

        //Retrieve search parameters
        String lastName = null;
        int personId = 0;
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = null;

        //Access application properties file to get jsp names
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String adminUrl = (String)session.getAttribute("adminPageUrl");
        String memberSearchUrl = properties.getProperty("memberSearchJsp.name");

        if (request.getParameter("searchID")!= null && !request.getParameter("searchID").isEmpty() ) {
            //Get member's info for the ID
            personId = Integer.parseInt(request.getParameter("searchID"));
            try {
                aPerson = getMemberInfo(request, personId);
            } catch (Exception ex){
                //If person is not found, return to member search page with an error message
                returnWithError(resultMessage, request, response, dispatcher, memberSearchUrl);
            }
            if (aPerson != null) {
                //Store it in the session
                logger.info("found member");
                session.setAttribute("aPerson", aPerson);
                session.setAttribute("searchID", personId);
//            } else {
        }
        //Otherwise, try searching by last name
        } else if (!request.getParameter("searchLastName").isEmpty()){
            //Store list of members with the last name in session container
            lastName = request.getParameter("searchLastName");
            try {
                session.setAttribute("memberList", getMemberList(lastName));
            } catch (Exception ex) {
                //If person is not found, return to member search page with an error message
                returnWithError(resultMessage, request, response, dispatcher, memberSearchUrl);
            }

            session.setAttribute("foundMembers", true);
        }

        //If searching by member ID redirect to another admin page
        //otherwise, bring back results to the member search page
        if (personId != 0) {
            dispatcher = getServletContext().getRequestDispatcher(adminUrl);
        } else {
            dispatcher = getServletContext().getRequestDispatcher(memberSearchUrl);
        }

        dispatcher.forward(request, response);
    }

    /**
     * Gets member info by ID.
     *
     * @param request  the request
     * @param personId the person id
     * @return the member info
     * @throws HibernateException the hibernate exception
     */
    public Person getMemberInfo(HttpServletRequest request, int personId) throws HibernateException{
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

    /**
     * Return with error.
     *
     * @param resultMessage   the result message
     * @param request         the request
     * @param response        the response
     * @param dispatcher      the dispatcher
     * @param memberSearchUrl the member search url
     */
    public void returnWithError(String resultMessage, HttpServletRequest request, HttpServletResponse response, RequestDispatcher dispatcher, String memberSearchUrl) {
        logger.info("no member found ");
        //If person is not found, return to member search page with an error message
        session.setAttribute("foundMembers", false);
        session.setAttribute("resultMessage", resultMessage);
        dispatcher = getServletContext().getRequestDispatcher(memberSearchUrl);
        try {
            dispatcher.forward(request, response);
        } catch (IOException ex) {
            logger.info("error calling member search page" + ex);
        } catch (ServletException se) {
            logger.info("error calling member search page" + se);
        }


    }
}