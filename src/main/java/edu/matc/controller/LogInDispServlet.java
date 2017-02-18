package edu.matc.controller;

/**
 *
 * @author O Collins
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * The type Log in display servlet.
 */
@WebServlet(
        name = "logInDispServlet",
        urlPatterns = { "/logInDispServlet" }
)
public class LogInDispServlet extends HttpServlet {
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

        String url = "/login.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}