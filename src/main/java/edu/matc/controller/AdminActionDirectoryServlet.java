package edu.matc.controller;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin action selection servlet
 * @author Olena Collins
 */
@WebServlet(
        name = "adminActionDirectoryServlet",
        urlPatterns = { "/adminActionDirectoryServlet" }
)
public class AdminActionDirectoryServlet extends HttpServlet {
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

        String url = request.getParameter("adm_option");
        logger.info("OVC displaying url " + url);

        //Get a page url to redirect to selected page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}