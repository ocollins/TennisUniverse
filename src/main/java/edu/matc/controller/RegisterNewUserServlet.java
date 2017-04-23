package edu.matc.controller;

import org.apache.log4j.Logger;

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


/**
 * Servlet to cleanup form data from the screen
 * @author Olena Collins
 */
@WebServlet(
        name = "registerNewUserServlet",
        urlPatterns = { "/registerNewUserServlet" }
)
public class RegisterNewUserServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Create context container
        //ServletContext context = getServletContext();
        HttpSession session = request.getSession(true);
//        session.removeAttribute("RequestedCaloriesResult");
//        session.removeAttribute("MoreCaloriesResult");
//        session.removeAttribute("Duration");
//        session.removeAttribute("Weight");
//        session.removeAttribute("DurationResult");


        //String responceurl = "/fitness.jsp";
        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String responseUrl = properties.getProperty("tempAdminOptions.name");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
        dispatcher.forward(request, response);

    }


}