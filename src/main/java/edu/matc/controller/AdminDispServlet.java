package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.entity.AdminAction;
import edu.matc.persistence.AdminActionDao;

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

        List<AdminAction> actions = dao.getAllAdminActions();


        //Get a list of admin actions and store it in the session
        session.setAttribute("adminActionsList", actions);

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("adminOptionsJsp.name");

        //String url = "/adminoptions.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}