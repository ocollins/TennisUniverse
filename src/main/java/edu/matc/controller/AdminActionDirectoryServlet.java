package edu.matc.controller;

import edu.matc.entity.AdminAction;
import edu.matc.entity.Service;
import edu.matc.persistence.ServiceDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Admin action selection servlet
 * Redirects to the page selected by they user
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
        //Get session container
        HttpSession session = request.getSession(true);

        //Get the adminPageUrl for the page, requested by the user
        String adminPageUrl = request.getParameter("adm_option");

        //Store the url in the session container, so it will be called by the
        //member search page
        session.setAttribute("adminPageUrl", adminPageUrl);

        boolean needMemberSearch = false;

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String memberSearchPageUrl = properties.getProperty("memberSearchJsp.name");
        String errorUrl = properties.getProperty("processingErrorJsp.name");

        //Get a list of admin actions and find the action that was selected
        List<AdminAction> actionList = (ArrayList<AdminAction>)session.getAttribute("adminActionsList");

        //Get a list of member services
        try {
            getListOfServices(session);
        } catch (Exception ex) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(errorUrl);
            dispatcher.forward(request, response);

        }

        //Find if the display member search switch is 'Y' for that action
        for (AdminAction action : actionList) {
            logger.info(action.getActionJSPName() + " " + action.getSearchMemberSw());
            if (action.getActionJSPName().equals(adminPageUrl)){
                if (action.getSearchMemberSw().equals("Y")) {
                    needMemberSearch = true;
                }
            }
        }

        //Call member search form if needed, otherwise forward to the page, requested by the user
        if(needMemberSearch) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(memberSearchPageUrl);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(adminPageUrl);
            dispatcher.forward(request, response);
        }

    }

    /**
     * Gets list of services.
     *
     * @param session the session
     * @throws Exception the exception
     */
    public void getListOfServices(HttpSession session) throws Exception{
        ServiceDao serviceDao = new ServiceDao();
        session.removeAttribute("serviceList");
        try {
            List<Service> services = serviceDao.getAllServices();
            session.setAttribute("serviceList", services);
        } catch (Exception ex) {
            logger.info("Error getting list of all services " + ex);
        }


    }
}