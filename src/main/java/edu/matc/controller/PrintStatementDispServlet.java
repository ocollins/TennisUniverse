package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.persistence.PersonDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The .
 */
@WebServlet(
        name = "printStatementDispServlet",
        urlPatterns = { "/printStatementDispServlet" }
)
public class PrintStatementDispServlet extends HttpServlet {
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
        //session.invalidate();

        //Create a new session
        //session = request.getSession(true);
        //Create AdminActions instance
        //AdminActionDao dao = new AdminActionDao();

        //Get a list of admin actions and store it in the session
        //request.setAttribute("adminActionsList", dao.getAllAdminActions());
        //request.setAttribute("message", "Testing message");

        //String url = "/.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);

    }
}