package edu.matc.email;

//**

import edu.matc.persistence.PersonDao;
import edu.matc.persistence.UserDao;
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

/* Member search action selection servlet
        *
        * @author Olena Collins
        */
@WebServlet(
        name = "sendEmailServlet",
        urlPatterns = { "/sendEmailServlet" }
)
public class SendEmailServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    HttpSession session;
    UserDao dao;

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

        dao = new UserDao();
        HttpSession session = request.getSession(true);
        String sendEmailMessage = "Email was sent successfully";
        String emailAddress = (String) session.getAttribute("personEmail");

        ServletContext context = getServletContext();
        Properties properties = (Properties)context.getAttribute("applicationProperties");
        String url = properties.getProperty("loginJsp.name");

        session.removeAttribute("sendEmailMessage");
        session.setAttribute("sendEmailMessage", emailAddress);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
