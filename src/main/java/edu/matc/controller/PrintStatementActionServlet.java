package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.pdfbox.CreateMemberStatement;
import edu.matc.persistence.PersonDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Print member statement action servlet.
 */
@WebServlet(
        name = "printStatementActionServlet",
        urlPatterns = { "/printStatementActionServlet" }
)
public class PrintStatementActionServlet extends HttpServlet {
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

        int memberId = Integer.parseInt(request.getParameter("memberID"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String feedbackMessage = null;
        session.removeAttribute("feedbackMessage");

        ServletContext servletContext = getServletContext();
        Properties properties = (Properties) servletContext.getAttribute("applicationProperties");
        String pdfFileName = properties.getProperty("memberStatement.fileName");

        try {
            CreateMemberStatement createMemberStatement = new CreateMemberStatement();
            createMemberStatement.createPDF(memberId, startDate, endDate, pdfFileName);
            feedbackMessage = "Statement was produced successfully.";
        } catch (Exception ex) {
            feedbackMessage = "Processing Error was encountered. Please contact Help Desk";
        }

        session.setAttribute("feedbackMessage", feedbackMessage);

        String url = String.valueOf(session.getAttribute("adminPageUrl"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}