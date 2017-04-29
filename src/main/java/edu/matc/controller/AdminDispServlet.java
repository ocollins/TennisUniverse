package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.entity.AdminAction;
import edu.matc.persistence.AdminActionDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
/**import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;*/
import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * The type Log in display servlet.
 */
@WebServlet(
        name = "adminDispServlet",
        urlPatterns = { "/adminDispServlet" }
)
public class AdminDispServlet extends HttpServlet {
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

        //Remove the old session
        HttpSession session = request.getSession(true);
        session.invalidate();

        //Create a new session
        session = request.getSession(true);

        //Create AdminActions instance
        AdminActionDao dao = new AdminActionDao();
        List<AdminAction> adminActionList = null;

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = null;

        //Get a list of admin pages
        try {
            adminActionList = dao.getActionList("A");
        } catch (Exception ex) {
            //In case of an error redirect to error page
            logger.info("Error getting list of admin actions" + ex);
            url = properties.getProperty("processingErrorJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

        //Store list of admin actions it in the session
        session.setAttribute("adminActionsList", adminActionList);

        url = properties.getProperty("adminOptionsJsp.name");

        //String url = "/adminoptions.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}