package edu.matc.controller;

import edu.matc.entity.AdminAction;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        //Get session container
        HttpSession session = request.getSession(true);

        //Get the adminPageUrl for the page, requested by the user
        String adminPageUrl = request.getParameter("adm_option");

        //Store the url in the session container, so it will be called by the
        //member search page
        session.setAttribute("adminPageUrl", adminPageUrl);

        boolean needMemberSearch = false;

        String memberSearchPageUrl = "/member_search.jsp";

        //Get a list of admin actions and find the action that was selected
        List<AdminAction> actionList = (ArrayList<AdminAction>)session.getAttribute("adminActionsList");


        //Find if the display member search switch is 'Y' for that action
        for (AdminAction action : actionList) {
            if (action.getActionJSPName() == adminPageUrl){
                if (action.getSearchMemberSw().equals('Y')) {
                    needMemberSearch = true;
                }
            }
        }

        //Call member search form if needed, otherwise forward to the page, requested by the user
        if(needMemberSearch) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(adminPageUrl);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(memberSearchPageUrl);
            dispatcher.forward(request, response);
        }

    }
}