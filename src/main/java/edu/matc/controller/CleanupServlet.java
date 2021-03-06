package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Duration;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.Properties;


/**
 * Servlet to cleanup form data from the calories calculator results
 *  @author Olena Collins
 */
@WebServlet(
        name = "cleanupServlet",
        urlPatterns = { "/cleanupServlet" }
)
public class CleanupServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Create context container
        //ServletContext context = getServletContext();
        HttpSession session = request.getSession(true);
        session.removeAttribute("RequestedCaloriesResult");
        session.removeAttribute("MoreCaloriesResult");
        session.removeAttribute("Duration");
        session.removeAttribute("Weight");
        session.removeAttribute("DurationResult");


        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String responseUrl = properties.getProperty("caloriesCalculatorJsp.name");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
        dispatcher.forward(request, response);

    }


}