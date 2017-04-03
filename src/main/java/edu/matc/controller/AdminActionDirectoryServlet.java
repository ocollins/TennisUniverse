package edu.matc.controller;

import edu.matc.persistence.ServiceDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    public void init() {
        ServiceDao dao = new ServiceDao();
        //request.setAttribute("serviceList", dao.getAllServices());

    }
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
        //response.setContentType("text/html");

        //Remove the old session
        //HttpSession session = request.getSession(true);
        //session.invalidate();
        String url = request.getParameter("adm_option");
        logger.info("OVC displaying url " + url);

        //String url = "/add_member.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}