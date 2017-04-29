package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.persistence.PersonDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * The type Log in display servlet.
 */
@WebServlet(
        name = "homeDispServlet",
        urlPatterns = { "/homeDispServlet" }
)
public class HomeDispServlet extends HttpServlet {
    private final Logger logger= Logger.getLogger(String.valueOf(this.getClass()));
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
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("indexJsp.name");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}