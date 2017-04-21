package edu.matc.controller;

/**
 *
 * @author O Collins
 */

import edu.matc.pdfbox.CreateMemberStatement;
import edu.matc.persistence.PersonDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * The .
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
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=formatter1.parse(sDate1);
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String feedbackMessage = null;
        session.removeAttribute("feedbackMessage");

        try {
            CreateMemberStatement createMemberStatement = new CreateMemberStatement();
            createMemberStatement.createPDF(memberId, startDate, endDate);
            feedbackMessage = "Statement was produced successfully. Click the link below to view.";
        } catch (Exception ex) {
            feedbackMessage = "Processing Error was encountered. Please contact Help Desk";
        }

        session.setAttribute("feedbackMessage", feedbackMessage);

        String url = String.valueOf(session.getAttribute("adminPageUrl"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}