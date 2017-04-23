package edu.matc.controller;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  Tennis Univerese Management Application Startup Servlet for Project 4
 *  @author Olena Collins
 */

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/application-startup" },
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {
    private Logger logger = Logger.getLogger(this.getClass());
    Properties properties;

    /**
    *  Servlet init method
    *  @exception ServletException 
    */
    public void init() throws ServletException {
        properties = new Properties();

        //Application properties
        String propertiesFilePath = "/application.properties";

        //Create ServletContext object
        ServletContext context = getServletContext();

        InputStream propertiesStream =
                this.getClass().getResourceAsStream(propertiesFilePath);
        try {
            properties.load(propertiesStream);

            //Store properties object in the ServletContext container
            context.setAttribute("applicationProperties", properties);

        } catch (IOException iOException) {
            logger.info("Cannot load application properties file");
            iOException.printStackTrace();
        } catch (Exception exception) {
            logger.info("Error loading application properties file");
            exception.printStackTrace();
        }
    }



}

