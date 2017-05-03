package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Activities;
import edu.matc.CaloriesCalculator.ActivitiesItem;
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
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Display fitness page servlet
 * @author Olena Collins
 */
@WebServlet(
        name = "fitnessDispServlet",
        urlPatterns = { "/fitnessDispServlet" }
)
public class FitnessDispServlet extends HttpServlet {
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

        HttpSession session = request.getSession(true);

        //Call the service to get a list of all activities to populate the dropbox
        Client client = ClientBuilder.newClient();

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("caloriesCalculatorActivities.path");
        WebTarget target = client.target(url + "/list");

        //Get response
        String restResponse = null;
        try {
            restResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        } catch (Exception ex) {
            logger.info("Error connecting to the Calories Calculator service " + ex);
            url = properties.getProperty("processingErrorJsp.name");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

        //Map to the Activities POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Activities activities = null;
        try {
            activities = objectMapper.readValue(restResponse, Activities.class);
            List<ActivitiesItem> activityList = activities.getActivities();
            session.setAttribute("activities", activityList);

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }

        String responseUrl = properties.getProperty("fitnessJsp.name");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseUrl);
        dispatcher.forward(request, response);

    }
}